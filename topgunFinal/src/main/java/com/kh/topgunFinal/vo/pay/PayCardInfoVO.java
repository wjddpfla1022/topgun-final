package com.kh.topgunFinal.vo.pay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PayCardInfoVO {
	private String kakaopayPurchaseCorp;
	private String kakaopayPurchaseCorpCode;
	private String kakaopayIssuerCorp;
	private String kakaopayIssuerCorpCode;
	private String bin;
	private String cardType;
	private String installMonth;
	private String approvedId;
	private String cardMid;
	private String interestFreeInstall;
	private String installmentType;
	private String cardItemCode;
}
