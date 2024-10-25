package com.kh.topgunFinal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.kh.topgunFinal.dto.SeatsDto;

@Service
public class CreateSeatService {

    public List<SeatsDto> createList(int seatRow, int seatCol, int flightId) {

        Random random = new Random();

        // seatList를 ArrayList로 초기화
        List<SeatsDto> seatList = new ArrayList<>();

        for (int row = 1; row <= seatRow; row++) {
            for (int col = 1; col <= seatCol; col++) {
                SeatsDto seat = new SeatsDto();
                
                seat.setFlightId(flightId);

                seat.setSeatsNo((row - 1) * seatCol + col); // 좌석 번호 계산 (고유 번호)

                // row 값을 알파벳으로 변환 ('A'는 65의 ASCII 코드)
                char rowLetter = (char) ('A' + row - 1);
                String seatNumber = rowLetter + String.valueOf(col); // "A1", "A2", ..., "B1", "B2"

                seat.setSeatsNumber(seatNumber); // 좌석 번호 설정

                // 랜덤으로 좌석 등급 결정 (50% 확률로 비즈니스/이코노미)
                String seatRank = random.nextBoolean() ? "비즈니스" : "이코노미";
                seat.setSeatsRank(seatRank); // 좌석 등급 설정

                // 좌석 가격 설정: 비즈니스 좌석이면 50,000원 추가, 기본은 0
                if ("비즈니스".equals(seatRank)) {
                    seat.setSeatsPrice(80000); // 비즈니스 좌석이면 80,000원
                } else {
                	 // 이코노미 좌석이면 기본 가격 0
                }

                // seatList에 추가
                seatList.add(seat);
            }
        }

        return seatList;
    }
}
