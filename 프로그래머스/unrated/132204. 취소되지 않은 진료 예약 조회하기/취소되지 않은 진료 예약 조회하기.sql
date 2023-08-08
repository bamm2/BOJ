-- 코드를 입력하세요
SELECT APNT_NO , PT_NAME, P.PT_NO , A.MCDP_CD, DR_NAME , APNT_YMD
FROM PATIENT AS P , DOCTOR AS D , APPOINTMENT AS A
WHERE D.DR_ID = A.MDDR_ID AND P.PT_NO = A.PT_NO
    AND DATE_FORMAT(A.APNT_YMD,"%Y-%m-%d") = '2022-04-13'
    AND APNT_CNCL_YN = 'N'
ORDER BY APNT_YMD ASC ; 