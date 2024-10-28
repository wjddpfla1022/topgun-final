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

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

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

	// 인증메일 발송 서비스(마임메세지)
	public void sendCert2(String userEmail) throws MessagingException, IOException {
		System.out.println(userEmail);
		// 인증번호 생성
		String value = randomService.generateNumber(6);

		// 메세지 생성
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
		helper.setTo(userEmail);
		helper.setSubject("[KH정보교육원] 인증번호 안내");

		ClassPathResource resource = new ClassPathResource("templates/cert.html");
		File target = resource.getFile();
		Document document = Jsoup.parse(target, "UTF-8");
		Element number = document.getElementById("cert-number");
		number.text(value);// OTP 번호를 텍스트로 설정

		helper.setText(document.toString(), true);

		// 메세지 전송
		sender.send(message);

		// DB 기록 남기기
		certDao.delete(userEmail);
		CertDto certDto = new CertDto();
		certDto.setCertEmail(userEmail);
		certDto.setCertNumber(value);
		certDao.insert(certDto);
	}

	// 임시 비밀번호 발급 및 메일 전송
	public void sendTempPw(String userId, String userEmail) throws IOException, MessagingException {
		// 임시 비밀번호 발급
		String tempPassword = randomService.generateString(12);
		userDao.updateUserPw(userId, tempPassword);

		// 이메일 템플릿 불러와 정보 설정 후 발송
		ClassPathResource resource = new ClassPathResource("templates/temp-pw.html");
		File target = resource.getFile();
		Document document = Jsoup.parse(target);
		Element element = document.getElementById("temp-password");
		element.text(tempPassword);

		// 메세지 생성
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
		helper.setTo(userEmail);
		helper.setSubject("[KH정보교육원] 임시 비밀번호 안내");
		helper.setText(document.toString(), true);

		// 메세지 발송
		sender.send(message);
	}

	// 비밀번호 재설정 메일 발송 기능
	public void sendResetPw(String userId, String userEmail) throws IOException, MessagingException {

		// 이메일 템플릿 불러와 정보 설정 후 발송
		ClassPathResource resource = new ClassPathResource("templates/reset-pw.html");
		File target = resource.getFile();
		Document document = Jsoup.parse(target);

		Element memberIdWrapper = document.getElementById("member-id");
		memberIdWrapper.text(userId);

		// 돌아올 링크 주소를 생성하는 코드

		// - 인증번호 생성
		String certNumber = randomService.generateNumber(6);
		certDao.delete(userEmail);
		CertDto certDto = new CertDto();
		certDto.setCertEmail(userEmail);
		certDto.setCertNumber(certNumber);
		certDao.insert(certDto);

		String url = ServletUriComponentsBuilder
//				.fromCurrentContextPath()//http://localhost:8080
				.fromHttpUrl("http://localhost:3000") // 원하는 호스트와 포트를 설정
				.path("/resetPw")// 나머지경로
				.queryParam("certNumber", certNumber)// 파라미터
				.queryParam("certEmail", userEmail)// 파라미터
				.queryParam("userId", userId)// 파라미터
				.build().toUriString();// 문자열변환

		Element resetUrlWrapper = document.getElementById("reset-url");
		resetUrlWrapper.attr("href", url);

		// 메세지 생성
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
		helper.setTo(userEmail);
		helper.setSubject("[KH정보교육원] 비밀번호 재설정 안내");
		helper.setText(document.toString(), true);

		// 전송
		sender.send(message);
	}
}
