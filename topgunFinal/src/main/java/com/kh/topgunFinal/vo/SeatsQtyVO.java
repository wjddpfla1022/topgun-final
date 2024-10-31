package com.kh.topgunFinal.vo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeatsQtyVO {
	private int flightId;
	private int seatsNo; 
    private int qty; //1로 고정
    private String paymentDetailPassport;
    private String paymentDetailPassanger;
    private String paymentDetailEnglish;
    private String paymentDetailSex;
    private LocalDate paymentDetailBirth;
    private String paymentDetailCountry;
    private String paymentDetailVisa;
    private LocalDate paymentDetailExpire;
}
