package com.tinderbot.services.tinder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinderbot.configurations.TinderConfig;
import com.tinderbot.utils.InputToJson;

@Service("tinderAuthenticationServiceImpl")
public class TinderAuthenticationServiceImpl {
	
	private static Logger LOGGER = Logger.getLogger(TinderAuthenticationServiceImpl.class);
	
	@Autowired
	private TinderConfig tinderConfig;
		
	/**
	 * Method: POST
	 * Description: Get Tinder's token. This token must be used to any other request to Tinder's API.
	 * @return JSONObject
	 * @throws IOException 
	 */
	public JSONObject execute(String facebookAccessToken) {
		JSONObject json = new JSONObject();
		HttpsURLConnection con = null;
		try {
			URL url = new URL(tinderConfig.getAuth());
			LOGGER.info(url.toString());
			con = (HttpsURLConnection) url.openConnection();
			
			con.setRequestMethod("POST");
			LOGGER.info(facebookAccessToken);
			json.accumulate("token", facebookAccessToken);

			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			con.setRequestProperty("User-Agent", "Tinder/7.5.3 (iPhone; iOS 10.3.2; Scale/2.00");

			OutputStream os = con.getOutputStream();
			os.write(json.toString().getBytes("UTF-8"));
			os.close();
					
			InputStreamReader input = new InputStreamReader(con.getInputStream());
			
			return InputToJson.execute(input);
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new JSONObject();
		}
		
	}
	
}

