package com.kh.topgunFinal.vo;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeatsPurchaseRequestVO {
	private List<SeatsQtyVO> seatsList;
	private String approvalUrl;
	private String cancelUrl;
	private String failUrl;
}
