-- 성분타입, 성분타입 별 총주문량
-- 총주문량 오름차순
-- 컬럼명 total_order로
select b.ingredient_type, sum(a.total_order) as total_order
from first_half as a
join icecream_info as b
on a.flavor = b.flavor
group by b.ingredient_type
order by total_order