use northwind;

select * from customers limit 5 offset 0;

select * from customers where id = 1;

select * from orders;

select * from orders
order by customer_id;

select * 
from customers
left join orders
on customers.id = orders.customer_id
