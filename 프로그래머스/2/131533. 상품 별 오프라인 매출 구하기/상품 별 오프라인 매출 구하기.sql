# select sum 매출액(판매가 * 판매량)
# from
# group by 상품코드별
# order by 매출액 내림차순, 상품코드 오름차순

select a.PRODUCT_CODE, sum(a.price * b.sales_amount) as SALES
from PRODUCT as a
join OFFLINE_SALE as b on a.PRODUCT_ID = b.PRODUCT_ID
group by a.PRODUCT_ID
order by SALES desc, PRODUCT_CODE