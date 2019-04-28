package com.tinderbot.services.tinder;

import java.io.InputStreamReader;
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

@Service("tinderSuperLikeUserServiceImpl")
public class TinderSuperLikeUserServiceImpl {

	private static Logger LOGGER = Logger.getLogger(TinderSuperLikeUserServiceImpl.class);

	@Autowired
	private TinderConfig tinderConfig;
	
	/**
	 * Method: POST
	 * Description: Super like user from user's id.
	 * @param id
	 * @return JSONObject
	 */
	public JSONObject execute(User user,String id) {
		JSONObject json = new JSONObject();
		try {
			URL url = new URL(tinderConfig.getSuperLikeUser() + id + "/super");
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

			con.setRequestMethod("POST");

			con.setDoOutput(true);
			con.setRequestProperty("X-Auth-Token", user.getTinderToken());
			con.setRequestProperty("User-Agent", "Tinder/7.5.3 (iPhone; iOS 10.3.2; Scale/2.00");
			con.setRequestProperty("plataform", "web");

			InputStreamReader input = new InputStreamReader(con.getInputStream());

			if(con.getResponseCode() == 401) {
				json = Authentication.setNotAuthenticated(user);
				return json;
			}
			
			return InputToJson.execute(input);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return json;

		}
	}
}
