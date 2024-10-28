package com.kh.topgunFinal.dto;

import lombok.Data;

@Data
public class AttachDto {
    private int attachNo;       // ATTACH_NO
    private String attachName;   // ATTACH_NAME
    private String attachType;   // ATTACH_TYPE
    private long attachSize;      // ATTACH_SIZE
}
