package com.kh.topgunFinal.vo;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kh.topgunFinal.advice.JsonEmptyIntegerToNullDeserializer;
import com.kh.topgunFinal.advice.JsonEmptyStringToNullDeserializer;


import lombok.Data;

@Data
public class UserComplexRequestVO {
	@JsonDeserialize(using = JsonEmptyStringToNullDeserializer.class)
	private String usersId;
	@JsonDeserialize(using = JsonEmptyStringToNullDeserializer.class)
	private String usersName;
	@JsonDeserialize(using = JsonEmptyStringToNullDeserializer.class)
	private String usersContact;
	@JsonDeserialize(using = JsonEmptyStringToNullDeserializer.class)
	private String usersEmail;
	@JsonDeserialize(using = JsonEmptyStringToNullDeserializer.class)
	private String usersType;
	@JsonDeserialize(using = JsonEmptyIntegerToNullDeserializer.class)
	private Integer beginRow, endRow;
	
	private List<String> orderList;
}
