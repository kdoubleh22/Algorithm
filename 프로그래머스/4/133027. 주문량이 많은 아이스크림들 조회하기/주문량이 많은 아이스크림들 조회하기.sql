select flavor
from first_half a
order by (total_order + (select sum(total_order) from july b where b.flavor = a.flavor)) desc
limit 3;