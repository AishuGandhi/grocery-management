create database grocery_management;

use  grocery_management;

create table grocery(item_id int primary key auto_increment, item_name varchar(100) , quantity int, price float, constraint uitemId unique(item_id));

create table orderdetails(order_id int,
quantity_per_item int, item_id int,FOREIGN KEY (item_id) REFERENCES grocery(item_id));


create table userdetails(user_id int primary key, user_name varchar(100), 
password varchar(15),mobile varchar(12));

create table userorder(order_id int primary key,user_id int,dt_Time datetime,FOREIGN KEY 
(user_id) REFERENCES userdetails(user_id));

insert into userdetails values(1,"Aishwarya","abcxyz","9881360394");

commit;