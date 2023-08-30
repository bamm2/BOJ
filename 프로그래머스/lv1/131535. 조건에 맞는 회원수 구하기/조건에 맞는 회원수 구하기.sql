select count(user_id)
from user_info
where extract(year from joined)='2021' AND AGE BETWEEN 20 AND 29 ;