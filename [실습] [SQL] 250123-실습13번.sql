-- 1.지점별 승인된 대출의 총 금액을 계산한 뒤 
-- 해당 금액이 전체 지점에서 승인된 대출 금액의 평균보다 낮은 지점의 지점명과 
-- 승인된 대출의 총 금액, 전체 지점에서 승인된 대출 금액의 평균을 출력하세요.
-- 평균값은 반올림하여 소수점 아래 둘째자리까지 표현합니다.
SELECT *
FROM (
    SELECT DISTINCT B.NAME, 
        SUM(L.AMOUNT) OVER (PARTITION BY B.NAME) TOTAL_AMOUNT,
        ROUND(AVG(L.AMOUNT) OVER (), 2) AVG_AMOUNT
    FROM BRANCHES B, LOANS L
    WHERE B.BRANCH_ID = L.BRANCH_ID
    AND L.STATUS = 'APPROVED'
)
WHERE TOTAL_AMOUNT <= AVG_AMOUNT;

-- 2. 모든 직원의 사번과 이름을 출력하되, 각 직원의 상사의 이름과 상사가 속한 부서 이름도 함께 출력하세요.
-- 상사가 없는 직원은 "NO MANAGER"로 표시하고, 상사가 속한 부서가 없을 경우 "NO DEPARTMENT"로 표시하세요.
SELECT E1.EMPLOYEE_ID, E1.NAME, 
    NVL(E2.NAME, 'NO MANAGER') MANAGER_NAME, 
    NVL(D.DEPARTMENT_NAME, 'NO DEPARTMENT') MANAGER_DEPT
FROM EMPLOYEES E1
LEFT OUTER JOIN EMPLOYEES E2
ON E1.MANAGER_ID = E2.EMPLOYEE_ID
LEFT OUTER JOIN DEPARTMENTS D
ON E2.DEPARTMENT_ID = D.DEPARTMENT_ID
ORDER BY E1.EMPLOYEE_ID;

-- 3. 각 직원의 급여와 그 급여가 전체 직원 급여에서 차지하는 백분율을 계산하세요.
-- 출력 컬럼은 사번, 이름, 급여, 백분율이며 백분율은 반올림하여 소수점 아래 둘째자리까지 표현합니다.
-- (급여가 없는 직원들은 제외, 백분율로 내림차순)
SELECT EMPLOYEE_ID, NAME, SALARY,
    ROUND(SALARY / SUM(SALARY) OVER() * 100, 2) PERCENTAGE
FROM EMPLOYEES
WHERE SALARY IS NOT NULL
ORDER BY SALARY DESC;

-- 4. 각 계좌의 최고 거래 금액이 높은 순으로 상위 5위까지의 계좌를 출력하세요.
-- 동일 순위가 존재할 경우 각각의 순위를 모두 출력하고 출력 컬럼은 계좌 ID, 최고 거래금액입니다.
SELECT *
FROM (
    SELECT ACCOUNT_ID,
    MAX(AMOUNT) MAX_AMOUNT,
    DENSE_RANK() OVER (ORDER BY MAX(AMOUNT) DESC) AS RANK
FROM TRANSACTIONS
GROUP BY ACCOUNT_ID
)
WHERE RANK <= 5;

-- 5. 각 부서에서 급여가 가장 높은 직원의 급여와 가장 낮은 직원의 급여 차이를 계산하여 출력하세요.
-- 부서 ID로 오름차순 정렬합니다.
SELECT DEPARTMENT_ID, MAX(SALARY) - MIN(SALARY) SAL_DIFF
FROM EMPLOYEES
WHERE DEPARTMENT_ID IS NOT NULL
GROUP BY DEPARTMENT_ID
ORDER BY DEPARTMENT_ID;

-- 6. 각 직원이 속한 부서별 평균 급여와 자신의 급여를 비교하여 급여의 차이를 계산하여 출력하세요.
-- 자신의 급여가 높으면 양수, 낮으면 음수로 표현합니다.
-- 출력 컬럼은 사번, 이름, 급여, 부서 평균 급여, 급여의 차이이며 
-- 부서 평균 급여와 급여의 차이는 반올림하여 정수로 출력해 주세요.
SELECT EMPLOYEE_ID, NAME, SALARY, 
    ROUND(AVG(SALARY) OVER (PARTITION BY DEPARTMENT_ID)) DEPT_AVG_SAL,
    ROUND(SALARY - AVG(SALARY) OVER (PARTITION BY DEPARTMENT_ID)) SAL_DIFF
