SELECT INGREDIENT_TYPE , SUM(TOTAL_ORDER) AS TOTAL_ORDER
FROM FIRST_HALF as F JOIN ICECREAM_INFO as I ON  F.FLAVOR = I.FLAVOR
GROUP BY INGREDIENT_TYPE
ORDER BY TOTAL_ORDER;