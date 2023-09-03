SELECT A.PRODUCT_CODE, (A.PRICE * SUM(SALES_AMOUNT)) AS SALES
FROM PRODUCT A JOIN OFFLINE_SALE B ON A.PRODUCT_ID = B.PRODUCT_ID
GROUP BY A.PRODUCT_CODE,PRICE
ORDER BY 2 DESC , 1