FROM EMPLOYEES
WHERE SALARY IS NOT NULL;

-- 7. 이름이 'Employee 241'인 사원의 모든 상사들을 출력하세요. 출력 데이터는
-- Employee 241 > Employee 179 > ... 형식이며 한 줄로 나타냅니다.
SELECT *
FROM (
    SELECT LTRIM(SYS_CONNECT_BY_PATH(NAME, ' > '),' > ') PATH
    FROM EMPLOYEES
    START WITH NAME = 'Employee 241'
    CONNECT BY EMPLOYEE_ID = PRIOR MANAGER_ID
    ORDER BY ROWNUM DESC
)
WHERE ROWNUM = 1;


-- 8. 사원 테이블에서 급여가 높은 상위 10명의 사원 정보를 출력하세요.
-- 급여가 동일한 경우 사원 이름 순으로 정렬하여 순위를 부여하세요.
SELECT *
FROM (
    SELECT E.*,
        RANK() OVER (ORDER BY SALARY DESC NULLS LAST, NAME) AS RANK
    FROM EMPLOYEES E
)
WHERE RANK <= 10;

-- 9. 입사 년도별 급여 합계를 출력하고 전체 급여 합계를 출력하세요.
-- 전체 급여 합계는 가장 아랫줄에 'ALL YEARS'로 표시합니다.
SELECT NVL(TO_CHAR(HIRE_DATE, 'YYYY'), 'ALL YEARS') HIRE_YEAR,
    SUM(SALARY) TOTAL
FROM EMPLOYEES
GROUP BY ROLLUP(TO_CHAR(HIRE_DATE, 'YYYY'))
ORDER BY HIRE_YEAR;

-- 10. 부서별 급여 합계, 직무별 급여 합계, 그리고 전체 급여 합계를 출력하세요.
-- 부서 정보가 NULL인 행은 제외하고 부서별, 직무별로 정렬하며
-- 출력 컬럼은 부서명, 직무 ID, 급여 합계입니다.
SELECT D.DEPARTMENT_NAME,
    E.JOB_ID,
    SUM(SALARY) TOTAL
FROM EMPLOYEES E, DEPARTMENTS D
WHERE E.DEPARTMENT_ID = D.DEPARTMENT_ID
AND E.DEPARTMENT_ID IS NOT NULL
GROUP BY GROUPING SETS(D.DEPARTMENT_NAME, E.JOB_ID, ())
ORDER BY D.DEPARTMENT_NAME, E.JOB_ID;

-- 11. 각 직원의 급여와 해당 직원의 부서에서 
-- 그 직원의 급여보다 100 적은 급여부터 100 큰 급여까지에 해당하는 급여의 합계를 계산하여 출력하세요
-- 출력 컬럼은 부서 ID, 사원 이름, 급여, 계산한 급여 합계입니다.
SELECT DEPARTMENT_ID, NAME, SALARY,
    SUM(SALARY) OVER (PARTITION BY DEPARTMENT_ID ORDER BY SALARY RANGE BETWEEN 100 PRECEDING AND 100 FOLLOWING) SUM_SAL
FROM EMPLOYEES;

-- 12. 각 직무별 평균 급여와 각 직원의 급여를 비교하여 "직무 평균 이상" 또는 "직무 평균 이하" 여부를 표시합니다.
-- 그리고 전체 평균 급여와의 비교 결과도 "전체 평균 이상" 또는 "전체 평균 이하"로 함께 표시합니다.
SELECT JOB_ID, EMPLOYEE_ID, SALARY,
    ROUND(AVG(SALARY) OVER (PARTITION BY JOB_ID),2) DEPT_AVG_SAL,
    ROUND(AVG(SALARY) OVER (), 2) ALL_AVG_SAL,
    CASE
        WHEN SALARY - AVG(SALARY) OVER (PARTITION BY JOB_ID) >= 0 THEN '직무 평균 이상'
        WHEN SALARY - AVG(SALARY) OVER (PARTITION BY JOB_ID) < 0 THEN '직무 평균 이하'
    END DEPT_AVG_COMPARISION,
    CASE
        WHEN SALARY - AVG(SALARY) OVER () >= 0 THEN '전체 평균 이상'
        WHEN SALARY - AVG(SALARY) OVER () < 0 THEN '전체 평균 이하'
    END ALL_AVG_COMPARISION
FROM EMPLOYEES
WHERE DEPARTMENT_ID IS NOT NULL
AND SALARY IS NOT NULL;