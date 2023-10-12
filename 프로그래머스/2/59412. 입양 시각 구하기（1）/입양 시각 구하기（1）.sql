# 시간 as hour, 시간대별로 입양이 몇 건 발생하는지 as count
# from
# 9시부터 19:59
# group by hour(datetime) 시간대별로 그룹화한 후
# 시간대 순(hour) 정렬

select hour(datetime) as hour, count(datetime) as count
from animal_outs
where hour(datetime) >= 9
and hour(datetime) <=19
group by hour(datetime)
order by hour