package com.kh.topgunFinal.restcontroller;

import org.springframework.web.bind.annotation.RestController;

import com.kh.topgunFinal.dao.UserDao;
import com.kh.topgunFinal.dto.MemberDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	
	@Autowired
	private UserDao userDao;
	
	
	@PostMapping("/login")
	public void login(@RequestBody MemberDTO memberDTO) {
		
	}
	
	
	
	
}
