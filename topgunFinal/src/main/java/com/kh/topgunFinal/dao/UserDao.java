package com.kh.topgunFinal.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kh.topgunFinal.dto.AirlineDto;
import com.kh.topgunFinal.dto.MemberDto;
import com.kh.topgunFinal.dto.UserDto;
import com.kh.topgunFinal.vo.InfoResponseVO;

@Repository
public class UserDao {

	@Autowired
	private SqlSession session;

	@Autowired
	private PasswordEncoder encoder;

	// 좀 더 추가할지 말지 논의
	private final String regex = "^(?=.*[0-9])(?=.*[!@#$])(?=.*[A-Z]).{8,}$";

	public UserDto selectOne(String memberId) {
		return session.selectOne("Users.find", memberId);
	}

	// 회원가입 ->
	// [1] 일반 회원
	// [2] 항공사
	// [3] 관리자 -> 관리자는 하드코딩으로 데이터 집어넣기 할것.
	@Transactional
	public void insertMember(UserDto userDto, MemberDto memberDto) {

		// 암호화
		String rawPw = userDto.getUsersPassword();

		boolean isPwVaild = rawPw.matches(regex);

		// 비밀번호가 정규 표현식에 맞는지 검사
		if (!isPwVaild) {
			throw new IllegalArgumentException("비밀번호는 숫자와 특수 문자를 포함해야 합니다.");
		}

		String encPw = encoder.encode(rawPw);
		userDto.setUsersPassword(encPw);

		session.insert("Users.insert", userDto);
		if (userDto.getUsersType().equals("MEMBER")) {
			session.insert("Users.memberInsert", memberDto);
		}
	}

	@Transactional
	public void insertAirLine(UserDto userDto, AirlineDto airlineDto) {

		// 암호화
		String rawPw = userDto.getUsersPassword();

		boolean isPwVaild = rawPw.matches(regex);

		// 비밀번호가 정규 표현식에 맞는지 검사
		if (!isPwVaild) {
			throw new IllegalArgumentException("비밀번호는 숫자와 특수 문자를 포함해야 합니다.");
		}

		String encPw = encoder.encode(rawPw);
		userDto.setUsersPassword(encPw);

		session.insert("Users.insert", userDto);
		if (userDto.getUsersType().equals("AIRLINE")) {
			session.insert("Users.airlineInsert", airlineDto);
		}
	}

	public InfoResponseVO getMyInfo(String userId, String userType) {
		// 맵에 파라미터 추가
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("userType", userType);

		return session.selectOne("Users.findInfo", params);
	}

	@Transactional
	public boolean updateInfo(InfoResponseVO infoVo) {
		if (infoVo == null) {
			return false;
		}

		// 첫 번째 테이블 업데이트
		int result1 = session.update("Users.updateInfo", infoVo);

		// 두 번째 테이블 업데이트
		int result2 = 0;

		switch (infoVo.getUsersType()) {
		case "MEMBER":
			result2 = session.update("Users.updateMember", infoVo);
			break;
		case "ADMIN":
			// ADMIN에 대한 업데이트가 필요 없다면, 1로 설정하여 성공 처리
			result2 = 1;
			break;
		case "AIRLINE":
			result2 = session.update("Users.updateAirline", infoVo); // 항공사 업데이트 메서드
			break;
		default:
			return false;
		}

		// 두 업데이트 모두 성공한 경우에만 true 반환
		return result1 > 0 && result2 > 0;
	}

	public boolean updateUserPw(String userId, String tempPassword) {
		// 맵에 파라미터 추가
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		String enPw = encoder.encode(tempPassword);
		params.put("tempPassword", enPw);
		return session.update("Users.updateUserPw", params) > 0;

	}
}
