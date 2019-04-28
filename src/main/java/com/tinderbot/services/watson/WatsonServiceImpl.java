package com.tinderbot.services.watson;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.Context;
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.tinderbot.configurations.WatsonConfig;
import com.tinderbot.utils.readers.WatsonServiceResponseReader;

@Service("watsonService")
public class WatsonServiceImpl {
	
	private static Logger LOGGER = Logger.getLogger(WatsonServiceImpl.class);
	
	@Autowired
	private WatsonConfig watsonConfig;
	
	/**
	 * Function must be used to send messages to Watson and get his response.
	 * @param message
	 * @param conversationId
	 * @return String
	 */
	public String sendMessage(String message, String conversationId) {
		Conversation conversation = new Conversation("2018-07-10");
		
		String username = watsonConfig.getUsername();
		String password = watsonConfig.getPassword();
		String workspaceId = watsonConfig.getWorkspaceId();
		
		conversation.setUsernameAndPassword(username, password);
		conversation.setEndPoint("https://gateway.watsonplatform.net/conversation/api");
		
		InputData input = new InputData.Builder(message).build();
		
		Context context = new Context();
		context.setConversationId(conversationId);
		
		MessageOptions options = new MessageOptions.Builder(workspaceId)
		  .input(input)
		  .context(context)
		  .build();

		MessageResponse response = conversation.message(options).execute();
		
		LOGGER.debug(response.toString());
		
		String responseString = WatsonServiceResponseReader.getText(response);
		
		LOGGER.info("Received: " + message + " - Response: " + responseString);
		
		if(responseString == null || responseString.isEmpty()) {
			responseString = "oi";
		}
		
		return responseString;
		
	}

}
