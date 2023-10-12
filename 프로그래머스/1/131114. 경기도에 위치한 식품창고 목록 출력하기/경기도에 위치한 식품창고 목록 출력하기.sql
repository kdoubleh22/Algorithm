select warehouse_id, warehouse_name, address, ifnull(freezer_yn,'N') as freezer_yn
from food_warehouse
where substring(address,1,2) = '경기'
order by warehouse_id