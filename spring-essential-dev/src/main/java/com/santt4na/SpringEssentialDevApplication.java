package com.santt4na;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
@SpringBootApplication
public class SpringEssentialDevApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringEssentialDevApplication.class, args);
	}
}
