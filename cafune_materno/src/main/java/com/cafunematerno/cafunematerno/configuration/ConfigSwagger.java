package com.cafunematerno.cafunematerno.configuration;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class ConfigSwagger {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors
			.basePackage("com.cafunematerno.cafunematerno.controller"))
			.paths(PathSelectors.any())
			.build()
			.apiInfo(metadata())
			.useDefaultResponseMessages(false)
			.globalResponses(HttpMethod.GET, responseMessageForGET());
	}
	
	public static ApiInfo metadata() {
		return new ApiInfoBuilder()
				.title("API - Cafuné Materno")
				.description("Projeto API Spring - Cafuné Materno")
				.version("1.0.0")
				.license("Apache License Version 2.0")
				.licenseUrl("http://localhost:8080/swagger-ui/")
				.contact(contact1())
				.contact(contact2())
				.contact(contact3())
				.contact(contact4())
				.contact(contact5())
				.build();
				
	}
	
	private static Contact contact1() {
		
		return new Contact("Angelo R. Arcangelo", "https://github.com/ArcangeloAR", "ang.arcangelo@gmail.com");
	}
	
	private static Contact contact2() {
		
		return new Contact("Ellen Prado Pimentel", "https://github.com/Ellen-code", "ellenprado.pimentel@gmail.com");
			
	}
	
	private static Contact contact3() {
		
		return new Contact("Julio Santos Silva", "https://github.com/Julio-0417", "julio.s.silva0417@gmail.com");
			
	}
	
	private static Contact contact4() {
		
		return new Contact("Luciano F. Nascimento", "https://github.com/Lucianf5", "luciano.fernas@gmail.com");
			
	}
	
	private static Contact contact5() {
		
		return new Contact("Nathalia F. Amorim", "https://github.com/FaveroNath", "faveronathalia@gmail.com");
			
	}
	
	private static List<Response> responseMessageForGET() {
		return new ArrayList<Response>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			{
				add(new ResponseBuilder().code("200")
				.description("Ok!").build());
				add(new ResponseBuilder().code("201")
				.description("Objeto Criado!").build());
				add(new ResponseBuilder().code("401")
				.description("Não Autorizado!").build());
				add(new ResponseBuilder().code("403")
				.description("Proibido!").build());
				add(new ResponseBuilder().code("404")
				.description("Não Encontrado!").build());
				add(new ResponseBuilder().code("500")
				.description("Erro!").build());
			}
		};
	}
	
	
	
	
}
