package com.latte;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.latte.property.FileUploadProperties;

@SpringBootApplication
@EnableConfigurationProperties({ FileUploadProperties.class })
@EntityScan(basePackageClasses = { LatteIsAHorseApplication.class, Jsr310JpaConverters.class })
@EnableTransactionManagement
public class LatteIsAHorseApplication {

	@PostConstruct
	void init() {
		// Timezone Setting
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(LatteIsAHorseApplication.class, args);
	}

}
//http://localhost:443/swagger-ui.html