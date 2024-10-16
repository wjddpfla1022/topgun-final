package com.kh.topgunFinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TopgunFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopgunFinalApplication.class, args);
	}

}
