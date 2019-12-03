package com.stackroute.matchrecommendationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MatchRecommendationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatchRecommendationServiceApplication.class, args);
	}

}

