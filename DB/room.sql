drop sequence room_seq;
create sequence room_seq;
drop table room cascade constraint;
create table room (
room_no number primary key,
room_name varchar2(300) not null,
room_created timestamp default systimestamp not null,
room_created_by varchar2(20) not null
);
