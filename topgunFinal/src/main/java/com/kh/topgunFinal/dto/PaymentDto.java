package com.kh.topgunFinal.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class PaymentDto {
    private int pyamentNo;
    private String paymentTid;
    private String paymentfly;
    private String paymentAirline;
    private String paymentSeatsRank;//삭제? detail 중복
    private String paymentSeatsNo;//삭제? detail 중복
    private int paymentTotal;
    private int paymentRemain;
    private String paymentName;//삭제?  detail 중복
    private Date paymentTime;
    
}
