package com.example.MonitoringAndCommunicationMicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.validation.annotation.Validated;

import java.util.TimeZone;

@Validated
@SpringBootApplication
public class MonitoringAndCommunicationMicroServiceApplication {
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MonitoringAndCommunicationMicroServiceApplication.class);
	}

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(MonitoringAndCommunicationMicroServiceApplication.class, args);
	}


}
