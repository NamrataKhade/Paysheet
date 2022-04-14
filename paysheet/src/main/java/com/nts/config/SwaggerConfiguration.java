package com.nts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * <p>
 * The SwaggerConfiguration class implemented for the Swagger Configuration
 * 
 * 
 * @author Parth
 *
 */


@EnableSwagger2
@Configuration
public class SwaggerConfiguration {
	
	@Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.apiInfo(metadata())
        		.groupName("data_extraction_service")
        		.select()
        		//.paths(PathSelectors.regex("/employee.*"))
        		.apis(RequestHandlerSelectors.basePackage("com.nts.controller"))
        		.paths(PathSelectors.any())
        		.build()
        		.useDefaultResponseMessages(false)
        		.forCodeGeneration(true);
    }
 
    private ApiInfo metadata() {
        return new ApiInfoBuilder()
        		.title("Data Extraction Service")
        		.description("API reference guide for developers")
        		.version("0.0.1")
        		.build();  
    }
    /*
    
	@Bean
	public Docket api() {
		// @formatter:off
		// Register the controllers to swagger
		// Also it is configuring the Swagger Docket
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.nts.controller"))
				//.apis(RequestHandlerSelectors.any())
				//.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.paths(PathSelectors.any())
				//.paths(PathSelectors.ant("/swagger2-demo"))
				.build();
		// @formatter:on
	}
*/
}

