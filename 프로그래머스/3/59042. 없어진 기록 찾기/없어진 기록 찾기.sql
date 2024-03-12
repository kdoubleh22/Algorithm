select ANIMAL_ID, NAME
from ANIMAL_OUTS
where ANIMAL_ID not in (select animal_id
                        from ANIMAL_INS
                       )
order by ANIMAL_ID;