-- 카테고리, 총 판매량(카테고리별 도서 판래야 합) as total_sales
-- 2022년 1월
-- 카테고리 별
-- 카테고리명 오름차순
select a.category, sum(b.sales) as total_sales
from book as a
join book_sales as b
on a.book_id = b.book_id
where substring(b.sales_date,1,7) = '2022-01'
group by a.category
order by a.category;