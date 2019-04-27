package com.tinderbot.resources;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tinderbot.configurations.security.JwtProvider;
import com.tinderbot.entities.MatchUser;
import com.tinderbot.entities.User;
import com.tinderbot.entities.requests.IdRequest;
import com.tinderbot.entities.requests.MessageRequest;
import com.tinderbot.entities.responses.PaginationResponse;
import com.tinderbot.repositories.MatchUserRepository;
import com.tinderbot.repositories.UserRepository;
import com.tinderbot.services.tinder.ITinderService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/match")
public class MatchResource {
	
	private final static Logger LOGGER = Logger.getLogger(MatchResource.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MatchUserRepository matchUserRepository;
	
	@Autowired
	private ITinderService tinderService;
	
	@Autowired
	private JwtProvider jwtProvider;
		
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<PaginationResponse> matches(
			HttpServletRequest request,
			@RequestParam(value="pageSize", required=false) Integer pageSize,
			@RequestParam(value="page", required=false) Integer page
		){
		
		String header = request.getHeader("Authorization");
        String username = jwtProvider.getUserNameFromJwtToken(header);
			
		User user = userRepository.findOneByUsername(username);
		
		PaginationResponse response;
		
		Long total = matchUserRepository.countByTinderId(user.getTinderId());
		
		// if pageSize and page are not present, then it will return all matches
		if(pageSize != null && page != null) {
			Page<MatchUser> matches = matchUserRepository.findByTinderId(user.getTinderId(), new PageRequest(page, pageSize));
			response = new PaginationResponse(total, matches.getContent());
		} else {
			List<MatchUser> matches = matchUserRepository.findByTinderId(user.getTinderId());
			response = new PaginationResponse(total, matches);
		}
		
		return ResponseEntity.ok().body(response);
	}
		
	@RequestMapping(value = "/block" , method = RequestMethod.POST)
	public ResponseEntity<?> blockUser(HttpServletRequest request, @RequestBody IdRequest id){
		
		String header = request.getHeader("Authorization");   
        String username = jwtProvider.getUserNameFromJwtToken(header);
		
		User user = userRepository.findOneByUsername(username);
		
		MatchUser matchUser = matchUserRepository.findByTinderIdAndUserId(user.getTinderId() ,id.getId());
				
		if(matchUser == null || !( matchUser.getTinderId().equals(user.getTinderId()) ) ) {
			return ResponseEntity.badRequest().build();
		}
		
		matchUser.setTalking(false);
		matchUser.setBlock(true);
		
		matchUserRepository.save(matchUser);
		
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "/sendmessage", method = RequestMethod.POST)
	public ResponseEntity<?> sendMessage(HttpServletRequest request, @RequestBody MessageRequest message){
		
		String header = request.getHeader("Authorization");   
        String username = jwtProvider.getUserNameFromJwtToken(header);
		
		User user = userRepository.findOneByUsername(username);
		
		if(message.getId().isEmpty() || message.getMessage().isEmpty())
			return ResponseEntity.badRequest().build();
		
		Boolean exists = matchUserRepository.existsByTinderIdAndUserId(user.getTinderId(), message.getId());
		
		if(!exists)
			return ResponseEntity.badRequest().build();
		
		tinderService.sendMessage(user, message.getMessage(), message.getId());
		
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
	public ResponseEntity<MatchUser> getInformation(HttpServletRequest request, @PathVariable String id ){
			
		String header = request.getHeader("Authorization");   
        String username = jwtProvider.getUserNameFromJwtToken(header);
		
		User user = userRepository.findOneByUsername(username);
		
		MatchUser matchUser = matchUserRepository.findByTinderIdAndUserId(user.getTinderId(), id);
		
		if(matchUser == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().body(matchUser);
	}

}
