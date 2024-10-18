package com.kh.topgunFinal.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeatsApproveRequestVO {
	private String partnerOrderId;
	private String tid;
	private String pgToken;
	private List<SeatsQtyVO> seatsList;
}
