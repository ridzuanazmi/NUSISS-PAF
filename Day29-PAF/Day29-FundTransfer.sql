create database day29;

use day29;

create table bank_account (
	id						int not null auto_increment,
    customer_name			varchar(256) not null,
    balance					float default '0.0',
    constraint				bank_account_pk primary key(id)
);

insert into bank_account (customer_name, balance) values ('Fred', 10000.00);
insert into bank_account (customer_name, balance) values ('John', 10000.00);

select * from bank_account;

select balance from bank_account where customer_name like 'fred';