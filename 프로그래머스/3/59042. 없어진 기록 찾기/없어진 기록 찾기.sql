# select 동물id, 이름
# from
# where 입양을 간 기록은 있고, 들어온 기록이 없는 -> out엔 id가 있고, in엔 id가 없음. not in
# order by 아이디순

select ANIMAL_ID, NAME
from ANIMAL_OUTS
where ANIMAL_ID not in (select ANIMAL_ID
                       from ANIMAL_INS)
order by ANIMAL_ID