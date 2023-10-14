# 11월만 볼게아니라, 9월에 시작해서 12월에 끝날수도잇음.

# select 자동차id, 자동차종류, 대여금액 as FEE
# from
# where 자동차 종류가 세단 또는 SUV
# and 2022년 11월 1일부터 2022년 11월 30일까지 대여가능 not 시작날짜가 사이거나 끝나는날짜가 사이임.
# and 30일간의 대여금액(일일요금*30*(1-할인율))이 50만원 이상 200만원 미만
# order by 대여금액 내림차순, 자동차종류 오름차순, 자동차id 내림차순

select *
# select c.CAR_ID, c.CAR_TYPE, truncate((c.DAILY_FEE*30*(1-d.DISCOUNT_RATE/100)),0) as FEE
from CAR_RENTAL_COMPANY_CAR as c
join CAR_RENTAL_COMPANY_RENTAL_HISTORY as h on c.CAR_ID = h.CAR_ID
join (select CAR_TYPE, DISCOUNT_RATE
      from CAR_RENTAL_COMPANY_DISCOUNT_PLAN
      where DURATION_TYPE = '30일 이상') as d on c.CAR_TYPE = d.CAR_TYPE
where c.CAR_TYPE in ('세단', 'SUV')
and c.CAR_ID not in (select distinct car_id
                   from CAR_RENTAL_COMPANY_RENTAL_HISTORY
                   where (start_date>='2022-11-01' and start_date<='2022-11-30')
                   or (end_date>='2022-11-01' and end_date<='2022-11-30'))
group by c.CAR_ID
# having FEE >= 500000 and FEE < 2000000
# order by FEE desc, CAR_TYPE, CAR_ID desc;

;
# select *
select c.CAR_ID, c.CAR_TYPE, truncate((c.DAILY_FEE*30*(1-d.DISCOUNT_RATE/100)),0) as FEE
from CAR_RENTAL_COMPANY_CAR as c
join (select CAR_TYPE, DISCOUNT_RATE
      from CAR_RENTAL_COMPANY_DISCOUNT_PLAN
      where DURATION_TYPE = '30일 이상') as d on c.CAR_TYPE = d.CAR_TYPE
where c.CAR_TYPE in ('세단', 'SUV')
and c.CAR_ID not in (select distinct car_id
                   from CAR_RENTAL_COMPANY_RENTAL_HISTORY
                   where (start_date>='2022-11-01' and start_date<='2022-11-30')
                   or (end_date>='2022-11-01' and end_date<='2022-11-30')
                   or (start_date <= '2022-10-31' and end_date >='2022-12-01'))
and c.DAILY_FEE*30*(1-d.DISCOUNT_RATE/100) >= 500000
and c.DAILY_FEE*30*(1-d.DISCOUNT_RATE/100) < 2000000
order by FEE desc, CAR_TYPE, CAR_ID desc;