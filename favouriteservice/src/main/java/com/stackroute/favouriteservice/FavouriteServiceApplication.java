package com.stackroute.favouriteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

//import com.stackroute.favouriteservice.filters.JwtFilter;

@EnableEurekaClient
@SpringBootApplication
public class FavouriteServiceApplication {

	/*@Bean
	public FilterRegistrationBean jwtFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new JwtFilter());
		filterRegistrationBean.addUrlPatterns("/api/*");
		return filterRegistrationBean;
	}*/


	public static void main(String[] args) {
		SpringApplication.run(FavouriteServiceApplication.class, args);
	}

}

