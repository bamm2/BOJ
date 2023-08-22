select A.rest_id,rest_name,food_type,favorites,address,ROUND(AVG(review_score),2) as SCORE
from rest_info A JOIN rest_review B 
    USING (rest_id)
where address like "서울%"
group by A.rest_id
order by 6 desc, 4 desc;