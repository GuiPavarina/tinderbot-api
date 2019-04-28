package com.tinderbot.services.tinder;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinderbot.configurations.TinderConfig;
import com.tinderbot.entities.User;
import com.tinderbot.utils.Authentication;
import com.tinderbot.utils.InputToJson;

@Service("tinderSendMessageServiceImpl")
public class TinderSendMessageServiceImpl {

	private static Logger LOGGER = Logger.getLogger(TinderSendMessageServiceImpl.class);
	
	@Autowired
	private TinderConfig tinderConfig;
	
	/**
	 * Method: POST
	 * Description: send message to user by match id.
	 * @param message
	 * @param id
	 * @return JSONObject
	 */
	public JSONObject execute(User user,String message, String id) {
		JSONObject json = new JSONObject();
		try {
			URL url = new URL(tinderConfig.getSendMessage() + id);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

			con.setRequestMethod("POST");
			
			json.accumulate("matchId", id);
			json.accumulate("message", message);
			json.accumulate("userId", user.getTinderId());
			
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestProperty("X-Auth-Token", user.getTinderToken());
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36");
			con.setRequestProperty("plataform", "web");
			con.setRequestProperty("user-session-id", "fecd0490-7fdd-4d66-b7ee-82d509bfa4a8");

			OutputStream os = con.getOutputStream();
			os.write(json.toString().getBytes("UTF-8"));
			os.close();
			
			if(con.getResponseCode() == 401) {
				json = Authentication.setNotAuthenticated(user);
				return json;
			}

			InputStreamReader input = new InputStreamReader(con.getInputStream());
			return InputToJson.execute(input);
			

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return json;
		}
	}

}
