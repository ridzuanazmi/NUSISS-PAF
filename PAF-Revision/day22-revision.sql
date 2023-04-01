create database day22workshop;

use day22workshop;

create table rsvp (
	id					int not null auto_increment,
    full_name			varchar(100) not null,
    email				varchar(100) not null,
    phone				varchar(26) not null,
    confirmation_date	date not null,
    comments			text,
    constraint			rsvp_pk primary key(id)
);

insert into rsvp (full_name, email, phone, confirmation_date, comments) 
values ('Mahesan Elangovan', 'mahesan@gmail.com', '98745632', '2023-02-01', 'sorry for party rockin');
insert into rsvp (full_name, email, phone, confirmation_date, comments) 
values ('Matt Damon', 'mattD@gmail.com', '69853214', '2023-01-01', 'pizza baus');
insert into rsvp (full_name, email, phone, confirmation_date, comments) 
values ('Fred Flintstone', 'stoney@gmail.com', '87632145', '2022-12-01', 'rock bottom');
insert into rsvp (full_name, email, phone, confirmation_date, comments) 
values ('Cubanos Habanos', 'cubbans@gmail.com', '85693214', '2022-12-11', 'Cigar anyone?');
insert into rsvp (full_name, email, phone, confirmation_date, comments) 
values ('Greg Hilman', 'ghills@gmail.com', '84563987', '2023-01-25', 'here for a good time');
select * from rsvp;
select * from rsvp where full_name like '%mahesan%';
select * from rsvp where email like 'mahesan@gmail.com';
update rsvp set full_name = 'mahesan', email = 'mahesan@gmail.com', phone = '89652365', confirmation_date = '2020-03-20', comments = 'pokemon' where id = 'gengar@gmail.com';
delete from rsvp where id = ?;