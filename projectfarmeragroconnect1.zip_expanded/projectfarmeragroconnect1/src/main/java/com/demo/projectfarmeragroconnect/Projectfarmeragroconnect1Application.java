package com.demo.projectfarmeragroconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.demo.projectfarmeragroconnect","com.controller","com.dao","com.model","com.service"})
@EntityScan("com.model")
@EnableJpaRepositories(basePackages="com.dao")
public class Projectfarmeragroconnect1Application {

	public static void main(String[] args) {
		SpringApplication.run(Projectfarmeragroconnect1Application.class, args);
	}

}
