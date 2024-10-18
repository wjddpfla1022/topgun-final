package com.kh.topgunFinal.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
@ConfigurationProperties(prefix = "custom.pay")
public class PayProperties {
	private String secret;
	private String cid;
}
