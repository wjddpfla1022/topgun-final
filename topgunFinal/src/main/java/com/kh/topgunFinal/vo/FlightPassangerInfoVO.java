package com.kh.topgunFinal.vo;

import java.sql.Date;

import lombok.Data;

@Data	
public class FlightPassangerInfoVO {
	private int flightId; 
    private String paymentDetailName;
    private String paymentDetailPassport; 
    private String paymentDetailPassanger; 
    private String paymentDetailEnglish; 
    private String paymentDetailSex;  
    private Date paymentDetailBirth;  
    private String paymentDetailCountry; 
    private String paymentDetailVisa;  
    private Date paymentDetailExpire;
}
