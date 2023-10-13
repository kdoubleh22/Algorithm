# select 아이디, 이름, 성별 및 중성화 여부
# from
# where 이름이 저 중 한 개
# order by 아이디 순

select animal_id, name, sex_upon_intake
from animal_ins
where name in ('Lucy', 'Ella', 'Pickle', 'Rogan', 'Sabrina', 'Mitty')
order by animal_id