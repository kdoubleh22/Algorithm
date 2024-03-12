select date_format(sales_date,'%Y-%m-%d') sales_date, product_id, user_id, sales_amount
from online_sale
where substr(sales_date,1,7) = '2022-03'
union
select date_format(sales_date,'%Y-%m-%d'), product_id, null, sales_amount
from offline_sale
where substr(sales_date,1,7) = '2022-03'
order by sales_date, product_id, user_id