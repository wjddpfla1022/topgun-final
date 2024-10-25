package com.kh.topgunFinal.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.kh.topgunFinal.dto.SeatsDto;
import com.kh.topgunFinal.service.CreateSeatService;

@Repository
public class SeatsDao {

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private CreateSeatService createSeatService;

	@Value("${custom.seat.row}")
	private int seatRow;

	@Value("${custom.seat.col}")
	private int seatCol;

	// 조회
	public List<SeatsDto> selectList() {
		System.out.println(sqlSession.selectList("seats.list"));
		return sqlSession.selectList("seats.list");
	}
	
	// 조건 리스트 조회(FlightId로 좌석을 조회해야한다)
	public List<SeatsDto> selectListByFlightId(int flightId){
		System.out.println(sqlSession.selectList("seats.listByFlightId"));
		return sqlSession.selectList("seats.listByFlightId", flightId);
	}

	// 좌석 조회
	public SeatsDto selectOne(int seatsNo) {
		return sqlSession.selectOne("seats.selectOne", seatsNo);
	}

	// 좌석 생성
	public void insertList(int flightId) {
	    List<SeatsDto> list = createSeatService.createList(seatRow, seatCol, flightId);
	    
	    for (SeatsDto seat : list) {
	        sqlSession.insert("seats.insert", seat);  // 각 좌석을 개별적으로 삽입
	    }
	}
}
