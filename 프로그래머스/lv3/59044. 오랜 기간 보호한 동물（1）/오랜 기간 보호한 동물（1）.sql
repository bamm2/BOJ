-- 코드를 입력하세요
SELECT I.NAME , I.DATETIME
FROM ANIMAL_INS as I 
LEFT JOIN ANIMAL_OUTS as O
ON I.ANIMAL_ID=O.ANIMAL_ID
WHERE O.DATETIME IS NULL 
ORDER BY DATETIME 
LIMIT 3;
