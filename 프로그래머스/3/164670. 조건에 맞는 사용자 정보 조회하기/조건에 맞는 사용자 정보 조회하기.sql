# select 사용자id,닉네임,전체주소,전화번호
# from
# group by 사용자 id
# having 3건 이상 등록 count
# order by 회원id 내림차순

select user_id, nickname, concat(CITY,' ',STREET_ADDRESS1,' ',STREET_ADDRESS2) as 전체주소, concat(substring(TLNO,1,3),'-',substring(TLNO,4,4),'-',substring(TLNO,8,4)) as 전화번호
from USED_GOODS_BOARD as a
join USED_GOODS_USER as b
on a.WRITER_ID = b.USER_ID
group by USER_ID
having count(USER_ID) >= 3
order by user_id desc