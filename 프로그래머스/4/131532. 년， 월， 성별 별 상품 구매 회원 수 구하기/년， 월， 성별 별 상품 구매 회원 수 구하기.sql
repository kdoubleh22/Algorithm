# select 년, 월, 성별, 회원수
# from
# where 성별 정보가 없는 경우 제외
# group by 년 월 성별
# order by 년 월 성별 오름차순

select year(sales_date) as year, month(sales_date) as month, gender, count(distinct a.user_id) as users
from user_info as a
join online_sale as b
on a.user_id = b.user_id
where gender is not null
group by year(sales_date), month(sales_date), gender
order by year, month, gender