package com.tinderbot.services;

import java.util.List;

//import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinderbot.entities.MatchUser;
import com.tinderbot.entities.Message;
import com.tinderbot.entities.NewMessages;
import com.tinderbot.entities.User;
import com.tinderbot.repositories.MatchUserRepository;
import com.tinderbot.services.tinder.ITinderService;
import com.tinderbot.utils.LoggerUtils;
import com.tinderbot.utils.readers.TinderUpdatesResponseReader;

@Service("tinderBotUpdateServiceImpl")
public class TinderBotUpdateServiceImpl {

//	private final static Logger LOGGER = Logger.getLogger(TinderBotUpdateService.class);

	@Autowired
	private ITinderService tinderService;

	@Autowired
	private MatchUserRepository matchUserRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private NewMessages newMessages;
	
	@Autowired
	private NotificationsServiceImpl notificationsServiceImpl;

	public void updateFromStart(User user) {

		userService.getAndUpdateLastActivity(user);

		List<MatchUser> list = TinderUpdatesResponseReader.readAllMatches(user.getTinderId(),
				tinderService.getUpdatesFromDate(user, ""));

		matchUserRepository.insert(list);

		LoggerUtils.printListOfMatchUsers(list);

	}

	public void updateFromLastActivity(User user) {

		String dateTime = userService.getAndUpdateLastActivity(user);

		JSONObject json = tinderService.getUpdatesFromDate(user, dateTime);

		List<MatchUser> list = TinderUpdatesResponseReader.readAllMatches(user.getTinderId(), json);
		matchUserRepository.insert(list);

		LoggerUtils.printListOfMatchUsers(list);

		List<Message> messages = TinderUpdatesResponseReader.readAllMessages(json);

		messages.forEach((message) -> {
			MatchUser matchUser = matchUserRepository.findByMatchId(message.getMatchId());
			if (!matchUser.getMessages().contains(message))
				matchUser.getMessages().add(message);
			matchUser.setTalking(true);
			matchUserRepository.save(matchUser);
		});

		newMessages.addMessages(user,messages);

		notificationsServiceImpl.addNotifications(user, messages);

		LoggerUtils.printListOfMessages(messages);
	}

}
