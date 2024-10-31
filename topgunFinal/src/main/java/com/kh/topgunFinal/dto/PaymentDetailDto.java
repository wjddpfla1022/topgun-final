package com.kh.topgunFinal.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PaymentDetailDto {
    private int paymentDetailNo;
    private int flightId;//복합키 flight_id
    private String paymentDetailName;
//    private String paymentDetailRank;
    private int paymentDetailPrice;
    private int paymentDetailQty;
    private int paymentDetailSeatsNo;//복합키 seats_no
    private int paymentDetailOrigin;
    private String paymentDetailStatus;
    private String paymentDetailPassport;
    private String paymentDetailPassanger;
    private String paymentDetailEnglish;
    private String paymentDetailSex;
    private LocalDate paymentDetailBirth;
    private String paymentDetailCountry;
    private String paymentDetailVisa;
    private LocalDate paymentDetailExpire;
}
 