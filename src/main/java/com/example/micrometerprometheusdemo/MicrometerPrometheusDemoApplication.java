package com.example.micrometerprometheusdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class MicrometerPrometheusDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrometerPrometheusDemoApplication.class, args);
	}

	@Bean
	ApplicationRunner init(FruitRepository repository) {
		return (ApplicationArguments args) ->  dataSetup(repository);
	}

	private void dataSetup(FruitRepository repository) {
		log.info("Creating default fruit");
		FruitModel fruit = FruitModel.builder()
				.color("Red")
				.name("Apply")
				.build();
		repository.save(fruit);

	}

}
