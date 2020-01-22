package com.latte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class LatteIsAHorseApplication {

	public static void main(String[] args) {
		SpringApplication.run(LatteIsAHorseApplication.class, args);
	}

}
//http://localhost:8080/swagger-ui.html