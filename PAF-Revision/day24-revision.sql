use day24workshop;

select * from order_details;
select * from orders;
select *
from orders
inner join order_details
on orders.id = order_details.order_id;

delete from orders where id between 30 and 37;

update order_details set discount = 0 where id = 4;

select max(id) from orders;

insert into order_details (order_id, product, unit_price, discount, quantity)
values (4, 'prism tv', 600, 0.2, 3);