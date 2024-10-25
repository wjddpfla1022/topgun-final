package com.kh.topgunFinal.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Data
public class NoticeDto {
    private int noticeId;          
    private String noticeTitle;    
    private String noticeContent;   
    private String noticeAuthor;    
    private String noticeCreatedAt;  
    private int mainNotice;           // 주요 공지 여부 (0 = false, 1 = true)
    private int urgentNotice;         // 긴급 공지 여부 (0 = false, 1 = true)
    private int modifiedNotice;       // 수정 여부 (0 = false, 1 = true)
}

