/*
 * '단어장' 프로그램을 위한 테이블 
 * 
 * 1.테이블 구조 								
 * 													3.To do :컬럼값들의 규칙 => 요구사항에 대한 제약조건				
 * 		idx     		number(8)					같은 값을 가질수 없다. 반드시 값이 있어야함
 * 		english			varchar2(100)				같은 값을 가질수 없다. 반드시 값이 있어야함
 * 		korean			nvarchar2(100)				반드시 값이 있어야함
 * 		step			number(1)					반드시 값이 없는 null도 가능. 1~4범위로만 한다.
 * 											
 * 			4.To do: 검색 속도 향상시키기 위한 컬럼을 정해라.'데이터행를 식별'할 수 있는 컬럼을 정해라(기본키 설정)		
 * 
 * 2. 테이블에 행 추가 - 데이터 입력하기
 * 	  오라클의 키워드는 테이블명, 컬럼명으로 사용 불가
 * 
 * 
 * 
 * */
CREATE TABLE tbl_javaword(
 	idx number(8),
 	english varchar2(100),
 	korean  nvarchar2(100),
 	step   number(1)
 	);
 	
 
 INSERT INTO TBL_JAVAWORD VALUES(1,'Purple','보라색',1);
 INSERT INTO TBL_JAVAWORD VALUES(2,'Red','빨간색',1);
 INSERT INTO TBL_JAVAWORD VALUES(3,'Yellow','노랑색',1);
 INSERT INTO TBL_JAVAWORD VALUES(4,'Grey','회색',1);
 INSERT INTO TBL_JAVAWORD VALUES(5,'Black','검정색',1);
 SELECT * FROM TBL_JAVAWORD;
--SELECT 컬럼명1,컬럼명2,... FROM 테이블명 => 모든 컬럼을 지정하는 문자는 *(와일드카드 문자)
SELECT ENGLISH ,KOREAN FROM  TBL_JAVAWORD ;
INSERT  INTO TBL_JAVAWORD(idx,english,korean)
VALUES (2,'private','사적인'); -- 입력하지 않은 컬럼 step의 값은 NULL

--SELECT 컬럼명1,컬럼명2,...FROM 테이블명 
						--[WHERE 컬럼명 = 값] => 특정 컬럼을 조건식으로 조회
SELECT * FROM  TBL_JAVAWORD WHERE STEP IS NOT NULL;
SELECT * FROM  TBL_JAVAWORD WHERE STEP IS  NULL;

SELECT * FROM  TBL_JAVAWORD WHERE IDX=2;
SELECT * FROM  TBL_JAVAWORD WHERE ENGLISH = 'public';
SELECT * FROM  TBL_JAVAWORD WHERE ENGLISH = 'public' AND STEP = 2;--AND : 는 논리연산
SELECT * FROM  TBL_JAVAWORD WHERE ENGLISH LIKE '%ur';  ---ur로 끝나는, %기호는 don't care
SELECT * FROM  TBL_JAVAWORD WHERE ENGLISH LIKE '%ur%';  ---ur이 중간에, %기호는 don't care
SELECT * FROM  TBL_JAVAWORD WHERE IDX BETWEEN 1 AND 3; --1~3
SELECT * FROM  TBL_JAVAWORD WHERE IDX NOT BETWEEN 1 AND 3; --1~3 범위값이 아닌것.
SELECT * FROM  TBL_JAVAWORD WHERE ENGLISH < 'Black'; --alphabet 순서







