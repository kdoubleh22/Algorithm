# select MEMBER_NAME, REVIEW_TEXT, date_format(REVIEW_DATE,'%Y-%m-%d') REVIEW_DATE
# from MEMBER_PROFILE a
# join REST_REVIEW b on a.MEMBER_ID = b.MEMBER_ID
# where a.MEMBER_ID in (select member_id
#                       from REST_REVIEW
#                       group by member_id
#                       having count(*) = (select count(*) c
#                                          from REST_REVIEW
#                                          group by member_id
#                                          order by c desc
#                                          limit 1
#                                         )
#                      )
# order by REVIEW_DATE, REVIEW_TEXT;

select MEMBER_NAME, REVIEW_TEXT, date_format(REVIEW_DATE,'%Y-%m-%d') REVIEW_DATE
from MEMBER_PROFILE a
join REST_REVIEW b on a.MEMBER_ID = b.MEMBER_ID
where a.MEMBER_ID in (select * from (select member_id
                                     from REST_REVIEW
                                     group by member_id
                                     order by count(*) desc
                                     limit 1
                                    ) temp
                     )
order by REVIEW_DATE, REVIEW_TEXT;