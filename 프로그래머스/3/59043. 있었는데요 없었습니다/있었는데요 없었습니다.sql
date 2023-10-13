# select 동물의 아이디, 이름
# from
# where 보호 시작일보다 입양일이 빠른
# order by 보호 시작일 오름차순

select i.ANIMAL_ID, i.NAME
from ANIMAL_INS as i
join ANIMAL_OUTS as o on i.ANIMAL_ID = o.ANIMAL_ID
where i.datetime > o.datetime
order by i.datetime