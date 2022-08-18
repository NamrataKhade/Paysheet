package com.nts;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication

public class PaysheetApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaysheetApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//		corsConfiguration.addAllowedMethod("GET");
//		corsConfiguration.addAllowedMethod("POST");
//		corsConfiguration.addAllowedMethod("PUT");
//		corsConfiguration.addAllowedMethod("DELETE");
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		FilterRegistrationBean<CorsFilter> filterRegistrationBean = new FilterRegistrationBean<>(
				new CorsFilter(urlBasedCorsConfigurationSource));
		filterRegistrationBean.setOrder(0);
		return filterRegistrationBean;
	}
}
