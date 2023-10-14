# select 회원이름, 리뷰텍스트, 리뷰작성일
# from
# where 리뷰를 가장 많이 작성한.
# order by 리뷰작성일 오름차순, 리뷰텍스트 오름차순

select MEMBER_NAME, REVIEW_TEXT, date_format(REVIEW_DATE,'%Y-%m-%d')
from MEMBER_PROFILE as m
join REST_REVIEW as r on m.MEMBER_ID = r.MEMBER_ID
where m.MEMBER_ID in (select MEMBER_ID
                      from REST_REVIEW
                      group by MEMBER_ID
                      having count(MEMBER_ID) = (select count(MEMBER_ID)
                                                 from REST_REVIEW
                                                 group by MEMBER_ID
                                                 order by count(MEMBER_ID) desc
                                                 limit 1))
order by REVIEW_DATE, REVIEW_TEXT;