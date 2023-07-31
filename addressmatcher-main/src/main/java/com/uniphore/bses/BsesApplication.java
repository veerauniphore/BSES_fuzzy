package com.uniphore.bses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.uniphore.*")
@EntityScan(basePackages = "com.uniphore.entity")
@EnableJpaRepositories(basePackages = "com.uniphore.repository")
public class BsesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BsesApplication.class, args);
	}

}
