# select 저자id, 저자명, 카테고리, 매출액
# from 
# where 2022년 1월
# group by 저자 별, 카테고리 별
# order by 저자id, 카테고리 내림차순

select a.author_id, b.author_name, a.category, sum(a.price * c.sales) as total_sales
from book as a
join author as b
on a.author_id = b.author_id
join book_sales as c
on a.book_id = c.book_id
where substring(sales_date,1,7) = '2022-01'
group by a.author_id, b.author_name, a.category
order by a.author_id, a.category desc;