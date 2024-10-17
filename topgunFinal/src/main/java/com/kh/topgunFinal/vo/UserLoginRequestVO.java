package com.kh.topgunFinal.vo;

import lombok.Data;

//로그인시 쓸 VO
@Data
public class UserLoginRequestVO {
	private String usersId;
	private String usersPw;
	private boolean rememberMe;
}
