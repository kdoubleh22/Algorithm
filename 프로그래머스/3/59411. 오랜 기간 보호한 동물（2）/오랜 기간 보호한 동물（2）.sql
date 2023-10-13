# select 아이디, 이름
# from 입양을 간 동물 중
# 입양기간이 가장 길었던
# order by 보호기간 오름차순
# limit 2

select a.animal_id, a.name
from animal_ins as a
join animal_outs as b
on a.animal_id = b.animal_id
order by datediff(b.datetime,a.datetime) desc
limit 2