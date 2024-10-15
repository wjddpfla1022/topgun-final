package com.kh.topgunFinal.dto;

import java.sql.Date;

import lombok.Data;


// UserToken-> refreshToken 저장소
@Data
public class UserTokenDto {
	private int tokenNo; // 식별키 token_no
	private String tokenTarget; // 토큰 대상 token_target
	private String tokenValue; // 토큰 값 token_value
	private Date tokenTime; // 토큰 발행 시간 token_time
}
