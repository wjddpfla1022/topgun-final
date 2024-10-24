-- 인증 테이블
create table cert (
cert_email varchar2(60) primary key,
cert_number char(6) not null,
cert_time date default sysdate not null
);
