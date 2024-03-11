package com.tracer.welcomesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class WelcomeSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(WelcomeSystemApplication.class, args);
	}

}
