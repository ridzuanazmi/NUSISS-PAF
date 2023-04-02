use northwind;

select * from customers;
select * from orders;
select * from order_details;
select * from products;

select o.id as order_id, o.customer_id, prod.id as product_id, o.order_date,  
(od.quantity * od.unit_price * if(od.discount > 0, od.discount, 1)) as total_price, 
(od.quantity * prod.standard_cost) as cost_price
from order_details as od
inner join products as prod
on od.product_id = prod.id
inner join orders as o
on o.id = od.order_id
where o.id = 40;