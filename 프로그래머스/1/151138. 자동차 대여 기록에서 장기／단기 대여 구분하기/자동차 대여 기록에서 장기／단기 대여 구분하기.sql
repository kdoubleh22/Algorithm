select HISTORY_ID, CAR_ID, 
date_format(START_DATE,'%Y-%m-%d') START_DATE, 
date_format(END_DATE,'%Y-%m-%d') END_DATE, 
(case when datediff(END_DATE,START_DATE) + 1 >= 30 then '장기대여'                                                                                    else '단기대여'                                                                                    end) RENT_TYPE
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
where substr(START_DATE,1,7) = '2022-09'
order by HISTORY_ID desc;

select history_id, car_id, date_format(start_date,'%Y-%m-%d') as start_date, date_format(end_date,'%Y-%m-%d') as end_date, (case
     when datediff(end_date, start_date) >= 29
     then '장기 대여'
     else '단기 대여'
 end) as rent_type
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
where substring(start_date,1,7) = '2022-09'
order by history_id desc;