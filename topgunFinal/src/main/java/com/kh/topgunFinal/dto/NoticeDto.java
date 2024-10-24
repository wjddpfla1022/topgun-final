package com.kh.topgunFinal.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Data
public class NoticeDto {
    private int noticeId;           // Camel Case
    private String noticeTitle;     // Camel Case
    private String noticeContent;   // Camel Case
    private String noticeAuthor;    // Camel Case
    private String noticeCreatedAt;  // Camel Case
}

