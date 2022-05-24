package com.nts;

import org.springframework.boot.SpringApplication;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PaysheetApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaysheetApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
