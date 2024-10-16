package com.kh.topgunFinal.vo;

import lombok.Data;

@Data
public class JoinRequestVO {
    private String usersId;
    private String usersPassword;
    private String usersName;
    private String usersEmail;
    private String usersContact;
    private String usersType;

    // 회원일 경우
    private String memberEngName;
    private String memberBirth; // 생년월일
    private String memberGender; // 성별

    // 항공사일 경우
    private String airlineName;
    private String airlineNo;
}
