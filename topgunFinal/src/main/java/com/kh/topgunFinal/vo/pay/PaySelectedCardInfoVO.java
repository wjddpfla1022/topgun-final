package com.kh.topgunFinal.vo.pay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaySelectedCardInfoVO {
	private String cardBin;
	private Integer installMonth;
	private String installmentType;
	private String cardCorpName;
	private String interestFreeInstall;
}
