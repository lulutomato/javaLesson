/*
    	프로시저 직접 만들어보기 : money_of_day
    	매게변수 IN : 고객ID, 날짜(패턴은 'yyyy-mm-dd')
    	매게변수 OUT: IN으로 받은 값들을 tbl_buy 에서 상품코드를 조회하여 '총 구매 금액' 을 구합니다. 
		*주의 : 매게 변수 선언에는 varchar2 또는 number만 씁니다.*			
					단, 고객은 1일 1품목만 구매 가능 합니다
	1)  고객ID,날짜 값으로 구매 테이블에서 수량과 pcode 조회하고 변수에 저장	
	2)  1)에서 구한 pcode 의 가격을 조회하여 저장하기
	3)  2)번 가격과 1)번 수량을 곱하기 하여 결과값을 변수에 저장 => 출력변수  
        곱셈 수식만 계산할 때에는 dual 임시 테이블로 합니다.
		
*/
CREATE OR REPLACE PROCEDURE money_of_day(
		acustom_id IN tbl_buy.CUSTOMID %TYPE,
		abuydate IN Date,
		amoney OUT NUMBER  
)
IS  
	   vpcode tbl_buy.pcode %TYPE ;
	   vprice NUMBER;
	   vquantity NUMBER;
	   vpcode varchar2;
BEGIN 
--1)	
   SELECT pcode,quantity
   INTO vpcode, vquantity
   FROM  TBL_BUY tb 
   WHERE customid = acustom_id AND to_char(BUY_DATE,'yyyy-mm-dd') = abuydate;
 --2)  
  SELECT PRICE
  INTO vprice
  FROM TBL_PRODUCT tp 
  WHERE pcode =vpcode;

  --3) 출력매게변수에 저장하기
  SELECT vquantity * vprice
  INTO amoney
  FROM dual;
	
	
	
	
	
	
END

 -- 프로시져 실행     
DECLARE
 vmoney number(8);     --일반변수 선언할떄는 () 에 크기 필요
 vid varchar2(20);
 vdate varchar2(20);
BEGIN
 money_of_day('mina012',2023-11-10,vmoney);
 dbms_output.put_line
 (CHR(10 || '고객ID : ' ||vid||'날짜:'|| vdate);
 dbms_output.put_line
('고객님의 구매금액은' || vmoney ||'입니다.');
 END;
 






