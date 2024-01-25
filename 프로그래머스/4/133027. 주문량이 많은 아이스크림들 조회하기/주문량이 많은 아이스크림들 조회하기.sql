WITH A as (
SELECT FLAVOR,SUM(TOTAL_ORDER) as T
FROM FIRST_HALF
GROUP BY FLAVOR
),
B as (
    SELECT FLAVOR,SUM(TOTAL_ORDER) as T
FROM JULY
GROUP BY FLAVOR
)

select   A.FLAVOR
from A JOIN B USING(FLAVOR)
ORDER BY (A.T + B.T) desc
limit 3;


