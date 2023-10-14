# select 맛
# from
# group by 맛으로 해서 total_order의 합이 큰 3개
# order by 맛
# limit 3

select a.FLAVOR
from FIRST_HALF as a
join JULY as b on a.FLAVOR = b.FLAVOR
group by a.FLAVOR
order by sum(a.TOTAL_ORDER + b.TOTAL_ORDER) desc
limit 3