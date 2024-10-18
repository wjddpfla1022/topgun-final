package com.kh.topgunFinal.vo.pay;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown =true)
public class PayReadyResponseVO {
	private String tid;
//	private String nextRedirectAppUrl;//앱결제
//	private String nextRedirectMobileUrl;//모바일결제
	private String nextRedirectPcUrl;
//	private String androidAppScheme;//안드로이드결제
//	private String iosAppScheme;//아이폰결제
	private LocalDateTime createdAt;
}
