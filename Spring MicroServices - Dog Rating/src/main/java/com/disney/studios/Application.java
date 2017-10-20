package com.disney.studios;

 

import static springfox.documentation.builders.PathSelectors.any;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * Bootstraps the Spring Boot com.disney.studios.Application
 *
 * Created by fredjean on 9/21/15.
 */
@EnableSwagger2
@SpringBootApplication
public class Application {
	
	 
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
   
    
    @Bean
    public Docket api() { 
		return new Docket(DocumentationType.SWAGGER_2).groupName("Dog Images and Ratings").select()
                .apis(RequestHandlerSelectors.basePackage("com.disney.studios"))
                .paths(any()).build().apiInfo(new ApiInfo("Dog Images",
                        "A set of services to provide data access to Dog Images", "1.0.0", null,
                        new Contact("Saptarshi Katwala", " ", null),null, null));                                           
    }
    
    
}
