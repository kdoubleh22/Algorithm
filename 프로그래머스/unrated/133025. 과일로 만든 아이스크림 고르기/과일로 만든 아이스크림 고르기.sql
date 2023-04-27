select distinct ii.flavor
from first_half hf join icecream_info ii on hf.flavor=ii.flavor
where total_order >= 3000
and ingredient_type = 'fruit_based'