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

@Service("tinderUserProfileServiceImpl")
public class TinderUserProfileServiceImpl  {
	
	private static Logger LOGGER = Logger.getLogger(TinderUserProfileServiceImpl.class);
	
	@Autowired
	private TinderConfig tinderConfig;
	
	/* (non-Javadoc)
	 * @see com.tinderbot.tinderservices.TinderUserProfileService#execute()
	 */
	public JSONObject execute(User user) {
		JSONObject json = new JSONObject();
		try {
			URL url = new URL(tinderConfig.getUserProfile());
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
