select a.item_id, item_name, rarity
from item_info a join item_tree b
on a.item_id = b.item_id
where (select rarity from item_info where item_id = b.parent_item_id) = 'RARE'
order by item_id desc;