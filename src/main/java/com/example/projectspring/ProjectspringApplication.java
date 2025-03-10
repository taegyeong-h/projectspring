package com.example.projectspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "entity")
public class ProjectspringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectspringApplication.class, args);
	}

}
