package com.kh.topgunFinal.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.topgunFinal.dao.RoomMessageDao;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/room")
public class RoomMessageRestController {

	@Autowired
	private RoomMessageDao roomMessageDao;
	
	@DeleteMapping("/chat/{roomMessageNo}")
	public void delete(@PathVariable int roomMessageNo) {
		roomMessageDao.delete(roomMessageNo);
	}
}
