-- 코드를 입력하세요
SELECT SUBSTRING(PRODUCT_CODE,1,2) as CATEGORY , COUNT(SUBSTRING(PRODUCT_CODE,1,2)) as PRODUCTS
FROM PRODUCT
GROUP BY 1
ORDER BY 1;
