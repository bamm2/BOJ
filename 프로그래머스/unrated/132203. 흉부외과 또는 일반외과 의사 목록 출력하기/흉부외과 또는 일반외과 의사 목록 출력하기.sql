select dr_name,dr_id,mcdp_cd,date_format(hire_ymd,"%Y-%m-%d") as hire_ymd
from doctor
where mcdp_cd IN ('CS','GS')
order by 4 desc, 1 ;