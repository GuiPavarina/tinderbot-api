package com.tinderbot.tinderservices;

import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinderbot.config.TinderConfig;
import com.tinderbot.entities.User;
import com.tinderbot.utils.Authentication;
import com.tinderbot.utils.InputToJson;

@Service("tinderAnotherUserProfileServiceImpl")
public class TinderAnotherUserProfileServiceImpl {

	private static Logger LOGGER = Logger.getLogger(TinderAnotherUserProfileServiceImpl.class);
	
	@Autowired
	private TinderConfig tinderConfig;
	
	
	/**
	 * Method: GET
	 * Description: Get profile data from any user
	 * @param id
	 * @return JSONObject
	 */
	public JSONObject execute(User user,String id) {
		JSONObject json = new JSONObject();
		try {
			URL url = new URL(tinderConfig.getAnotherUserProfile() + id);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

			con.setRequestMethod("GET");
			
			con.setDoOutput(true);
			con.setRequestProperty("X-Auth-Token", user.getTinderToken());
			con.setRequestProperty("User-Agent", "Tinder/7.5.3 (iPhone; iOS 10.3.2; Scale/2.00");
			
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


