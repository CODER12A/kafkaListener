package com.training.kafkaListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})

public class KafkaListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaListenerApplication.class, args);
		
	}

}
