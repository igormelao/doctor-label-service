package com.doctorlabel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DoctorlabelApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorlabelApplication.class, args);
	}
	
	@Value("${allowed.origin}")
	private String allowedOrigin;

}
