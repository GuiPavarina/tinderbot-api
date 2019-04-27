package com.tinderbot.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component("newMessages")
public class NewMessages {

	private Map<String, String> map = new HashMap<String, String>();

	public void addMessages(User user, List<Message> messages) {
		for (Message message : messages) {
			if (message.getTo().equals(user.getTinderId())) {
				if (map.containsKey(message.getMatchId())) {
					String str = map.get(message.getMatchId());
					str = str.concat(" " + message.getMessage());
					map.put(message.getMatchId(), str);
				} else {
					map.put(message.getMatchId(), message.getMessage());
				}
			}
		}
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void clear() {
		map.clear();
	}

	@Override
	public String toString() {
		return "NewMessages [map=" + map.toString() + "]";
	}

}
