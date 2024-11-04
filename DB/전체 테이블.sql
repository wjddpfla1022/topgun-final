--채팅방 참가자 테이블
drop table room_member;
create table room_member(
room_no references room(room_no) on delete cascade,
users_id references users(users_id) on delete cascade,
primary key(room_no, users_id)
);

-- 채팅방 메세지 테이블
drop sequence room_message_seq;
create sequence room_message_seq;
drop table room_message;
create table room_message(
room_message_no number primary key,
room_message_type varchar2(10) not null,
room_message_sender references users(users_id) on delete set null,
room_message_receiver references users(users_id) on delete set null,
room_message_content varchar2(300) not null,
room_message_time timestamp default systimestamp not null,
room_no references room(room_no) on delete cascade,
check(room_message_type in ('chat','dm','system')),
check(room_message_sender != room_message_receiver)
);

-- 채팅방 테이블
drop sequence room_seq;
create sequence room_seq;
drop table room cascade constraint;
create table room (
room_no number primary key,
room_name varchar2(300) not null,
room_created timestamp default systimestamp not null,
room_created_by varchar2(20) not null
);

--사용자 테이블
DROP sequence users_seq;
CREATE sequence users_seq;
drop table users;
CREATE TABLE Users (
    users_id         varchar2(20)     primary key   NOT NULL,
    users_name       varchar2(20)        NOT NULL,
    users_pw    varchar2(16)       NOT NULL,
    users_email      varchar2(60)        NOT NULL,
    users_contact    char(11)            NOT NULL,
    user_type        varchar2(21)        DEFAULT 'MEMBER' NOT NULL,
    CHECK(user_type IN ('MEMBER', 'AIRLINE', 'ADMIN')),
    CHECK(regexp_like(users_id, '^[a-z][a-z0-9]{4,19}$')),
    CHECK(
        regexp_like(users_pw, '^[A-Za-z0-9!@#$]{8,16}$')
        AND regexp_like(users_pw, '[A-Z]+')
        AND regexp_like(users_pw, '[a-z]+')
        AND regexp_like(users_pw, '[0-9]+')
        AND regexp_like(users_pw, '[!@#$]+')
    ),
    CHECK(regexp_like(users_contact, '^010[1-9][0-9]{6,7}$'))
);

---항공편 테이블
drop sequence flight_seq;
create sequence flight_seq;
DROP TABLE flight;
CREATE TABLE Flight (
    flight_id NUMBER NOT NULL PRIMARY KEY,
    flight_number VARCHAR2(255) NOT NULL,
    departure_time Timestamp NOT NULL,
    arrival_time Timestamp NOT NULL,
    flight_time VARCHAR2(255) NOT NULL,
    departure_airport VARCHAR2(255) NOT NULL,
    arrival_airport VARCHAR2(255) NOT NULL,
    user_id VARCHAR2(255) NOT NULL,
    flight_price NUMBER NOT NULL,
    flight_status CHAR(6) DEFAULT '대기'
);

--항공사 테이블
drop sequence airline_seq;
create sequence airline_seq; 
drop table airline;
create table airline(
users_id references users(users_id) on delete cascade,
airline_name varchar(255) not null,
airline_no varchar(255) not null
);

--좌석결제대표
CREATE SEQUENCE payment_seq;
CREATE TABLE payment (
    payment_no NUMBER PRIMARY KEY,
    payment_tid CHAR(20) NOT NULL,
    payment_name varchar2(255) NOT NULL,
    payment_total NUMBER NOT NULL,
    payment_remain NUMBER NOT NULL,
    user_id VARCHAR2(255) REFERENCES member(member_id)ON DELETE SET NULL,
    payment_time TIMESTAMP DEFAULT SYSDATE NOT NULL,
    flight_Id NUMBER NOT NULL
);

