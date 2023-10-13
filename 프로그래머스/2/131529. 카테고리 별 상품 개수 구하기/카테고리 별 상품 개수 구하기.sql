# select 카테고리, 카테고리코드별 상품 개수 as products
# from product 테이블에서
# group by 상품 카테고리 코드 별
# order by 상품 카테괼 코드별 오름차순

select substring(product_code,1,2) as category, count(*) as products
from product
group by substring(product_code,1,2)
order by category