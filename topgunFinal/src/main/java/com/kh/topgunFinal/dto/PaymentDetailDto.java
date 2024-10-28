package com.kh.topgunFinal.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PaymentDetailDto {
    private int paymentDetailNo;
    private int flightId;
    private String paymentDetailName;
//    private String paymentDetailRank;
    private int paymentDetailPrice;
    private int paymentDetailQty;
    private int paymentDetailSeatsNo;
    private int paymentDetailOrigin;
    private String paymentDetailStatus;
//    private String paymentDetailPassport;
//    private String paymentDetailPassanger;
//    private String paymentDetailEnglish;
//    private String paymentDetailSex;
//    private LocalDate paymentDetailBirth;
//    private String paymentDetailCountry;
//    private String paymentDetailVisa;
//    private LocalDate paymentDetailExpire;
}
 