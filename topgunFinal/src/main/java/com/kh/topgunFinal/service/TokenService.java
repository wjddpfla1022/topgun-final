package com.kh.topgunFinal.service;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kh.topgunFinal.configuration.TokenProperties;
import com.kh.topgunFinal.dao.UserTokenDao;
import com.kh.topgunFinal.dto.UserTokenDto;
import com.kh.topgunFinal.vo.UserClaimVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenService {

	public static final String BEARER_PREFIX = "Bearer ";

	@Autowired
	private TokenProperties tokenProperties;
	
	@Autowired
	private UserTokenDao userTokenDao;

	// 토큰 생성 메소드
	public String createAccessToken(UserClaimVO vo) {
		// 키 생성
		SecretKey key = Keys.hmacShaKeyFor(tokenProperties.getSecret().getBytes(StandardCharsets.UTF_8));

		// 만료시간 계산
		Calendar c = Calendar.getInstance();
		Date now = c.getTime();
		c.add(Calendar.MINUTE, tokenProperties.getExpire());
		Date limit = c.getTime();
		// 토큰
		return Jwts.builder().signWith(key)
				.expiration(limit) // 만료 시간 -> TokenProperties
				.issuer(tokenProperties.getIssuer())// 발행자 -> TokenProperties
				.issuedAt(now) // 발행 시간 -> 지금
				.claim("userId", vo.getUserId()) // 발급자 ID
				.claim("userType", vo.getUserType()) // 발급자 Type
			.compact(); // 생성

	}

	// 리프레시 토큰 생성
	public String createRefreshToken(UserClaimVO vo) {
		// 키 생성
		SecretKey key = Keys.hmacShaKeyFor(tokenProperties.getSecret().getBytes(StandardCharsets.UTF_8));
		// 만료시간 계산
		Calendar c = Calendar.getInstance();
		Date now = c.getTime();
		c.add(Calendar.MONTH, 1);
		Date limit = c.getTime();

		// 토큰
		String token = Jwts.builder()
				.signWith(key)
				.expiration(limit) // 만료시간 -> 1달
				.issuer(tokenProperties.getIssuer())
				.issuedAt(now).claim("userId", vo.getUserId()) // 발급자 ID
				.claim("userType", vo.getUserType()) // 발급자 Type
			.compact(); // 생성

		// DB 저장
		UserTokenDto userTokenDto = new UserTokenDto();
		userTokenDto.setTokenTarget(vo.getUserId());
		userTokenDto.setTokenValue(token);
		userTokenDao.insert(userTokenDto);

		return token;
	}

	// 토큰 검증 메소드
	public UserClaimVO check(String token) {
		// 키 생성
		SecretKey key = Keys.hmacShaKeyFor(tokenProperties.getSecret().getBytes(StandardCharsets.UTF_8));
		// 토큰 해석
		Claims claims = (Claims) Jwts.parser().verifyWith(key).requireIssuer(tokenProperties.getIssuer()).build()
				.parse(token).getPayload();
		// 결과 생성 및 반환
		UserClaimVO vo = new UserClaimVO();
		vo.setUserId((String) claims.get("userId"));
		vo.setUserType((String) claims.get("userType"));
		return vo;
	}
	
	//Bearer 토큰인지 검사하는 메소드
	public boolean isBearerToken(String token) {
		return token != null && token.startsWith(BEARER_PREFIX);
	}
	
	//Bearer 를 제거하는 메소드
	public String removeBearer(String token) {
		//return token.substring(7);
		return token.substring(BEARER_PREFIX.length());
	}
	
	
	//(옵션 토큰 정리 메소드)
	@Scheduled(cron = "0 0 0 * * *")
	public void clearToken() {
		userTokenDao.clear();
	}
}
