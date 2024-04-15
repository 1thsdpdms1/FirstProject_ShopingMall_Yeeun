package org.spring.e1i4TeamProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class E1i4TeamProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(E1i4TeamProjectApplication.class, args);
	}

}
