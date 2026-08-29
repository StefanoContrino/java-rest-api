package com.finale.progetto.progetto_finale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.finale.progetto.progetto_finale.repository")
public class ProgettoFinaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgettoFinaleApplication.class, args);

	}

}
