# 조회수가 가장 높은 게시물은 하나만 존재
# select
# from 테이블
# where 조회수가 가장 높은 게시물
# order by file_id 내림차순

select concat('/home/grep/src/',a.board_id,'/',file_id,FILE_NAME,FILE_EXT) as FILE_PATH
from USED_GOODS_BOARD as a
join USED_GOODS_FILE as b
on a.BOARD_ID = b.BOARD_ID
where VIEWS = (select max(views) from USED_GOODS_BOARD)
order by FILE_ID desc