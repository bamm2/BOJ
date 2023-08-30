select name 
from (select * from animal_ins order by datetime)
where ROWNUM=1