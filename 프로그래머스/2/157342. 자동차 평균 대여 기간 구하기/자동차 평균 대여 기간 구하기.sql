select car_id, round(avg(datediff(end_date,start_date) + 1), 1) AVERAGE_DURATION
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
group by car_id
having avg(datediff(end_date,start_date) + 1) >= 7
order by AVERAGE_DURATION desc, car_id desc;