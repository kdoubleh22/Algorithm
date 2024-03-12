select user_id, nickname, concat(city,' ',STREET_ADDRESS1,' ',STREET_ADDRESS2) 전체주소,
concat(substr(tlno,1,3),'-',substr(tlno,4,4),'-',substr(tlno,8,4)) 전화번호
from USED_GOODS_USER
where USER_ID in (select writer_id
                  from USED_GOODS_BOARD
                  group by writer_id
                  having count(*) >= 3
                 )
order by user_id desc;