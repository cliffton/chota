package io.cliffton.chota;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ChotaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChotaApplication.class, args);
	}
}
