select a.rest_id, a.rest_name, a.food_type, a.favorites, a.address, round(avg(b.review_score),2) score
from rest_info a join rest_review b
on a.rest_id = b.rest_id
where substr(address,1,2) = '서울'
group by rest_id
order by score desc, favorites desc;