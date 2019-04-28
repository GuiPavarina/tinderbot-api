package com.tinderbot.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.tinderbot.controllers.TinderBotAuthenticateUsers;

@SpringBootApplication
@ComponentScan({"com.tinderbot"})
public class Application implements CommandLineRunner {
	
	@Autowired
	private TinderBotAuthenticateUsers tinderBotAuthenticateUsers;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		tinderBotAuthenticateUsers.checkApiKeyAtStart();
	}
}
