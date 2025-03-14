package io.github.paulosdoliveira.livrariaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.CrossOrigin;


@EnableJpaAuditing
@SpringBootApplication
@CrossOrigin("*")
public class LivrariaapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivrariaapiApplication.class, args);
	}

}
