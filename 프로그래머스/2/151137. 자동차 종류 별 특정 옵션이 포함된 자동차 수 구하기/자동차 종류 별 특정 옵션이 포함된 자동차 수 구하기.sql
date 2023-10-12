-- 자동차종류, 종류별 통열가 중 하나이상의 옵션이 포함된 자동차의 개수 as cars
-- 퉁열가 중 하나를 포함한 자동차
-- 자동차 종류 별
-- 자동차 종류 오름차순
select car_type, count(*) as cars
from car_rental_company_car
where options like '%통풍시트%'
or options like '%열선시트%'
or options like '%가죽시트%'
group by car_type
order by car_type