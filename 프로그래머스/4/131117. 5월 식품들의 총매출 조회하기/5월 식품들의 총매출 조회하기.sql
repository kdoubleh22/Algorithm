# select 식품id, 식품이름, sum(가격*양) as 총매출
# from
# where 생산일자가 2022년 5월
# group by 식품id
# order by 총매출 내림차순, 식품id 오름차순

# select p.PRODUCT_ID, p.PRODUCT_NAME, sum(p.PRICE * o.AMOUNT) as TOTAL_SALES
# from FOOD_PRODUCT as p
# join FOOD_ORDER as o on p.PRODUCT_ID = o.PRODUCT_ID
# where substring(o.PRODUCE_DATE,1,7) = '2022-05'
# group by p.PRODUCT_ID
# order by TOTAL_SALES desc, PRODUCT_ID

# select 식품id, 식품이름, 총매출(sum(price * amount))
# where 생산일자가 2022년 5월
# order by 총매출 내림차순, 식품id 오름차순

select p.PRODUCT_ID, p.PRODUCT_NAME, sum(p.price * o.amount) as TOTAL_SALES
from food_product as p
join food_order as o on p.product_id = o.product_id
where substring(o.produce_date,1,7) = '2022-05'
group by p.PRODUCT_ID
order by TOTAL_SALES desc, PRODUCT_ID