package com.kh.topgunFinal.vo;

import lombok.Data;

@Data
public class ChangePasswordRequestVO {
	private String userId;
	private String newPassword;
	private String certEmail;
}
