package com.kh.topgunFinal.vo.pay;

import com.kh.topgunFinal.dto.PaymentDetailDto;
import com.kh.topgunFinal.dto.SeatsDto;

import lombok.Data;

@Data
//취소할때 사용하려했으나 다른방법으로 사용하지 않음
public class PaymentCancelSatusVO {
	private PaymentDetailDto paymentDetailDto;
    private SeatsDto seatsDto;
}
