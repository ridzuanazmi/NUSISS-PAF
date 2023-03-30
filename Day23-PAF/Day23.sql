-- --------------------------------------------------------------------
-- ------------------LEISURE SCHEMAS CODES ----------------------------
-- --------------------------------------------------------------------
use leisure;

select distinct first_name, last_name from customer;

select count(*) from customer where first_name like 'Mahesan';

select * from room;

select count(*) from room;

select * from room where id = 1;

delete from room where id = 1;

insert into room (room_type, price) values ('deluxe', 250);

update room set price = 450 where id = 2; 

-- ------------------------------- DAY 23 -----------------------------
use leisure;

create table store (
	id					int not null auto_increment,
    address_id			int not null,
    manager_id			int not null,
    staff_id			int not null,
    last_update			timestamp,
	constraint store_pk primary key (id),
    constraint store_address_fk foreign key (address_id) references address(id),
    constraint store_manager_fk foreign key (manager_id) references manager(id),
    constraint store_staff_fk foreign key (staff_id) references staff(id)
);

create table staff (
	id					int not null,
    name				varchar(100) not null,
    constraint staff_pk primary key (id)
);

create table manager (
	id					int not null auto_increment,
    manager_name		varchar(150) not null,
    constraint manager_pk primary key (id)
);

create table address (
	id					int not null auto_increment,
    city_id				int not null,
    address				varchar(50) not null,
    address2			varchar(50) not null,
    district			varchar(20) not null,
    postal_code			varchar(10) not null,
    phone				varchar(20) not null,
    location			geometry,
    last_update			timestamp,
    constraint address_pk primary key (id),
    constraint address_city_fk foreign key (city) references city(id)
);

create table city (
	id					int not null auto_increment,
    city				varchar(50) not null,
    country_id			int not null,
    last_update			timestamp,
    constraint city_pk primary key (id),
    constraint city_country foreign key (country) references country(id)
);

create table country (
	id					int not null auto_increment,
    country				varchar(50) not null,
    constraint country_pk primary key (id)
);

select * from employee;
select * from dependent;

insert into employee (first_name, last_name, salary) values ('darren', 'low', 9000.00);
insert into employee (first_name, last_name, salary) values ('irwan', 'salim', 10000.00);
insert into employee (first_name, last_name, salary) values ('mahesan', 'elangova', 10000.00);
insert into employee (first_name, last_name, salary) values ('shi yi', 'yeo', 10000.00);
insert into employee (first_name, last_name, salary) values ('ridzuan', 'azmi', 15000.00);
insert into employee (first_name, last_name, salary) values ('romnah', 'tan', 5000.00);

insert into dependent (employee_id, full_name, relationship, birthdate) values (1, 'jason bourne', 'father', '1985-05-06');
insert into dependent (employee_id, full_name, relationship, birthdate) values (1, 'jason statham', 'grandfather', '1998-06-20');
insert into dependent (employee_id, full_name, relationship, birthdate) values (2, 'shrek', 'brother', '1965-05-06');
insert into dependent (employee_id, full_name, relationship, birthdate) values (2, 'pussinboots', 'mother', '1976-08-06');
insert into dependent (employee_id, full_name, relationship, birthdate) values (4, 'camilla', 'sister', '1997-05-25');
insert into dependent (employee_id, full_name, relationship, birthdate) values (5, 'cabello', 'son', '1992-10-25');

-- ---------------------- update employee/dependent ------------------------------------------
update employee set first_name = ?, last_name = ?, salary = ? 
where id = ?;
update dependent set employee_id= 1, full_name = 'akhtar khan', relationship = 'godfather', birthdate = '1995-04-06' 
where id = 2;

-- ---------------------- delete employee/dependent ------------------------------------------
delete from employee where id = 1;
delete from dependent where id = 9;

-- --- pull employee record together it's dependent using left/right/inner join ----
select emp.id, emp.first_name, emp.last_name, emp.salary, dep.id dep_id, dep.full_name dep_full_name, dep.relationship 
from employee as emp 
left join dependent as dep 
on emp.id = dep.employee_id;

