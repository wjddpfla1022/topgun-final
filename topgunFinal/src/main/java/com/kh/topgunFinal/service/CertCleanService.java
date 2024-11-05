package com.kh.topgunFinal.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kh.topgunFinal.configuration.CustomCertProperties;
import com.kh.topgunFinal.dao.CertDao;

@Service
public class CertCleanService {
	
	@Autowired
	private CertDao certDao;
	
	@Autowired
	private CustomCertProperties customCertProperties;
	
//	매 시 정각마다 인증번호 청소 작업을 수행
	@Scheduled(cron = "0 0 * * * *")
	public void clean() {
		certDao.clean(customCertProperties.getExpire());
	}
	
}
