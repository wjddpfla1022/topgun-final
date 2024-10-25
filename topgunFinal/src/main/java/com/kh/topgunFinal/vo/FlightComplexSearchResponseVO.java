package com.kh.topgunFinal.vo;

import java.util.List;

import lombok.Data;

//Response VO (검색 결과 응답)
@Data
public class FlightComplexSearchResponseVO {
    private List<FlightVO> flightList;
	private int count; 	//개수는 몇개인가
	private boolean Last;		//다음 항목이 존재하는가
}
