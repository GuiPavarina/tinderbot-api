package com.tinderbot.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableMongoRepositories(basePackages = "com.tinderbot.repositories")
@PropertySource("application.properties")
@EnableScheduling
public class AppConfig {

}
