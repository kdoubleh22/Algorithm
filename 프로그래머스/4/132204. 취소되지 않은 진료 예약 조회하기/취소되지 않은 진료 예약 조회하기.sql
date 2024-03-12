select a.APNT_NO, b.PT_NAME, a.PT_NO, a.MCDP_CD, c.DR_NAME, a.APNT_YMD
from appointment a
join PATIENT b on a.PT_NO = b.PT_NO
join doctor c on a.mddr_id = c.dr_id
where substr(a.APNT_YMD, 1, 10) = '2022-04-13'
and APNT_CNCL_YN = 'N'
and a.MCDP_CD = 'CS'
order by apnt_ymd;