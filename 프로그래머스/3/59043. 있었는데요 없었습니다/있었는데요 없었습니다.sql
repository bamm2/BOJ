SELECT I.ANIMAL_ID,I.NAME
FROM ANIMAL_INS as I JOIN ANIMAL_OUTS as O USING(ANIMAL_ID)
WHERE I.DATETIME > O.DATETIME
ORDER BY I.DATETIME
