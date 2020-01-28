package com.latte;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		LatteIsAHorseApplication.class,
		Jsr310JpaConverters.class
})
@EnableTransactionManagement
public class LatteIsAHorseApplication {

	@PostConstruct
	void init() {
		//Timezone Setting
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
	
	public static void main(String[] args) {
		SpringApplication.run(LatteIsAHorseApplication.class, args);
	}

}
//http://localhost:8080/swagger-ui.html