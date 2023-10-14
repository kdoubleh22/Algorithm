select i.ANIMAL_ID, i.ANIMAL_TYPE, i.NAME
from ANIMAL_INS as i
join ANIMAL_OUTS as o on i.ANIMAL_ID = o.ANIMAL_ID
where i.SEX_UPON_INTAKE not like '%Spayed%' and i.SEX_UPON_INTAKE not like '%Neutered%'
and (o.SEX_UPON_OUTCOME like '%Spayed%' or o.SEX_UPON_OUTCOME like '%Neutered%')
order by ANIMAL_ID