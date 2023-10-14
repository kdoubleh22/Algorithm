# select 년, 월, 구매한 유저수, round(비율,1) 
# from
# where 2021년에 가입
# group by 구매년, 구매월
# order by 년 오름차순, 월 오름차순

select year(SALES_DATE) as YEAR, month(SALES_DATE) as MONTH, count(distinct u.user_id) as PUCHASED_USERS, round(count(distinct u.user_id) / (select count(user_id) from USER_INFO where year(JOINED)=2021),1) PUCHASED_RATIO
from USER_INFO as u
join ONLINE_SALE as s on u.USER_ID = s.USER_ID
where year(u.JOINED) = 2021
group by year(SALES_DATE), month(SALES_DATE)
order by YEAR, MONTH;

# 2021년에 가입한 회원 수
# select count(user_id) from USER_INFO where year(JOINED)=2021;

# # 2021년 가입한 회원 중 년월별로 상품을 구매한 회원수
# select year(SALES_DATE) as YEAR, month(SALES_DATE) as MONTH, count(*) as PUCHASED_USERS
# from USER_INFO as u
# join ONLINE_SALE as s on u.USER_ID = s.USER_ID
# where year(u.JOINED) = 2021
# group by year(SALES_DATE), month(SALES_DATE)
# order by YEAR, MONTH;