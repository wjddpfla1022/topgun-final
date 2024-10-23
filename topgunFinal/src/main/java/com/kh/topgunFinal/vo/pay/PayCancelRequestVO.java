package com.kh.topgunFinal.vo.pay;

import lombok.Data;

@Data
public class PayCancelRequestVO {
	private String tid;
	private int cancelAmount;
	private int cancelTaxFreeAmount = 0;
}
