select flavor
from first_half A join icecream_info B using(flavor)
where ingredient_type='fruit_based' and total_order>3000
order by total_order desc ;