package com.kh.topgunFinal.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.topgunFinal.dto.UserTokenDto;

@Repository
public class UserTokenDao {
	
	@Autowired
	private SqlSession session;
	
	// 리프레시 토큰 저장
	public void insert(UserTokenDto userTokenDto) {
		session.insert("userToken.insert", userTokenDto);
	}
	
	// 리프레시 토큰 조회
	public UserTokenDto selectOne(UserTokenDto userTokenDto) {
		return session.selectOne("userToken.check",userTokenDto);
	}
	
	// 리프레시 토큰 삭제
	public boolean delete(UserTokenDto userTokenDto) {
		return session.delete("userToken.delete", userTokenDto) > 0;
	}
	
	// 리프레시 토큰 청소?
	public int clear() {
		return session.delete("userToken.clear");
	}
}
