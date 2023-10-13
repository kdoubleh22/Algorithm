# select 아이디, 이름
# from
# where 이름 like '%el%' and 개
# order by 이름순

select animal_id, name
from animal_ins
where name like '%el%'
and animal_type = 'Dog'
order by name
