/*
 *
 * 	팀별 예시 문제 : 2024-01-25
 * 
 * */

--- A조 ---
-- 김미나가 구매한 상품 금액 합계
SELECT sum(tp.PRICE) "상품 금액 합계", sum(tp.PRICE * tb.QUANTITY) "총 구매 비용"
FROM TBL_BUY tb
JOIN TBL_PRODUCT tp ON tb.PCODE = tp.PCODE AND tb.CUSTOMID = 'mina012';

-- 이름에 '길동'이 들어가는 회원 구매한 상품 구매현황 (권태윤)
SELECT *
FROM TBL_BUY tb 
JOIN TBL_CUSTOM tc ON tb.CUSTOMID = tc.CUSTOM_ID 
WHERE tc.NAME LIKE '%길동';


-- 25살이상 고객님들의 `구매`한 `상품명` 조회하기
SELECT tp.PNAME 
FROM TBL_PRODUCT tp 
JOIN TBL_BUY tb ON tp.PCODE = tb.PCODE
JOIN TBL_CUSTOM tc ON tb.CUSTOMID = tc.CUSTOM_ID
WHERE tc.AGE >= 25
GROUP BY tp.PNAME;

-- 상품명에 '사과' 단어가 포함된 상품을 구매한 고객에 대해 상품별 구매금액의 합을 구하기
SELECT tc.NAME "구매자 이름", sum(tp.PRICE * tb.QUANTITY) "총 구매 가격"
FROM TBL_PRODUCT tp 
JOIN TBL_BUY tb ON tp.PCODE = tb.PCODE AND tp.PNAME LIKE "%사과%"
JOIN TBL_CUSTOM tc ON tb.CUSTOMID = tc.CUSTOM_ID 
GROUP BY tc.NAME;

-- with 구문이용하여 saleMoney를 그룹으로 묶고 그 중 구매합산 금액 중 20000~70000사이 값을 출력하시오
WITH saleMoney
AS (
	SELECT tb.PCODE, tb.QUANTITY * tp.PRICE totalMoney
	FROM TBL_BUY tb 
	JOIN TBL_PRODUCT tp ON tb.PCODE = tp.PCODE
)
SELECT PCODE, sum(totalMoney) "구매금액"
FROM saleMoney
WHERE totalMoney BETWEEN 20000 AND 70000
GROUP BY PCODE;

--- B조 ---
-- 20대 나이 고객의 구매 상품 코드와 나이를 나이순으로 정렬 조회
SELECT NAME, AGE, PCODE
FROM TBL_CUSTOM tc, (
	SELECT tb.CUSTOMID, tp.PCODE
	FROM TBL_BUY tb 
	JOIN TBL_PRODUCT tp ON tb.PCODE = tp.PCODE
) codes WHERE tc.CUSTOM_ID = codes.CUSTOMID AND tc.AGE BETWEEN 20 AND 29
ORDER BY tc.AGE;


-- 2023년 하반기 구매금액을 고객ID별로 조회하시오. 금액이 높은 순서부터 조회하세요
SELECT tc.CUSTOM_ID "고객 ID", sum(tb.QUANTITY * tp.PRICE) "하반기 구매 금액"
FROM TBL_PRODUCT tp 
JOIN TBL_BUY tb ON tp.PCODE = tb.PCODE 
JOIN TBL_CUSTOM tc ON tb.CUSTOMID = tc.CUSTOM_ID
WHERE MONTHS_BETWEEN('2024-01-01', to_char(tb.BUY_DATE, 'yyyy-mm-dd')) < 7
GROUP BY tc.CUSTOM_ID
ORDER BY "하반기 구매 금액" DESC;

-- 2021년 12월1일 부터 2022년 03월17일 사이에 가장 많이 산 사람의 이름 , 물건이름 , 수량순으로 조회하시오
SELECT tc.NAME, tp.PNAME, tb.QUANTITY
FROM TBL_CUSTOM tc, TBL_BUY tb, TBL_PRODUCT tp 
WHERE
	tc.CUSTOM_ID = tb.CUSTOMID AND
	tp.PCODE = tb.PCODE AND
	tb.QUANTITY = (
		SELECT max(tb.QUANTITY)
		FROM TBL_BUY tb 
		WHERE tb.BUY_DATE BETWEEN '2021-12-01' AND '2022-03-17'
	)
GROUP BY tc.NAME, tp.PNAME, tb.QUANTITY;

-- 회원별로 가장 많은 사용금액을 구하시오
SELECT tc.NAME, max(tp.PRICE * tb.QUANTITY) "최고 사용금액"
FROM TBL_PRODUCT tp 
JOIN TBL_BUY tb ON tp.PCODE = tb.PCODE 
JOIN TBL_CUSTOM tc ON tb.CUSTOMID = tc.CUSTOM_ID 
GROUP BY tc.NAME;

