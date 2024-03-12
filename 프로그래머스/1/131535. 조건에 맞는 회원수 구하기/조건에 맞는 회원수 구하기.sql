select count(*)
from user_info
where substr(joined,1,4) = '2021'
and age between 20 and 29;