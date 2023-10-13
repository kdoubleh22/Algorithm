# select 자동차 id, round평균 대여 기간 as average_duration
# from
# group by car_id
# having 평균 대여 기간,1 >= 7
# 평균대여기간 내림차순, 자동차 id 내림차순

select car_id, round(avg(datediff(end_date,start_date)) + 1,1) as average_duration
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
group by car_id
having round(avg(datediff(end_date,start_date) + 1),1) >= 7
order by average_duration desc, car_id desc;