WITH FINDMAXCNT AS
(
SELECT MEMBER_ID
FROM REST_REVIEW
GROUP BY MEMBER_ID
ORDER BY COUNT(*) DESC
LIMIT 1
)

SELECT MEMBER_NAME,REVIEW_TEXT,DATE_FORMAT(REVIEW_DATE,'%Y-%m-%d') AS REVIEW_DATE
FROM MEMBER_PROFILE A JOIN REST_REVIEW USING(MEMBER_ID)
WHERE MEMBER_ID IN (
                    SELECT *
                    FROM FINDMAXCNT
                  )
ORDER BY 3,2