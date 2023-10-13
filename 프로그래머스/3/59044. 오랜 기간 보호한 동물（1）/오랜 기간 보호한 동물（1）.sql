# select 가장 오래 보호소에 있었던 동물 3마리
# from
# where 입양을 못 간 -> in엔 id가 있고 out엔 id가 없음
# order by 보호시작일 

select NAME, DATETIME
from ANIMAL_INS
where ANIMAL_ID not in (select ANIMAL_ID
                       from ANIMAL_OUTS)
order by DATETIME
limit 3