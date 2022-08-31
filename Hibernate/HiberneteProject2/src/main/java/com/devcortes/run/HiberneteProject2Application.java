package com.devcortes.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
@ComponentScan(basePackages = "com.devcortes")
@Configuration
public class HiberneteProject2Application {

	public static void main(String[] args) {		
		SpringApplication.run(HiberneteProject2Application.class, args);		
		//ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
	}
}
