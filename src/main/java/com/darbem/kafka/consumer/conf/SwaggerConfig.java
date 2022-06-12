package com.darbem.kafka.consumer.conf;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

	 @Bean
	  public OpenAPI springShopOpenAPI() {
	      return new OpenAPI()
	              .info(new Info().title("Pagamento API")
	              .description("Demo Pagamento")
	              .version("v0.0.1")
	              .license(new License().name("Apache 2.0").url("http://springdoc.org")));
//	              .externalDocs(new ExternalDocumentation()
//	              .description("SpringShop Wiki Documentation")
//	              .url("https://springshop.wiki.github.org/docs"));
	  }
	
	 @Bean
	  public GroupedOpenApi publicApi() {
	      return GroupedOpenApi.builder()
	              .group("pagamento-public")
	              .packagesToScan("com.darbem.kafka.consumer.controller")
	              .pathsToMatch("/pagamento/**")
	              .build();
	  }
	
//	  @Bean
//	  public GroupedOpenApi adminApi() {
//	      return GroupedOpenApi.builder()
//	              .group("springshop-admin")
//	              .pathsToMatch("/admin/**")
//	              .addMethodFilter(method -> method.isAnnotationPresent(Admin.class))
//	              .build();
//	  }
//		
}
