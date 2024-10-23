package com.kh.topgunFinal.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.topgunFinal.dto.PaymentDetailDto;
import com.kh.topgunFinal.dto.PaymentDto;
import com.kh.topgunFinal.vo.PaymentTotalVO;

@Repository
public class PaymentDao {
    @Autowired
    private SqlSession sqlSession;

    public int paymentSequence(){
        return sqlSession.selectOne("payment.paymentSequence");
    }
    public int paymentDetailSequence(){
        return sqlSession.selectOne("payment.paymentDetailSequence");
    }
    public void paymentInsert(PaymentDto paymentDto){
        sqlSession.insert("payment.paymentInsert", paymentDto);
    }
    public void paymentDetailInsert(PaymentDetailDto paymentDetailDto){
        sqlSession.insert("payment.paymentDetailInsert", paymentDetailDto);
    }
	public List<PaymentDto> selectList(String userId) {
		return sqlSession.selectList("payment.list", userId);
	}
	public PaymentDto selectOne(int paymentNo) {
		return sqlSession.selectOne("payment.find", paymentNo);
	}
	public List<PaymentDetailDto> selectDetailList(int paymentNo){
		return sqlSession.selectList("payment.findDetail", paymentNo);
	}
	public List<PaymentTotalVO> selectTotalList(String userId){
		return sqlSession.selectList("payment.findTotal", userId);
	}
}
