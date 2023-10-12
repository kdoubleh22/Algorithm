-- 생물 종, 이름, 성별 및 중성화
-- 아이디 순
-- 이름이 null이면 "No name"으로 표시.
select animal_type, ifnull(name,'No name') as name, sex_upon_intake
from animal_ins
order by animal_id