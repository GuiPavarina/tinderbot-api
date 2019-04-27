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

@Service("tinderPassUserServiceImpl")
public class TinderPassUserServiceImpl {

	private static Logger LOGGER = Logger.getLogger(TinderPassUserServiceImpl.class);
	
	@Autowired
	private TinderConfig tinderConfig;
	
	/**
	 * Method: GET
	 * Description: pass user by user's id
	 * @param id
	 * @return JSONObject
	 */
	public JSONObject execute(User user,String id) {
		JSONObject json = new JSONObject();
		try {
			URL url = new URL(tinderConfig.getPassUser() + id);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

			con.setRequestMethod("GET");
			
			con.setDoOutput(true);
			con.setRequestProperty("X-Auth-Token", user.getTinderToken());
			con.setRequestProperty("User-Agent", "Tinder/7.5.3 (iPhone; iOS 10.3.2; Scale/2.00");
			con.setRequestProperty("plataform", "web");

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
