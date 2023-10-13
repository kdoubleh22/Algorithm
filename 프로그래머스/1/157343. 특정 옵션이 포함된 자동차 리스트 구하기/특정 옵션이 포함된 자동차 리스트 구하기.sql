# select *
# from
# where 네비게이션 옵션이 포함된 자동차
# order by 자동차 id 기준 내림차순

select *
from CAR_RENTAL_COMPANY_CAR
where options like '%네비게이션%'
order by car_id desc