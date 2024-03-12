select concat('/home/grep/src/',a.board_id,'/',b.file_id,b.file_name,b.file_ext) file_path
from USED_GOODS_BOARD a
join USED_GOODS_FILE b on a.BOARD_ID = b.BOARD_ID
where a.VIEWS = (select max(views) from USED_GOODS_BOARD)
order by b.file_id desc;