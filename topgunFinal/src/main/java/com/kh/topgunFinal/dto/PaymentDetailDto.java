package com.kh.topgunFinal.dto;

import lombok.Data;

@Data
public class PaymentDetailDto {
    private int paymentDetailNo;
    private int paymentDetailPrice;
    private int paymentDetailOrigin;
    private int paymentDetailSeatsNo;
    private String payementDetailSeatsRank;
    private String paymentDetailPassportCode;
    private String paymentDetailName;
    private String paymentDetailSex;
    private String paymentDetailBirth;
    private String paymentDetailStatus;
}
