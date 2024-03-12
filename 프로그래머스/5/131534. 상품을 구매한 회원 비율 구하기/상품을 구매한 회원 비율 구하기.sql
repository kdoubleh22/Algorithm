select year(b.SALES_DATE) YEAR, month(b.SALES_DATE) MONTH, 
count(distinct a.user_id) PUCHASED_USERS, 
round(count(distinct a.user_id) / (select count(*)
                                  from USER_INFO
                                  where year(joined) = 2021
                                  ),1) PUCHASED_RATIO
from USER_INFO a
join ONLINE_SALE b on a.USER_ID = b.USER_ID
where year(a.joined) = 2021
group by year(b.SALES_DATE), month(b.SALES_DATE)
order by YEAR, MONTH;