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
	public List<SeatsDto> selectList(int flightId) {
	    return sqlSession.selectList("seats.selectSeatsListByFlightId", flightId);
	}

//	// 좌석 조회
//	public SeatsDto selectOne(int seatsNo) {
//		return sqlSession.selectOne("seats.selectOne", seatsNo);
//	}

	// 좌석 생성
	public void insertList(int flightId) {
	    List<SeatsDto> list = createSeatService.createList(seatRow, seatCol, flightId);
	    System.out.println("좌석 서비스에서 나온 리스트 " + list);
	    for (SeatsDto seat : list) {
	        sqlSession.insert("seats.insert", seat);  // 각 좌석을 개별적으로 삽입
	    }
	}
}
