package com.kh.topgunFinal.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.topgunFinal.dto.AttachDto;

@Repository
public class AttachDao {
    
	@Autowired
    private SqlSession session;


    // 다음 시퀀스 값을 가져오는 메서드
    public Integer sequence() {
        return session.selectOne("attach.selectNextVal");
    }

    // AttachDto 객체를 데이터베이스에 삽입하는 메서드
    public void insert(AttachDto attachDto) {
    	session.insert("attach.insert", attachDto);
    }

    // 특정 attachment_no에 해당하는 AttachDto 객체를 가져오는 메서드
    public AttachDto selectOne(int attachNo) {
        return session.selectOne("attach.selectOne", attachNo);
    }

    // 특정 attachment_no에 해당하는 데이터를 삭제하는 메서드
    public boolean delete(int attachNo) {
        return session.delete("attach.delete", attachNo) > 0;
    }
}
