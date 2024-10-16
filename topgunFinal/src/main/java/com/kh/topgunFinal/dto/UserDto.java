package com.kh.topgunFinal.dto;

import lombok.Data;

// 회원 공용 테이블
@Data
public class UserDto {
	private String userId; // user_id
	private String userName; //user_name
	private String userPassword; //user_password
	private String userEmail; // user_email
	private String userContact; //user_contact
	private String userType; // user_type
}
