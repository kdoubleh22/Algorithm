-- 진료과코드 as '진료과 코드', 예약건수 as '5월예약건수'
-- 2022년 5월
-- 진료과코드 별
-- 5월예약건수 오름차순, 진료과코드 오름차순
select mcdp_cd as '진료과코드', count(*) as '5월예약건수'
from appointment
where substring(apnt_ymd,1,7) = '2022-05'
group by mcdp_cd
order by 5월예약건수, 진료과코드