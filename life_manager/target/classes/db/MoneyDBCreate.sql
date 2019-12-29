CREATE DATABASE life_management;
USE life_management;
CREATE TABLE money_manager(id int primary key auto_increment, date_consumed varchar(20) not null
, year int, month int, day int, weekday int, amount decimal(6,2) not null, item varchar(40) not null, shop_name varchar(40), category varchar(20),
payment varchar(20), notes varchar(100));

CREATE TABLE t_user (id int primary key auto_increment, username varchar(20) not null
, password varchar(40) not null, salt varchar(40), email varchar(30), is_delete int, 
created_user varchar(20), created_time date, modified_user varchar(20), modified_time date);