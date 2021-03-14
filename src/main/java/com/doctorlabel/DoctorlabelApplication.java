package com.doctorlabel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
* 
* <p>This is Spring Boot class and your responsible it to run the application</p>
* 
* 
* @author Igor Melão (igormelao@gmail.com)
* @Date:  14-03-2021
* @since  0.0.1-SNAPSHOT
* 
* 
*/

@SpringBootApplication
@EnableSwagger2
public class DoctorlabelApplication {

	/**
	 * <p>This is a method do run the DoctorlabelApplication</p>
	 * @param
	 * @since 0.0.1-SNAPSHOT
	 */
	public static void main(String[] args) {
		SpringApplication.run(DoctorlabelApplication.class, args);
	}
	
	/**
	 * <p>This attribute that contains the values of all outside endpoints that can acces this application</p>
	 * <p> this is used to configure CORS @see(com.doctorlabel.config.security.WebConfig.java)<p>
	 * @since 0.0.1-SNAPSHOT
	 */
	@Value("${allowed.origin}")
	private String allowedOrigin;

}
