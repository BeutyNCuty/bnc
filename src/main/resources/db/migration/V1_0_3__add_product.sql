create table product (
    dtype varchar(31) not null,
    product_id bigint not null auto_increment,
    brand varchar(255),
    name varchar(255),
    price integer not null,
    product_status varchar(255),
    stock integer not null,
    accessory varchar(255),
    bag_size varchar(255),
    clothes_size varchar(255),
    shoes_size varchar(255),
    primary key (product_id)
);