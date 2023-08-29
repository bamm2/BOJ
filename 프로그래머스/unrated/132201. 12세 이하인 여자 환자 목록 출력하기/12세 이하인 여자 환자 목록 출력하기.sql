select pt_name,pt_no,gend_cd,age,IFNULL(tlno,'NONE') as tlno
from patient
where age <= 12 AND gend_cd = 'W'
order by 4 desc, 1 ;