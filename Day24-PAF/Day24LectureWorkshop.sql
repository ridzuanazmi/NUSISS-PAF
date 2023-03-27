
-- -------------------------------------- DAY 24 -------------------------------------------------
create database day24workshop;

use day24workshop;

create table orders (
	id					int not null auto_increment,
    order_date			date,
    customer_name		varchar(128),
	ship_address		varchar(128),
    notes				text,
    tax					decimal(4,2) default 0.05,
    constraint			order_pk primary key (id)
);

create table order_details (
	id					int not null auto_increment,
    order_id			int not null,
    product				varchar(64),
    unit_price			decimal(5,2),
    discount			decimal(4,2) default 1.00,
    quantity			int,
    constraint			order_details_pk primary key (id),
    constraint			order_details_orders_fk foreign key (order_id) references orders(id)
);

-- Populate the tables with values
insert into orders (order_date, customer_name, ship_address, notes, tax) values ('2022-06-04', 'akhtar khan', 'Choa Chu Kang St 52', 'handle with care', '0.5');
insert into orders (order_date, customer_name, ship_address, notes, tax) values ('2023-01-04', 'jalal tan', 'bukit ho swee st 90', 'fragile items', '0.8');
insert into orders (order_date, customer_name, ship_address, notes, tax) values ('2023-02-10', 'ridzy jaeger', 'sixth avenue 12', 'electronics inside', '0.9');

insert into order_details (order_id, product, unit_price, discount, quantity) values (1, 'prism tv', 600.00, 10.2, 1);
insert into order_details (order_id, product, unit_price, discount, quantity) values (2, 'prism tv', 850.00, 50.2, 2);
insert into order_details (order_id, product, unit_price, discount, quantity) values (1, 'air fryer', 400.00, 20.5, 3);

select * from orders;
select * from order_details;

select o.id as order_id, od.product, o.order_date, o.customer_name, o.ship_address, o.notes 
from orders as o
right join order_details as od
on o.id = od.order_id
order by o.id;

select o.id as order_id,  o.customer_name, o.ship_address, o.order_date,
od.product, od.unit_price, od.discount, od.quantity, o.notes, o.tax 
from orders as o
left join order_details as od
on o.id = od.order_id
order by o.id;

select * 
from orders 
inner join order_details 
on orders.id = order_details.order_id 
order by orders.id;