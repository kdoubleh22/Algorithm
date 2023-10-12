# select 회원 id, 닉네임, 총거래금액 as total_sales
# from
# where 완료된
# group by user_id로 그룹화.
# having 총금액이 70만원 이상
# order by 총거래금액 오름차순

select b.user_id, b.nickname, sum(a.price) as total_sales
from used_goods_board as a
join used_goods_user as b
on a.writer_id = b.user_id
where a.status = 'DONE'
group by b.user_id
having sum(a.price) >= 700000
order by total_sales