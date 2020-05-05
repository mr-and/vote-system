package com.votesystem.graduation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan("com.votesystem.graduation.model")
@EnableJpaRepositories(basePackages = "com.votesystem.graduation.repository")
@EnableCaching
public class GraduationApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraduationApplication.class, args);
	}

}
