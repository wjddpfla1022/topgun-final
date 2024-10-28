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
