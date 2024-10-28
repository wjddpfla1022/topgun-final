package com.kh.topgunFinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

//리프레시 토큰 자동 삭제를 위한 스케쥴러	
@EnableScheduling
//모든 시스템에 Spring Security가 자동 적용되는 것을 방지하도록 설정
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class TopgunFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopgunFinalApplication.class, args);
	}

}
