# 가격대별로 as price_group, 상품 개수 as products
# from
# 만원 단위의 가격대 별로
# 가격대를 기준으로 오름차순

select truncate(price,-4) as price_group, count(price) as products
from product
group by truncate(price,-4)
order by price_group