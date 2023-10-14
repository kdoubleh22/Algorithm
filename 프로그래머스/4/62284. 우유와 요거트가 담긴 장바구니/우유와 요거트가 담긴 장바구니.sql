# select 장바구니id
# from
# where 우유를 가진 cart_id 중에서 yogurt를 가진 cart_id를 찾음.
# order by 장바구니 아이디 오름차순

select distinct CART_ID
from CART_PRODUCTS
where CART_ID in (select CART_ID
                  from CART_PRODUCTS
                  where NAME = 'Milk')
and NAME = 'Yogurt'
order by CART_ID;

# 우유를 가진 장바구니id
# select *
# from CART_PRODUCTS
# where NAME = 'Milk';

# 286
# 287
# 448
# 448
# 578
# 578
# 636
# 977
# 1048

# 요거트를 가진 장바구니id
# select *
# from CART_PRODUCTS
# where NAME = 'Yogurt';

# 286 448 578 977 1048