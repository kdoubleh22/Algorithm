# 대여중이라는 의미는 2022-10-16이 start_date보다는 크거나 같고, end_date보단 작거나 같아야함.
# start_date <= 2022-10-16 <= end_date 이 한 개라도 있으면 대여가능
# group by car_id
# order by car_id

select car_id, (case
                    when car_id in (select car_id
                                    from CAR_RENTAL_COMPANY_RENTAL_HISTORY
                                    where start_date <= '2022-10-16'
                                    and end_date >= '2022-10-16')
                    then '대여중'
                    else '대여 가능'
                end) as availability
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
group by car_id
order by car_id desc;