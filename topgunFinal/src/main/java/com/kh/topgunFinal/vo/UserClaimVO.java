package com.kh.topgunFinal.vo;

import lombok.Data;

// JWT 생성시 사용될 Claim
@Data
public class UserClaimVO {
	private String userId;
	private String userType;
	private String userName;
}
