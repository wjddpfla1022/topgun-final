package com.kh.topgunFinal.controller;

import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kh.topgunFinal.service.TokenService;

@Controller
public class MessageController {

	@Autowired
	private TokenService tokenService;
	
}
