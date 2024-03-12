select a.ANIMAL_ID, a.NAME
from ANIMAL_INS a
join ANIMAL_OUTS b on a.ANIMAL_ID = b.ANIMAL_ID
order by datediff(b.DATETIME, a.DATETIME) desc
limit 2;