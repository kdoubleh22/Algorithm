# select 주문 id, 제품 id, 출고일자, 출고여부(5월1일까지 출고완료, 이후날짜는 출고대기, 미정이면 출고미정)
# from food_order
# order by 주문 id

select order_id, product_id, date_format(out_date,'%Y-%m-%d'), (case
                                                                   when out_date <= '2022-05-01'
                                                                   then '출고완료'
                                                                   when out_date > '2022-05-01'
                                                                   then '출고대기'
                                                                   else '출고미정'
                                                               end) as 출고여부
from food_order
order by order_id