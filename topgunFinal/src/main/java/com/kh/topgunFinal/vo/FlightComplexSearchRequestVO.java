package com.kh.topgunFinal.vo;

import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kh.topgunFinal.advice.JsonEmptyStringToNullDeserializer;

import lombok.Data;

//Request VO (복합 검색 요청)
@Data
public class FlightComplexSearchRequestVO {
	    private String departureAirport;  // 출발지
	    
	    private String arrivalAirport;    // 도착지
	    
	    private String airlineName;
		
	    @JsonDeserialize(using = JsonEmptyStringToNullDeserializer.class)
	    private String departureTime;     // 출발 날짜 (YYYY-MM-DD)
	    
	    private Integer passengers;       // 인원수
	    
	    private List<String> orderList;   // 정렬 기준
	    private Integer beginRow, endRow;

}
