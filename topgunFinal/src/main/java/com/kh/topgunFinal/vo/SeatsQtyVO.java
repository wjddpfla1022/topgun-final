package com.kh.topgunFinal.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeatsQtyVO {
	private int seatsNo; 
    private int qty; 
}
