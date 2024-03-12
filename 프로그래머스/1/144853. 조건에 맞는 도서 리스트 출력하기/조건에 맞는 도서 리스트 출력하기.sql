select book_id, date_format(published_date, '%Y-%m-%d') as published_date
from book
where substr(published_date,1,4) = '2021'
and category = '인문'
order by published_date;