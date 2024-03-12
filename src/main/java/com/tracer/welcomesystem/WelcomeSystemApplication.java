package com.tracer.welcomesystem;

import com.tracer.welcomesystem.models.User;
import com.tracer.welcomesystem.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



//@ConfigurationProperties(prefix = "application.yml")
@SpringBootApplication()

public class WelcomeSystemApplication {

	public static void main(String[] args) {
//		ApplicationContext context = new AnnotationConfigApplicationContext(WelcomeSystemApplication.class);
//		SpringApplication.run(WelcomeSystemApplication.class, args);
		try {
			SpringApplication.run(WelcomeSystemApplication.class, args);
		} catch (Exception e) {
			System.out.println(e);
		}


	}

}
