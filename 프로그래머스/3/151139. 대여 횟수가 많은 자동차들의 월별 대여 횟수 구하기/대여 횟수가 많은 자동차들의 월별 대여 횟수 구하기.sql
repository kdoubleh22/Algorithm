# select 월, 차id, 총 대여횟수  as records
# from
# where 2022년 8월부터 2022년 10월까지 총 대여 횟수가 5회 이사인 자동차들에 대해서
# group by 
# having
# order by 월을 기준으로 오름차순, id 기준 내림차순

select month(start_date) as month, car_id, count(*) as records
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
where car_id in (select car_id
                from CAR_RENTAL_COMPANY_RENTAL_HISTORY
                where year(start_date) = 2022
                and month(start_date) >= 8
                and month(start_date) <= 10
                group by car_id
                having count(car_id) >= 5)
and year(start_date) = 2022
and month(start_date) >= 8
and month(start_date) <= 10
group by month(start_date), car_id
# having count(*) != 0
order by month, car_id desc