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

@Service("tinderUpdatesServiceImpl")
public class TinderUpdatesServiceImpl {

	private static Logger LOGGER = Logger.getLogger(TinderUpdatesServiceImpl.class);
	
	@Autowired
	private TinderConfig tinderConfig;
	
	/**
	 * Method: POST
	 * Description: Returns all updates ( matches, blocks ) from an specific date.
	 * String dateTime must be in ISO-8061 representation.
	 * @param dateTime
	 * @return JSONObject
	 */
	public JSONObject execute(User user, String dateTime) {
		JSONObject json = new JSONObject();
		try {
			URL url = new URL(tinderConfig.getUpdates());
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

			con.setRequestMethod("POST");
			
			json.accumulate("last_activity_date", dateTime);
			
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			con.setRequestProperty("X-Auth-Token", user.getTinderToken());
			con.setRequestProperty("User-Agent", "Tinder/7.5.3 (iPhone; iOS 10.3.2; Scale/2.00");
			con.setRequestProperty("plataform", "web");

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
