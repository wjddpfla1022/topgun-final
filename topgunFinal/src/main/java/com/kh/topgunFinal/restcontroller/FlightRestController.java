package com.kh.topgunFinal.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.topgunFinal.dao.FlightDao;
import com.kh.topgunFinal.dto.FlightDto;
import com.kh.topgunFinal.vo.FlightVO;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/flight")
public class FlightRestController {

	@Autowired
	private FlightDao flightDao;
	
	@GetMapping("/")
	public List<FlightVO> list() {
		return flightDao.selectList();
	}
	 
	@GetMapping("/column/{column}/keyword/{keyword}")
	public List<FlightDto> search(
	        @PathVariable String column, @PathVariable String keyword) {
	    List<FlightDto> list = flightDao.search(column, keyword);
	    return list;
	}
	
	@GetMapping("/{flightId}")
	public FlightDto detail(@PathVariable int flightId) {
		return flightDao.selectOne(flightId);
	}
	
	@DeleteMapping("/{flightId}")
	public void delete(@PathVariable int flightId) {
		flightDao.delete(flightId);
	}
	
	@PostMapping("/")
	public void insert(@RequestBody FlightDto flightDto) {
		flightDao.insert(flightDto);
	}
	
	@PutMapping("/")
	public void update(@RequestBody FlightDto flightDto) {
		flightDao.update(flightDto);
	}
	
}
