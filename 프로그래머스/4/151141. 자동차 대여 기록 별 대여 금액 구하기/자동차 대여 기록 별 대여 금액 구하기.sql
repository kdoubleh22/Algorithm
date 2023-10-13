# select 대여기록id, 대여금액 as fee
# from
# where 트럭
# order by 금액 내림차순, 대여기록id 내림차순

select distinct history_id, truncate(a.daily_fee * (datediff(b.end_date,b.start_date)+1) * 
                    (case
                          when datediff(b.end_date,b.start_date)+1 < 7
                          then 1
                          when datediff(b.end_date,b.start_date)+1 >= 7
                                and datediff(b.end_date,b.start_date)+1 < 30
                          then 1-((select discount_rate 
                                from CAR_RENTAL_COMPANY_DISCOUNT_PLAN
                                where car_type = '트럭' and duration_type = '7일 이상') / 100)
                          when datediff(b.end_date,b.start_date)+1 >= 30
                                and datediff(b.end_date,b.start_date)+1 < 90
                          then 1-((select discount_rate 
                                from CAR_RENTAL_COMPANY_DISCOUNT_PLAN
                                where car_type = '트럭' and duration_type = '30일 이상') / 100)
                          else 1-((select discount_rate 
                                from CAR_RENTAL_COMPANY_DISCOUNT_PLAN
                                where car_type = '트럭' and duration_type = '90일 이상') / 100)
                     end),0) as fee
from CAR_RENTAL_COMPANY_CAR as a
join CAR_RENTAL_COMPANY_RENTAL_HISTORY as b on a.CAR_ID = b.CAR_ID
join CAR_RENTAL_COMPANY_DISCOUNT_PLAN as c on a.CAR_TYPE = c.CAR_TYPE
where a.CAR_TYPE = '트럭'
order by fee desc, history_id desc;

# select *
# from CAR_RENTAL_COMPANY_CAR as a
# join CAR_RENTAL_COMPANY_RENTAL_HISTORY as b on a.CAR_ID = b.CAR_ID
# join CAR_RENTAL_COMPANY_DISCOUNT_PLAN as c on a.CAR_TYPE = c.CAR_TYPE
# where history_id = 556
# order by a.car_id desc;

# # select * from CAR_RENTAL_COMPANY_CAR;