--좌석결제상세
CREATE SEQUENCE payment_detail_seq;
CREATE TABLE payment_detail (
    payment_detail_no NUMBER PRIMARY KEY,
    flight_id NUMBER NOT NULL,
    payment_detail_name VARCHAR2(255) NOT NULL,
    payment_detail_price NUMBER NOT NULL,
    payment_detail_qty NUMBER NOT NULL,
    payment_detail_seatsNo NUMBER NOT NULL,
    payment_detail_origin NUMBER REFERENCES payment(payment_no) ON DELETE CASCADE,
    payment_detail_status CHAR(6) NOT NULL,
    payment_detail_passport VARCHAR2(9),
    payment_detail_passanger VARCHAR2(90),
    payment_detail_english VARCHAR2(25),
    payment_detail_sex CHAR(1),
    payment_detail_birth DATE,
    payment_detail_country VARCHAR2(30),
    payment_detail_visa VARCHAR2(30),
    payment_detail_expire DATE,
    CHECK (payment_detail_status IN ('승인', '취소')),  -- CHECK 제약 조건 추가
    FOREIGN KEY (flight_id) REFERENCES FLIGHT(FLIGHT_ID),  -- FLIGHT_ID 외래 키 설정
    FOREIGN KEY (payment_detail_seatsNo, flight_id) REFERENCES SEATS(SEATS_NO, FLIGHT_ID),  -- 복합 외래 키 설정
    UNIQUE (flight_id, payment_detail_seatsNo, payment_detail_no, payment_detail_origin)-- 3개 모두 충족되면 중복처리로 인식
);

--결제 tid 시퀀스
CREATE SEQUENCE payService_seq;

-- trigger 결제취소 되면 좌석 미사용으로 변경
CREATE OR REPLACE TRIGGER update_seat_status
AFTER UPDATE OF payment_detail_status ON payment_detail
FOR EACH ROW
BEGIN
    IF :NEW.payment_detail_status = '취소' THEN
        UPDATE SEATS
        SET SEATS_STATUS = '미사용'
        WHERE SEATS_NO = :OLD.payment_detail_seatsNo
        AND FLIGHT_ID = :OLD.flight_id;
    END IF;
END;

--좌석
CREATE TABLE SEATS (
    SEATS_NO NUMBER,
    SEATS_RANK VARCHAR2(15) NOT NULL,
    SEATS_NUMBER VARCHAR2(3) NOT NULL,
    SEATS_PRICE NUMBER DEFAULT 0 NOT NULL,
    SEATS_STATUS VARCHAR2(9) DEFAULT '미사용',
    FLIGHT_ID NUMBER,  -- FLIGHT_ID 추가
    CHECK (SEATS_RANK IN ('비즈니스', '이코노미')),
    CHECK (SEATS_STATUS IN ('사용', '미사용')),
    PRIMARY KEY (SEATS_NO, FLIGHT_ID),  -- 복합 기본 키 설정
    FOREIGN KEY (FLIGHT_ID) REFERENCES FLIGHT(FLIGHT_ID)  -- 외래 키 설정
);

--공지사항 테이블 
CREATE SEQUENCE NOTICE_SEQ
START WITH 1 -- 시작 번호
INCREMENT BY 1; -- 증가 값
drop SEQUENCE NOTICE_SEQ;
drop table notice;
CREATE TABLE NOTICE (
    NOTICE_ID NUMBER PRIMARY KEY,                     -- 공지사항 ID (수동 증가)
    NOTICE_TITLE VARCHAR2(255) NOT NULL,              -- 공지 제목
    NOTICE_CONTENT CLOB NOT NULL,                      -- 공지 내용
    NOTICE_CREATED_AT VARCHAR2(100) NOT NULL,         -- 작성일시
    MAIN_NOTICE NUMBER(1) DEFAULT 0,                  -- 주요 공지 여부 (0 = 일반 공지, 1 = 주요 공지)
    URGENT_NOTICE NUMBER(1) DEFAULT 0,                -- 긴급 공지 여부 (0 = 일반 공지, 1 = 긴급 공지)
    MODIFIED_NOTICE NUMBER(1) DEFAULT 0,              -- 수정 여부 
    CONSTRAINT FK_NOTICE_AUTHOR FOREIGN KEY (NOTICE_AUTHOR) REFERENCES USERS(user_id) -- 외래 키 제약 조건
);

--공지사항 트리거
CREATE OR REPLACE TRIGGER NOTICE_ID_TRIGGER
BEFORE INSERT ON NOTICE
FOR EACH ROW
BEGIN
    :NEW.NOTICE_ID := NOTICE_SEQ.NEXTVAL; -- 시퀀스를 통해 NOTICE_ID 자동 설정
END;
