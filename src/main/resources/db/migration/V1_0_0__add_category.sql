create table category
(
    category_id bigint not null auto_increment,
    name        varchar(255),
    parent_id   bigint,
    primary key (category_id)
);