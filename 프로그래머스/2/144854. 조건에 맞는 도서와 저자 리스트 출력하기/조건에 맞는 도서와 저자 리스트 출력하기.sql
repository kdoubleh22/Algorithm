# select 도서id,저자명,출판일
# from
# where 경제 카테고리
# order by 출판일 오름차순

select BOOK_ID, AUTHOR_NAME, date_format(PUBLISHED_DATE,'%Y-%m-%d') as PUBLISHED_DATE
from book as a
join author as b on a.author_id = b.author_id
where a.category = '경제'
order by PUBLISHED_DATE;