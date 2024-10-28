package com.kh.topgunFinal.service;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kh.topgunFinal.dao.CertDao;
import com.kh.topgunFinal.dao.UserDao;
import com.kh.topgunFinal.dto.CertDto;
import com.kh.topgunFinal.dto.UserDto;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender sender;

	@Autowired
	private RandomService randomService;

	@Autowired
	private CertDao certDao;

	@Autowired
	private UserDao userDao;

	// 비밀번호 재설정 메일 발송 기능
	public void sendResetPw(String userId, String userEmail) throws IOException, MessagingException {

		// 사용자 정보 가져오기
		UserDto userDto = userDao.selectOne(userId);
		if (userDto.getUsersId().equals(userId) && userDto.getUsersEmail().equals(userEmail)) {
			// 이메일 템플릿 불러와 정보 설정 후 발송
			ClassPathResource resource = new ClassPathResource("templates/reset-pw.html");
			File target = resource.getFile();
			Document document = Jsoup.parse(target);

			Element userIdWrapper = document.getElementById("user-id");
			userIdWrapper.text(userId);

			// 돌아올 링크 주소를 생성하는 코드
			

			// - 인증번호 생성
			String certNumber = randomService.generateNumber(6);
			certDao.delete(userEmail);
			CertDto certDto = new CertDto();
			certDto.setCertEmail(userEmail);
			certDto.setCertNumber(certNumber);
			certDao.insert(certDto);
			
			
			// URI 인코드
			String encodedCertNumber = URLEncoder.encode(certNumber, StandardCharsets.UTF_8.toString());
	        String encodedUserEmail = URLEncoder.encode(userEmail, StandardCharsets.UTF_8.toString());
	        String encodedUserId = URLEncoder.encode(userId, StandardCharsets.UTF_8.toString());

			String url = ServletUriComponentsBuilder
//					.fromCurrentContextPath()//http://localhost:8080
					.fromHttpUrl("http://localhost:3000") // 원하는 호스트와 포트를 설정
					.path("/resetPw")// 나머지경로
					.queryParam("certNumber", encodedCertNumber)// 파라미터
					.queryParam("certEmail", encodedUserEmail)// 파라미터
					.queryParam("userId", encodedUserId)// 파라미터
					.build().toUriString();// 문자열변환

			Element resetUrlWrapper = document.getElementById("reset-url");
			resetUrlWrapper.attr("href", url);

			// 메세지 생성
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
			helper.setTo(userEmail);
			helper.setSubject("[TOPGUN] 비밀번호 재설정 안내");
			helper.setText(document.toString(), true);

			// 전송
			sender.send(message);
		}
		else
			// 사용자 정보가 일치하지 않는 경우 예외 발생
		    throw new IllegalArgumentException("사용자 정보가 일치하지 않습니다.");
	}
}
