-- ---------------------------- Day 24 Transaction --------------------------------------------------
create database day24lecture;

use day24lecture;

create table bank_account(
	id				int not null auto_increment,
    full_name		varchar(128) not null,
    is_active		boolean default true,
    acct_type		varchar(64),
    balance			float default 0.0,
    constraint		bank_account_pk primary key (id)
);

insert into bank_account(full_name, is_active, acct_type, balance) values ('Mahesan Elangovan', true, 'premium', 1000000);
insert into bank_account(full_name, is_active, acct_type, balance) values ('Irwan Salim', true, 'premium', 1000000);
insert into bank_account(full_name, is_active, acct_type, balance) values ('Yeo Shi Yi', true, 'premium', 1000000);

select * from  bank_account;

create table book (
	id				int not null auto_increment,
    title			varchar(128) not null,
    quantity		int not null,
    constraint		book_pk primary key (id)
);

create table resv_details (
	id				int not null auto_increment,
    book_id			int not null,
    resv_id			int not null,
    constraint		resv_details_pk primary key (id),
    constraint		resv_details_book_fk foreign key (book_id) references book(id),
    constraint		resv_details_resv_fk foreign key (resv_id) references resv(id)
);

create table resv (
	id				int not null auto_increment,
    resv_date		date not null,
    full_name		varchar(128) not null,
    constraint		resv_pk primary key (id)
);

INSERT INTO book (title) VALUES
('The Great Gatsby'),
('To Kill a Mockingbird'),
('Pride and Prejudice'),
('1984'),
('Animal Farm');
-- AND() function to generate a random decimal number between 0 and 1, 
-- then multiplies it by 5 using the FLOOR() function to round the result down to the nearest integer
UPDATE book SET quantity = FLOOR(1 + RAND() * 5) where id = 1;
UPDATE book SET quantity = FLOOR(1 + RAND() * 5) where id = 2;
UPDATE book SET quantity = FLOOR(1 + RAND() * 5) where id = 3;
UPDATE book SET quantity = FLOOR(1 + RAND() * 5) where id = 4;
UPDATE book SET quantity = FLOOR(1 + RAND() * 5) where id = 5;

select * from book;