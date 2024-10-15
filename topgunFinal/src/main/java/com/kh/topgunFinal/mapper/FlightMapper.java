package com.kh.topgunFinal.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.kh.topgunFinal.dto.FlightDto;

@Service
public class FlightMapper implements RowMapper<FlightDto>{

	@Override
	public FlightDto mapRow(ResultSet rs, int rowNum) throws SQLException {
	    FlightDto flightDto = new FlightDto();
	    flightDto.setFlightId(rs.getInt("flight_no"));
	    flightDto.setFlightNumber(rs.getString("flight_number"));
	    flightDto.setDepartureTime(rs.getString("departure_time"));
	    flightDto.setArrivalTime(rs.getString("arrival_time"));
	    
	    // LocalDateTime Timstamp로 적용 수정예정
	    flightDto.setFlightTime(rs.getTimestamp("flight_time").toLocalDateTime());
	    flightDto.setDepartureAirport(rs.getString("departure_airport"));
	    flightDto.setArrivalAirport(rs.getString("arrival_airport"));
	    flightDto.setUserId(rs.getString("user_id"));
	    flightDto.setFlightTotalSeat(rs.getString("flight_total_seat"));
	    flightDto.setFlightStatus(rs.getString("flight_status"));
	    
	    return flightDto;
	}

}
