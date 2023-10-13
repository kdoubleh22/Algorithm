# select 중복없는(distinct) 자동차id
# from
# where 자동차 종류가 세단, 10월에 대여 시작
# order by 자동차id 내림차순

select distinct a.car_id
from CAR_RENTAL_COMPANY_CAR as a
join CAR_RENTAL_COMPANY_RENTAL_HISTORY as b
on a.car_id = b.car_id
where a.car_type = '세단'
and month(START_DATE) = 10
order by car_id desc