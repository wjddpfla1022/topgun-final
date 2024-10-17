package com.kh.topgunFinal.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.kh.topgunFinal.dto.UserDto;

@Repository
public class UserDao {

	@Autowired
	private SqlSession session;

	@Autowired
	private PasswordEncoder encoder;

	public UserDto selectOne(String memberId) {
		return session.selectOne("Users.find", memberId);
	}

	// 회원가입 ->
	// [1] 일반 회원
	// [2] 항공사
	// [3] 관리자 -> 관리자는 하드코딩으로 데이터 집어넣기 할것.
	public void insert(UserDto userDto) {

		// 암호화
		String rawPw = userDto.getUsersPassword();
		String encPw = encoder.encode(rawPw);
		userDto.setUsersPassword(encPw);

		session.insert("Users.insert", userDto);
	}
}
