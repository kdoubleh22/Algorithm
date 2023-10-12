# 음식종류, rest_id, rest_name, favorites
# from rest_info 테이블에서
# where 각 음식종류별 가장 많은 favorites 서브쿼리
# group by 음식종류 별로
# order by 음식종류 내림차순

# 근데 여기서 즐겨찾기 수가 가장 많은 식당이 여러개면? 그래서 where 서브쿼리 쓰는구나.

select food_type, rest_id, rest_name, favorites
from rest_info
where (food_type, favorites) in (select food_type, max(favorites)
                                 from rest_info
                                 group by food_type)
order by food_type desc;