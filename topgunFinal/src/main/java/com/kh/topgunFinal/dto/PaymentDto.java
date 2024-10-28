package com.kh.topgunFinal.dto;


import java.sql.Date;

import lombok.Data;

@Data
public class PaymentDto {
    private int paymentNo;
    private String paymentTid;
//    private int flightId;
    private String paymentName;
//    private String paymentRank;
    private int paymentTotal;
    private int paymentRemain;
    private String userId;
    private Date paymentTime;
}
