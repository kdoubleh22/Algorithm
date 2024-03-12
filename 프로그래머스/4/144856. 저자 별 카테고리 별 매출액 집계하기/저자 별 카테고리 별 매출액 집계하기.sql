select a.author_id, b.author_name, a.category, sum(a.price * c.sales) total_sales
from book a join author b
on a.author_id = b.author_id
join book_sales c
on a.book_id = c.book_id
where substr(c.sales_date,1,7) = '2022-01'
group by a.author_id, b.author_name, a.category
order by a.author_id, a.category desc;