select emp.id, emp.first_name, emp.last_name, emp.salary, dep.id dep_id, dep.full_name dep_full_name, dep.relationship 
from employee as emp 
right join dependent as dep 
on emp.id = dep.employee_id;

select emp.id, emp.first_name, emp.last_name, emp.salary, dep.id dep_id, dep.full_name dep_full_name, dep.relationship 
from employee as emp 
inner join dependent as dep 
on emp.id = dep.employee_id;

-- --- findById ------------------------------------------------------
select emp.id, emp.first_name, emp.last_name, emp.salary, dep.id dep_id, dep.full_name dep_full_name, dep.relationship
from employee as emp
inner join dependent as dep
on emp.id = dep.employee_id
where emp.id = 3;

-- -------------------- INDEX ----------------------------------------------
create index idx_firstname
on employee(first_name);
-- --------------------------------------------------------------------
-- ------------------NORTHWIND SCHEMAS CODES --------------------------
-- --------------------------------------------------------------------

use northwind;

select count(*) from customers where city like 'New York';

select city, count(city) as cnt from customers group by city;

select city, state_province, count(city) as cnt from customers group by city, state_province;

select * from order_details;

select customer_id, avg(shipping_fee) as shipping_avg, sum(shipping_fee) as shipping_sum
from orders 
group by customer_id
order by customer_id;

select last_name, length(last_name) as last_name_length, 
first_name, length(first_name) as first_name_length
from customers;

select customer_id, avg(shipping_fee) as shipping_avg, sum(shipping_fee) as shipping_sum
from orders 
group by customer_id
having sum(shipping_fee) >50
order by customer_id;

-- ---------------------------- NORTHWIND JOINS ---------------------------------------------
select * from customers;

select * from orders;

-- ----------------------------- INNER JOIN with VIEWS---------------------------------------
create view customer_orders as 
select cust.id as cust_id, cust.company, cust.last_name, cust.first_name, cust.job_title, 
ord.order_date, ord.shipped_date, ord.ship_name, ord.shipping_fee
from customers as cust
inner join orders as ord
on cust.id = ord.customer_id
order by cust.company;

select * from customer_orders;

-- ----------------------------- LEFT JOIN ------------------------------------------------
select cust.id as cust_id, cust.last_name, ord.id as order_id -- column aliasing
from customers as cust -- table aliasing
left join orders as ord
on cust.id = ord.customer_id
order by cust.last_name;

-- ----------------------------- RIGHT JOIN ------------------------------------------------
select cust.id cust_id, cust.last_name,
ord.id as order_id
from customers as cust
right join orders as ord
on ord.customer_id = cust.id
order by cust.last_name;

-- ------------------------- SUB QUERIES ---------------------------------------------
select * from products;
select * from suppliers;
select * from purchase_order_details;

select id, product_code,product_name from products
where id in (select distinct product_id from purchase_order_details);

select * from products;
select * from suppliers;
select id, company, last_name,first_name, job_title from suppliers
where id in (select distinct supplier_ids from products);

select distinct sup.id, sup.company, sup.last_name, sup.first_name, sup.job_title
from suppliers as sup
inner join products as prod
on prod.supplier_ids = sup.id;

-- ---------------------- DAY 23 WORKSHOP ---------------------------------------------------
select * from order_details;
select * from products;
select * from orders;
select id as order_id, order_date, customer_id from orders;

-- --------------------- total price of the order -------------------------------------
select * from order_details;

select order_id, product_id, (quantity * unit_price * if(discount > 0, discount, 1)) as total_price
from order_details; -- get the total price from order_details

select od.order_id, od.product_id, (od.quantity * prod.standard_cost) as cost_price
from order_details as od
inner join products as prod
on od.product_id = prod.id; -- get cost price from using products and order details table

select o.id as order_id, o.order_date, o.customer_id, od.product_id,
(od.quantity * od.unit_price * if(od.discount > 0, od.discount, 1)) as total_price,
(od.quantity * prod.standard_cost) as cost_price
from order_details as od
inner join products as prod
on od.product_id = prod.id
inner join orders as o
on o.id = od.order_id 
where o.id = 31;