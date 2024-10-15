package com.kh.topgunFinal.vo;

import lombok.Data;

@Data
public class JoinResponseVO {
    private String userId; // 가입할 사용자 ID

    // 사용자 유형별 필드
    private MemberDetails memberDetails; // MEMBER일 경우 추가 정보
    private AirlineDetails airlineDetails; // AIRLINE일 경우 추가 정보

    @Data
    public static class MemberDetails {
        private String memberEngName; // 영어 이름
        private String memberBirth; // 생년월일
        private char memberGender; // 성별
        private int memberPoint; // 포인트
    }

    @Data
    public static class AirlineDetails {
        private String airlineName; // 항공사 이름
        private String airlineNo; // 사업자 번호
    }
}
