package com.kh.topgunFinal.dto;

import lombok.Data;

// 회원 공용 테이블
@Data
public class UserDto {
	private String usersId; // users_id
	private String usersName; //users_name
	private String usersPassword; //users_password
	private String usersEmail; // users_email
	private String usersContact; //users_contact
	private String usersType; // users_type
}
