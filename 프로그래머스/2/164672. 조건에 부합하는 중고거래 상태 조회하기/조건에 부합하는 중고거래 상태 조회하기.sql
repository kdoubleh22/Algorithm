# select 게시글id, 작성자id, 게시글제목, 가격, (if ~ 거래상태) as status
# from
# where 2022년 10월 4일
# order by 게시글id 내림차순

select board_id, writer_id, title, price, (case
                                            when status = 'SALE'
                                            then '판매중'
                                            when status = 'RESERVED'
                                            then '예약중'
                                            else '거래완료'
                                           end) as status
from used_goods_board
where substring(created_date,1,10) = '2022-10-05'
order by board_id desc;