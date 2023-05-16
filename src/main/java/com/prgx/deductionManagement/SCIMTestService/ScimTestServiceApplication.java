package com.prgx.deductionManagement.SCIMTestService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.prgx.deductionManagement.*"})
@EnableJpaRepositories("com.prgx.deductionManagement.*")
@EntityScan("com.prgx.deductionManagement.*")
@SpringBootApplication
public class ScimTestServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ScimTestServiceApplication.class, args);
	}
}
