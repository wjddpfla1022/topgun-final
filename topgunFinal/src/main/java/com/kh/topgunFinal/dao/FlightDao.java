package com.kh.topgunFinal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.topgunFinal.dto.FlightDto;
import com.kh.topgunFinal.mapper.FlightMapper;

@Repository
public class FlightDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private FlightMapper flightMapper;
	

	 public void insert(FlightDto flightDto) {
	        String sql = "INSERT INTO Flight ("
	                     + "flight_id, flight_number, departure_time, arrival_time, flight_time, "
	                     + "departure_airport, arrival_airport, user_id, flight_total_seat"
	                     + ") "
	                     + "VALUES (flight_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";

	        Object[] data = {
	            flightDto.getFlightNumber(),
	            flightDto.getDepartureTime(),
	            flightDto.getArrivalTime(),
	            flightDto.getFlightTime(),
	            flightDto.getDepartureAirport(),
	            flightDto.getArrivalAirport(),
	            flightDto.getUserId(),
	            flightDto.getFlightTotalSeat()
	        };

	        jdbcTemplate.update(sql, data);
	    }
	 
//	// 수정
//	 public boolean update(FlightDto dto) {
//	     String sql = "UPDATE Flight "
//	                  + "SET flight_number = ?, departure_time = ?, arrival_time = ?, "
//	                  + "flight_time = ?, departure_airport = ?, arrival_airport = ?, "
//	                  + "flight_total_seat = ?, flight_status = ? "
//	                  + "WHERE flight_id = ?";
//	     Object[] data = {
//	         dto.getFlightNumber(), dto.getDepartureTime(), dto.getArrivalTime(),
//	         dto.getFlightTime(), dto.getDepartureAirport(), dto.getArrivalAirport(),
//	         dto.getFlightTotalSeat(), dto.getFlightStatus(),
//	         dto.getFlightId()
//	     };
//	     return jdbcTemplate.update(sql, data) > 0;
//	 }
//
//	 // 삭제
//	 public boolean delete(int flightId) {
//	     String sql = "DELETE FROM Flight WHERE flight_id = ?";
//	     Object[] data = {flightId};
//	     return jdbcTemplate.update(sql, data) > 0;
//	 }
//
//	 // 조회
//	 public List<FlightDto> selectList() {
//	     String sql = "SELECT * FROM Flight ORDER BY flight_id ASC";
//	     return jdbcTemplate.query(sql, flightMapper);
//	 }
//
//	 // 검색
//	 public List<FlightDto> selectList(String column, String keyword) {
//	     String sql = "SELECT * FROM Flight "
//	                  + "WHERE INSTR(" + column + ", ?) > 0 "
//	                  + "ORDER BY " + column + " ASC, flight_id ASC";
//	     Object[] data = {keyword};
//	     return jdbcTemplate.query(sql, flightMapper, data);
//	 }
//
//	 // 상세
//	 public FlightDto selectOne(int flightId) {
//	     String sql = "SELECT * FROM Flight WHERE flight_id = ?";
//	     Object[] data = {flightId};
//	     List<FlightDto> list = jdbcTemplate.query(sql, flightMapper, data);
//	     return list.isEmpty() ? null : list.get(0);
//	 }
//
//	 // 비행 통계현황 조회 기능
//	 public List<StatusVO> status() {
//	     String sql = "SELECT flight_status title, COUNT(*) cnt FROM Flight "
//	                  + "GROUP BY flight_status "
//	                  + "ORDER BY cnt DESC, flight_status ASC";
//	     return jdbcTemplate.query(sql, statusMapper);
//	 }
//
//	 // 시퀀스 생성 및 등록 메소드
//	 public int sequence() {
//	     String sql = "SELECT flight_seq.NEXTVAL FROM dual";  // Adjust sequence name as needed
//	     return jdbcTemplate.queryForObject(sql, Integer.class);
//	 }
//
//	 public void insertWithSequence(FlightDto flightDto) {
//	     String sql = "INSERT INTO Flight("
//	                  + "flight_id, flight_number, departure_time, arrival_time, "
//	                  + "flight_time, departure_airport, arrival_airport, "
//	                  + "user_id, flight_total_seat, flight_status) "
//	                  + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//	     Object[] data = {
//	         flightDto.getFlightId(),
//	         flightDto.getFlightNumber(),
//	         flightDto.getDepartureTime(),
//	         flightDto.getArrivalTime(),
//	         flightDto.getFlightTime(),
//	         flightDto.getDepartureAirport(),
//	         flightDto.getArrivalAirport(),
//	         flightDto.getUserId(),
//	         flightDto.getFlightTotalSeat(),
//	         flightDto.getFlightStatus()
//	     };
//	     jdbcTemplate.update(sql, data);
//	 }
//
//	 // 연결 기능
//	 public void connect(int flightId, int attachmentNo) {
//	     String sql = "INSERT INTO flight_image(flight_id, attachment) "
//	                  + "VALUES(?, ?)";
//	     Object[] data = {flightId, attachmentNo};
//	     jdbcTemplate.update(sql, data);
//	 }
//
//	 // 이미지 번호 찾기 기능
//	 public Integer findImage(int flightId) {
//	     String sql = "SELECT attachment FROM flight_image WHERE flight_id = ?";
//	     Object[] data = {flightId};
//	     return jdbcTemplate.queryForObject(sql, Integer.class, data);
//	 }
//
//	 public FlightDto selectOneByFlightNumber(String flightNumber) {
//	     String sql = "SELECT * FROM Flight WHERE flight_number = ?";
//	     Object[] data = {flightNumber};
//	     List<FlightDto> list = jdbcTemplate.query(sql, flightMapper, data);
//	     return list.isEmpty() ? null : list.get(0);
//	 }

	}