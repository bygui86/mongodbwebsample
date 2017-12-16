package com.rabbitshop.mongodbweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
// @EnableWebMvc // <mvc:annotation-driven />
// @EnableScheduling // ??
public class MongoDbWebApplication {
	
	public static void main(final String[] args) {

		SpringApplication.run(MongoDbWebApplication.class, args);
	}
	
}
