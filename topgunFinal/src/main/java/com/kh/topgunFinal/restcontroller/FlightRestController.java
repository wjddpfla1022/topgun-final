package com.kh.topgunFinal.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.topgunFinal.dao.FlightDao;
import com.kh.topgunFinal.dto.FlightDto;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/flight")
public class FlightRestController {

	@Autowired
	private FlightDao flightDao;
	
//	@GetMapping("/")
//	public List<FlightDto> list() {
//		return flightDao.selectList();
//	}
//	
//	@DeleteMapping("/{flight}")
//	public void delete(@PathVariable int poketmonNo) {
//		poketmonDao.delete(poketmonNo);
//	}
//	
	@PostMapping("/")
	public void insert(@RequestBody FlightDto flightDto) {
		flightDao.insert(flightDto);
	}
	
//	@PutMapping("/")
//	public void update(@RequestBody FlightDto flightDto) {
//		flightDao.update(flightDto);
//	}
}
