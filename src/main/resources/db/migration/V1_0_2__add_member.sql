create table member (
    id bigint not null auto_increment,
    addr varchar(255),
    creat_at datetime(6),
    grade varchar(255),
    member_status varchar(255),
    password varchar(255),
    phone varchar(255),
    total_price bigint not null,
    user_id varchar(255) not null,
    primary key (id)
);
