# select 도서id, 저자명, 출판일
# from
# where 경제 카테고리
# group by 출판일 오름차순

select BOOK_ID, AUTHOR_NAME, date_format(PUBLISHED_DATE,'%Y-%m-%d') as PUBLISHED_DATE
from BOOK as b
join AUTHOR as a on b.AUTHOR_ID = a.AUTHOR_ID
where b.category = '경제'
order by PUBLISHED_DATE