# select a.item_id, item_name, rarity
# from item_info a join item_tree b
# on a.item_id = b.item_id
# where (select rarity from item_info where item_id = b.parent_item_id) = 'RARE'
# order by item_id desc;

# 참고코드
select a.item_id, item_name, rarity
from item_info a join item_tree b
on a.item_id = b.item_id
where b.parent_item_id in (select item_id from item_info where rarity = 'RARE')
order by item_id desc;