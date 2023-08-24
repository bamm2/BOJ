select book_id,DATE_FORMAT(published_date,"%Y-%m-%d") as PUBLISHED_DATE
from BOOK
where YEAR(published_date) = 2021 and category='인문'
order by 2 ;