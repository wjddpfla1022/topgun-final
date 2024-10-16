package com.kh.topgunFinal.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AirlineDto extends UserDto {
	private String userId; // airline user ID (Foreign Key referencing User)
    private String airlineName; // 항공사 이름 airline_name
    private String airlineNo; // 사업자(또는 항공사) 번호 airline_no
}
