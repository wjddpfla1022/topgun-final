--채팅방 참가자 테이블
drop table room_member;
create table room_member(
room_no references room(room_no) on delete cascade,
users_id references users(users_id) on delete cascade,
primary key(room_no, users_id)
);
