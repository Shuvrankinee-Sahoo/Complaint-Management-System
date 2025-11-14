package com.studcomp.stuBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
public class StuBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(StuBackendApplication.class, args);
	}

}
