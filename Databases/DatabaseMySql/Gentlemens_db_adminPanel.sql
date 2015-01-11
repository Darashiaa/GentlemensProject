drop database if exists test;
create database test; 
use test;

DROP user eddy;
CREATE USER eddy IDENTIFIED BY '0000'; 

grant usage on *.* to sqluser@localhost identified by '0000'; 
grant all privileges on test.* to sqluser@localhost; 

drop table if exists userLogin ;
drop table if exists price;
drop table if exists Product;

create table userLogin (
  userid int auto_increment,
  username varchar(50),
  userpassword varchar(20),
  primary key(userid)
);


INSERT INTO userLogin (username,userpassword) VALUES ('Sharelison','Sharelison');

create table product (
 productname varchar(300),
 price decimal(9,2),
 link varchar(350),
 photo varchar (350),
 clicked int,
 primary key(link)
);

create table price (
    price decimal, 
    linkproduct varchar (250),
	currenttime timestamp,
	foreign key (linkproduct) references product (link),
	primary key (linkproduct, currenttime)
);
