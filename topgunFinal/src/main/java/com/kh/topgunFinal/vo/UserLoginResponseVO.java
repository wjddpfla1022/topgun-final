package com.kh.topgunFinal.vo;

import lombok.Data;

//로그인 성공시 보낼 객체
@Data
public class UserLoginResponseVO {
	private String usersId;// 로그인 성공한 사용자의 아이디
	private String usersType;// 로그인 성공한 사용자의 등급
	private String usersName; // 로그인 성공한 사용자의 이름
	private String accessToken;// 나중에 들고올 토큰
	private String refreshToken;// 어떤 이유로 액세스토큰 사용이 불가해지면 갱신을 위한 토큰(아주긴 시간 + DB에 저장)
}