-- 2024년에 구매횟수가 1회 이상인 고객id, 고객이름, 나이,이메일을 조회하세요.
SELECT tc.CUSTOM_ID, tc.NAME, tc.AGE, tc.AGE 
FROM TBL_CUSTOM tc 
JOIN TBL_BUY tb ON tc.CUSTOM_ID = tb.CUSTOMID
WHERE tb.QUANTITY >= 1;

-- 구매이력이 2 이상인 고객 중에 나이가 20을 초과한 인원만 조회하세요.
SELECT tc.*, tb.QUANTITY 
FROM TBL_CUSTOM tc 
JOIN TBL_BUY tb ON tc.CUSTOM_ID = tb.CUSTOMID AND tb.QUANTITY >= 2
WHERE tc.AGE > 20;

-- 고객별-상품별 구매금액을 조회하세요. 정렬도 고객ID,상품코드 오름차순으로 정렬하세요.
SELECT tc.CUSTOM_ID, tp.PCODE, tb.QUANTITY * tp.PRICE "고객-상품 별 구매 금액"
FROM TBL_CUSTOM tc 
JOIN TBL_BUY tb ON tc.CUSTOM_ID = tb.CUSTOMID 
JOIN TBL_PRODUCT tp ON tp.PCODE = tb.PCODE 
ORDER BY tc.CUSTOM_ID, tp.PCODE;

-- tbl_student 와 tbl_score 에서 '김모모'라는 이름을 가진 2021001 학번의 점수를 각각 출력하고 평균을 구하시오.
SELECT * FROM TBL_SCORE;
INSERT INTO TBL_SCORE ts VALUES ('2021001', '수학', 80, '김모모', '2022_1');
SELECT * FROM TBL_STUDENT;

SELECT
	st.stuno 학생번호,
	st.name 이름,sc.JUMSU 점수,(SELECT avg(JUMSU) FROM tbl_score sc WHERE
	sc.STUNO='2021001'),sc.SUBJECT 과목 FROM TBL_STUDENT st
INNER JOIN TBL_SCORE sc ON st.stuno = sc.stuno
WHERE st.name = '김모모'


--- D조 ---
-- 진라면을 가장 많이 구매한 회원을 구매금액이 높은 순으로 회원아이디와 총 진라면 구매금액을 보여주세요.
SELECT tc.NAME, sum(tb.QUANTITY * tp.PRICE) total
FROM TBL_PRODUCT tp 
JOIN TBL_BUY tb ON tp.PCODE = tb.PCODE AND tb.PCODE = 'JINRMn5'
JOIN TBL_CUSTOM tc ON tb.CUSTOMID = tc.CUSTOM_ID
WHERE tb.PCODE = 'JINRMn5'
GROUP BY tc.NAME 
ORDER BY total DESC;

--D-2. 판매 갯수가 가장 많은 순서로 상품(상품코드와 상품이름 그룹화)을 정렬하고 총 팔린 금액을 출력하시오.
-- 	 판매 개수가 같으면 상품 코드 순서로 정렬합니다.			ㄴ 동등 조인으로 조회

--D-1. 진라면을 가장 많이 구매한 회원을 구매금액이 높은 순으로 회원아이디와 총 진라면 구매금액을 보여주세요.(조하연)
-- 							ㄴ 서브쿼리 없이 조인만 사용
-- 진라면을 "가장 많이 구매한 회원"이면 한사람 아닌가요?
SELECT tc.CUSTOM_ID, sum(tb.QUANTITY * tp.PRICE) "진라면 구매금액"
FROM TBL_CUSTOM tc 
JOIN TBL_BUY tb ON tc.CUSTOM_ID = tb.CUSTOMID 
JOIN TBL_PRODUCT tp ON tb.PCODE = tp.PCODE
WHERE tp.PCODE = 'JINRMn5'
GROUP BY tc.CUSTOM_ID 
ORDER BY "진라면 구매금액" DESC;

-- 진라면을 구매한 고객들의 평균 나이를 제품코드(PCODE)와 함께출력해 주세요.
SELECT tp.PCODE, avg(tc.AGE) "평균 나이"
FROM TBL_CUSTOM tc 
JOIN TBL_BUY tb ON tc.CUSTOM_ID = tb.CUSTOMID 
JOIN TBL_PRODUCT tp ON tb.PCODE = tp.PCODE
WHERE tp.PCODE = 'JINRMn5'
GROUP BY tp.PCODE;

--D-4. 30세 미만 회원별 구매금액을 구하고 회원으로 그룹바이해서 구매금액 합계가 큰 순으로 정렬
-- 						ㄴ 3개의 테이블 조인
SELECT tc.CUSTOM_ID, sum(tp.PRICE * tb.QUANTITY) "구매금액"
FROM TBL_CUSTOM tc 
JOIN TBL_BUY tb ON tc.CUSTOM_ID = tb.CUSTOMID 
JOIN TBL_PRODUCT tp ON tb.PCODE = tp.PCODE 
WHERE tc.AGE < 30
GROUP BY tc.CUSTOM_ID
ORDER BY "구매금액" DESC;