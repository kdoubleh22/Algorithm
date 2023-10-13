select a.APNT_NO, p.PT_NAME, p.PT_NO, a.MCDP_CD, d.DR_NAME, a.APNT_YMD
from APPOINTMENT as a
join PATIENT as p on a.PT_NO = p.PT_NO
join DOCTOR as d on a.MDDR_ID = d.DR_ID
where substring(APNT_YMD,1,10) = '2022-04-13'
and APNT_CNCL_YMD is null
and a.MCDP_CD = 'CS'
order by a.APNT_YMD