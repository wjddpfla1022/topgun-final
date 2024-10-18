package com.kh.topgunFinal.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PayConfiguration {

	@Autowired
	private PayProperties payProperties;
	
	@Bean
	public RestTemplate template() {
		RestTemplate template = new RestTemplate();
		return template;
	}
	
	@Bean
	public HttpHeaders headers() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "SECRET_KEY " + payProperties.getSecret());
		headers.add("Content-Type", "application/json");
		return headers;
	}
	
}
