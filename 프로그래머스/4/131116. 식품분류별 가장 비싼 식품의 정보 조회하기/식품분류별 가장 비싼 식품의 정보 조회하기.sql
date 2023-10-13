# select 분류, 가격, 이름
# from 테이블에서
# where 식품분류가 '과자', '국', '김치', '식용유'
# group by 식품분류별로
# order by 식품 가격 내림차순
 
select category, price as max_price, product_name
from food_product
where category in ('과자','국','김치','식용유')
and (category, price) in (select category, max(price)
                          from food_product
                          group by category)
order by max_price desc;