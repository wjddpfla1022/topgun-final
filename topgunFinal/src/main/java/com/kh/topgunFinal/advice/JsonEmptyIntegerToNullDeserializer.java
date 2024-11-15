package com.kh.topgunFinal.advice;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;


//JSON 내부에 빈 문자열을 null로 치환하는 도구(등록 필요 없고 DTO, VO에 선언)
public class JsonEmptyIntegerToNullDeserializer extends JsonDeserializer<Integer>{
	@Override
	public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		//JsonParser가 @JsonDeserialize가 붙은 필드 혹은 클래스의 내용을 읽는다
		String value = p.getText();
		if(value == null) return null;
		if(value.isEmpty()) return null;
		return Integer.valueOf(value);
	}
}
