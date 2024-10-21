package com.kh.topgunFinal.vo.pay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PayAmountVO {
	private int total;
	private int taxFree;
	private int vat;
	private int point;
	private int discount;
	private int greenDeposit;
}
