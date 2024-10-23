package com.kh.topgunFinal.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.topgunFinal.dto.CertDto;

@Repository
public class CertDao {

	@Autowired
	private SqlSession session;

	// 생성
	public void insert(CertDto certDto) {
		session.insert("cert.insert", certDto);
	}

	// 삭제
	public boolean delete(String certEmail) {
		return session.delete("cert.delete", certEmail) > 0;
	}
	
	// 이메일과 인증번호가 유효한지 검사하는 기능
	public boolean check(CertDto certDto, int duration) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("certEmail", certDto.getCertEmail());
	    params.put("certNumber", certDto.getCertNumber());
	    params.put("duration", duration);
	    
	    return session.selectList("cert.check", params).size() > 0;
	}


	// 유효시간이 지난 인증번호를 삭제하도록 구현
	public boolean clean(int minute) {
		return session.delete("cert.clean", minute) > 0;
	}

}
