package com.seoul.library.jeheon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JeheonApplication {

	public static void main(String[] args) {
		SpringApplication.run(JeheonApplication.class, args);
	}

}
