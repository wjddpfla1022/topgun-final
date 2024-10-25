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
