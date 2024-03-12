select distinct a.car_id
from CAR_RENTAL_COMPANY_CAR a
join CAR_RENTAL_COMPANY_RENTAL_HISTORY b on a.car_id = b.car_id
where a.car_type = '세단'
and month(b.start_date) = 10
order by a.car_id desc;