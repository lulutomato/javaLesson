/*
   서브 쿼리(sub query) : select 조회 결과(여러개의 행)가 다른 DML 명령어에 쓰일 수 있습니다.
   												   ㄴ select,insert,update,delete
   					select,insert,update,delete 포함되는 select 를 서브 쿼리라고 합니다.
   	
   	select 함께 쓰이는 서브쿼리
   					: select 컬럼1, 컬럼2 from (select......)
   									   where 컬럼명1 = 값(select......)
   	select 서브쿼리는 join 으로도 가능합니다.
*/
--1) where 에서 쓰는 서브쿼리 : twice가 구매한 상품 정보
--join
SELECT tp.*
FROM TBL_BUY tb , TBL_PRODUCT tp 
WHERE tb.PCODE = tp.PCODE        -- 1)크로스 연산
AND tb.CUSTOMID = 'twice';		 -- 2)조건식 연산

--서브쿼리
SELECT tp.*
FROM TBL_PRODUCT tp 
WHERE PCODE IN     				--2) 조건식 연산
-- ('CJBAb12g','APLE5kg','DOWON123a');
(SELECT PCODE FROM TBL_BUY tb WHERE tb.CUSTOMID='twice'); -- 1) 조건식 연산 

--from 에서 사용되는 서브 쿼리 : 컬럼명 또는 테이블명 별칭을 줄때 한글을 쓰려면 ""안에 사용합니다.
select
		saleMoney.PCODE, sum(QUANTITY),sum("구매 금액")
From
	 (SELECT tp.PCODE, PNAME, PRICE, QUANTITY ,QUANTITY * PRICE "구매 금액"
	 FROM TBL_BUY tb , TBL_PRODUCT tp 
 	 WHERE tb.PCODE = tp.PCODE) saleMoney
GROUP BY saleMoney.PCODE;

--오라클 with 구문 : select 조회 결과를 저장해 놓고 사용하도록 합니다.
WITH saleMoney 
AS 
(
	 SELECT tp.PCODE, PNAME, PRICE, QUANTITY ,QUANTITY * PRICE "구매 금액"
	 FROM TBL_BUY tb , TBL_PRODUCT tp 
 	 WHERE tb.PCODE = tp.PCODE
)
SELECT saleMoney.PCODE, sum(salemoney.quantity),sum("구매 금액")
FROM SALEMONEY
GROUP BY SALEMONEY.pcode;



--2)를 join으로 
SELECT 
    tp.PCODE,
    SUM(tb.QUANTITY) AS TotalQuantity,
    SUM(tp.PRICE * tb.QUANTITY) AS TotalAmount
FROM 
    TBL_PRODUCT tp
JOIN 
    TBL_BUY tb ON tp.PCODE = tb.PCODE
GROUP BY 
    tp.PCODE;
-- 회원별로 구매금액이 가장 큰 상품은?
   
   
   
 --서브쿼리와 조인을 이용한 문제를 한개씩 만들어서 공유
  --가장 많이 팔린 품목은 무엇이며 얼마나 나갔을까?
 WITH saleMoney
 AS 
 (
 SELECT tp.Pcode,PNAME,PRICE,QUANTITY,QUANTITY* price"구매 금액"
   FROM TBL_PRODUCT tp , TBL_BUY tb 
   WHERE tp.PCODE = tb.PCODE
   )
 SELECT saleMoney.PCODE, SUM(saleMoney.),SUM("구매 금액")
 FROM saleMoney
 GROUP BY saleMoney.pcode;





SELECT tp.Pcode,PNAME,PRICE,QUANTITY
FROM TBL_PRODUCT tp , TBL_BUY tb
WHERE tp.Pcode =tb.Pcode;
SELECT min(quantity), tb.Pcode 
FROM TBL_BUY tb
--LEFT JOIN TBL_PRODUCT tp ON tb.PCODE = tp.PCODE
GROUP BY tb.Pcode;

SELECT dongwon.CUSTOMID, dongwon.Price1 "가격", dongwon.quantity "개수" 
FROM 
(
   SELECT tb.customid, tb.quantity, tp.price,  tp.price* tb.quantity AS Price1
   FROM TBL_PRODUCT tp,
       tbl_buy tb
   WHERE   tp.pcode = tb.pcode
      AND tp.pcode = 'CJBAb12g'
)dongwon;

SELECT 

--고객별로 구매 금액이 가장 높은 상품코드를 조회해 봅시다.
-- ㄴ RANK 함수            (그룹화 결과에 대한 순위를 제공)

-- 내림차순 또는 오름차순 정렬된 결과에서 top3 조회하기
 



