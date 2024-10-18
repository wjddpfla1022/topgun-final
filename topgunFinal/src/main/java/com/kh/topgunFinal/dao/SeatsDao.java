package com.kh.topgunFinal.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.topgunFinal.dto.SeatsDto;

@Repository
public class SeatsDao {

	@Autowired
	private SqlSession sqlSession;
	
	//조회
	public List<SeatsDto> selectList(){
		System.out.println(sqlSession.selectList("seats.list"));
		return sqlSession.selectList("seats.list");
	}

	 // 좌석 조회
    public SeatsDto selectOne(int seatsNo) {
        return sqlSession.selectOne("seats.selectOne", seatsNo);
    }
}
