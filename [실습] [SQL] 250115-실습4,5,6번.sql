-- 실습문제 4번
-- 고객 테이블에서 고객 이름을 오름차순 정렬하여 출력하시오.
SELECT NAME 
FROM CUSTOMERS 
ORDER BY NAME;

-- 계좌 테이블에서 잔액이 높은 순으로 계좌 ID와 잔액을 출력하시오.
SELECT ACCOUNT_ID, BALANCE 
FROM ACCOUNTS 
ORDER BY BALANCE DESC;

-- 거래 테이블에서 거래 금액이 낮은 순으로 거래 ID와 금액을 출력하시오.
SELECT TRANSACTION_ID, AMOUNT 
FROM TRANSACTIONS 
ORDER BY AMOUNT;

-- 대출 테이블에서 대출 금액이 높은 순으로 대출 ID와 고객 ID를 출력하시오.
SELECT LOAN_ID, CUSTOMER_ID 
FROM LOANS 
ORDER BY AMOUNT DESC;

-- 실습문제 5번
-- 고객 테이블에서 이름을 오름차순으로, 이메일을 내림차순으로 정렬하여 출력하시오.
SELECT * FROM CUSTOMERS ORDER BY NAME, EMAIL DESC;

-- 계좌 테이블에서 잔액을 내림차순으로 정렬하되 동일 잔액일 경우, 계좌 ID를 오름차순으로 정렬하여 출력하시오.
SELECT * FROM ACCOUNTS ORDER BY BALANCE DESC, ACCOUNT_ID;

-- 거래 테이블에서 거래 유형을 기준으로 오름차순, 거래 금액을 기준으로 내림차순으로 정렬하여 출력하시오.
SELECT * FROM TRANSACTIONS ORDER BY TRANSACTION_TYPE, AMOUNT DESC;

-- 대출 테이블에서 대출 상태를 기준으로 오름차순, 대출 금액을 기준으로 내림차순으로 정렬하여 출력하시오.
SELECT * FROM LOANS ORDER BY STATUS, AMOUNT DESC;

-- 실습문제 6번
-- 승인된 대출(APPROVED)의 총 대출 금액과 해당 대출 건수를 출력하시오.
SELECT SUM(AMOUNT) AS "총 대출 금액", 
    COUNT(*) AS "해당 대출 건수"
FROM LOANS
WHERE STATUS = 'APPROVED';

-- 각 월별 총 거래 금액을 계산하고, 거래 금액이 10,000 이상인 달만 출력하시오.
SELECT TO_CHAR(TRANSACTION_DATE, 'YYYY-MM') AS "거래 월", 
    SUM(AMOUNT) AS "월별 총 거래 금액"
FROM TRANSACTIONS
GROUP BY TO_CHAR(TRANSACTION_DATE, 'YYYY-MM')
HAVING SUM(AMOUNT) >= 10000;

-- 각 지점에서 승인된 대출(APPROVED)의 평균 대출 금액과 총 대출 금액을 계산하시오.
SELECT BRANCH_ID AS "지점",
    AVG(AMOUNT) AS "평균 대출 금액",
    SUM(AMOUNT) AS "총 대출 금액"
FROM LOANS
WHERE STATUS = 'APPROVED'
GROUP BY BRANCH_ID
ORDER BY BRANCH_ID;
