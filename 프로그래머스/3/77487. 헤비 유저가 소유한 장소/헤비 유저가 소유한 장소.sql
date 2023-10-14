# select *
# from
# where host_id가 공간을 둘 이상 등록한 사람
# order by id 오름차순

select *
from PLACES
where HOST_ID in (select HOST_ID
                  from PLACES
                  group by HOST_ID
                  having count(HOST_ID) >= 2)
order by ID