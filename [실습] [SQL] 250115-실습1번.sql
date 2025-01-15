-- 실습문제 1번
-- 고객 테이블에서 모든 고객의 이름과 이메일을 출력하시오.
SELECT NAME, EMAIL FROM CUSTOMERS;

-- 계좌 테이블에서 모든 계좌의 계좌 ID와 잔액을 출력하시오.
SELECT ACCOUNT_ID, BALANCE FROM ACCOUNTS;

-- 거래 테이블에서 모든 거래의 금액과 거래 유형을 출력하시오.
SELECT AMOUNT, TRANSACTION_TYPE FROM TRANSACTIONS;

-- 실습문제 2번
-- 고객 테이블에서 특정 주소("Address 100")를 가진 고객의 이름과 전화번호를 출력하시오.
SELECT NAME, PHONE 
FROM CUSTOMERS
WHERE ADDRESS = 'Address 100';

-- 계좌 테이블에서 잔액이 50,000 이상인 계좌의 계좌 ID와 잔액을 출력하시오.
SELECT ACCOUNT_ID, BALANCE
FROM ACCOUNTS
WHERE BALANCE >= 50000;

-- 거래 테이블에서 거래 금액이 음수인 거래의 ID와 금액을 출력하시오.
SELECT TRANSACTION_ID, AMOUNT
FROM TRANSACTIONS
WHERE AMOUNT < 0;

-- 대출 테이블에서 상태가 "APPROVED"인 대출의 대출 금액과 고객 ID를 출력하시오.
SELECT AMOUNT, CUSTOMER_ID
FROM LOANS
WHERE STATUS = 'APPROVED';

-- 실습문제 3번
