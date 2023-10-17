package com.stock;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StockAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockAppApplication.class, args);
	}
	// hi this is git training on 17th oct 2023
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
