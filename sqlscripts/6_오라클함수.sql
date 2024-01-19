-- 수업 날짜 : 1월19일 

-- 실제로는 테이블 컬럼으로 함수 실행합니다. select lower (컬럼명) = from 테이블명;

-- 1.문자열 함수
SELECT INITCAP('hello')			-- 예상 : Hello
				FROM dual;  --initial capital : 첫번째 대문자
SELECT upper('hello') FROM dual;  --대문자로 변환,HELLO
SELECT lower('hello') FROM dual;  --소문자로 변환,hello
SELECT length('hello')FROM dual;  --문자열 길이,6
SELECT SUBSTR('java program',3,5)  --부분 추출(문자열,위치,길이) 결과 :
			  FROM dual;         -- *오라클에서 문자열 위치 인덱스는 1부터 시작.*
SELECT SUBSTR('java program',-5,3)  --부분 추출 결과: 위치음수이면 뒤에서부터 . 문자열 뒤에서부터 5번째 3개			
			  FROM dual;
SELECT REPLACE ('java program','pro','프로') -- 문자열 바꾸기.java 프로gram
			  FROM dual;
SELECT instr('java program','og')   --자바의 indexOf
   			  FROM dual;            -- 결과 8. 해당 문자열이 없으면 결과 0
SELECT trim('java program    ')     --공백 (불필요한 앞뒤 공백) 제거
   			  FROM dual;            --
SELECT LENGTH (' java program    ')FROM dual; --공백포함 17
SELECT LENGTH (trim('  java program    '))
			  FROM dual;                     -- 공백 제거 후에는 12
			  
-- 2.숫자 함수(정수 또는 실수 number를 대상으로 하는 함수)
-- abs(n) : 절대값
-- trunc(숫자,자리수) : 소수점 이하 자리수 맞추기 위해서 버림 3.177567 -> 3.17
SELECT trunc(3.177567,2)FROM dual;

-- round (숫자,자리수) :          	      반올림 3.177567 -> 3.18
SELECT round(3.177567,2)FROM dual;

-- ceil(숫자) : 실수를 정수로 올림으로 변환
SELECT CEIL(3.177567)FROM dual;     --결과값 : 4

--floor(숫자) : 실수를 정수로 내림으로 변환
SELECT floor(3.177567)FROM dual;    --결과값 : 3

--3.날짜 함수 : to_char(날짜형식을 문자열로 변환) 와 to_date (문자열을 날짜형식으로 변환)
SELECT SYSDATE, SYSTIMESTAMP FROM dual;    -- systimestamp 는 표준시와의 시차(타임존) 표시
SELECT TO_char(SYSDATE,'yyyy-mm-dd')FROM dual;
SELECT TO_char(SYSDATE,'yyyy"년" mm"월" dd"일"')FROM dual;

-- yyyy-mm-dd hh24:mi:ss 24시간 기준
-- yyyy-mm-dd hh:mi:ss am(pm) 12시간 기준

-- TBL_MEMBER 테이블에 JOIN_DATE 칼럼 insert
--자동 캐스팅 패턴 yyyy-mm-dd 만 가능합니다.

INSERT INTO TBL_MEMBER VALUES (2,'박나연','parkny@gmail.com','2022-10-24 13:24:55'); --오류
-- ***** 저장할 날짜 문자열을 날짜 패턴을 알려주고 날짜형식으로 변경하기
INSERT INTO TBL_MEMBER VALUES (2,'박나연','parkny@gmail.com',to_date('2022-10-24 13:24:55','yyyy-mm-dd hh24:mi:ss'));

INSERT INTO TBL_MEMBER VALUES (10,'홍길동','gdHong@daum.net',to_date('20241019','yyyymmdd'));
SELECT *FROM TBL_MEMBER;

SELECT ADD_MONTHS(SYSDATE,3)    -- 오늘 날짜 3개월 이후, 첫번째 인자는 날짜 형식, 두번째 더해지는 값 
		FROM dual;
	
SELECT TO_CHAR(ADD_MONTHS(SYSDATE,3),'YYYY/MM/DD') -- 문자열패턴 기호 - 또는 / 또는 구분기호 없음 가능 
		FROM dual;

SELECT MONTHS_BETWEEN(SYSDATE , TO_DATE('2020-07-10')) -- 지정된 2개의 날짜 사이에 간격(월), 결과는 소수점
       FROM dual;
      
SELECT TRUNC(SYSDATE)-TO_DATE('19910626','YYYYMMDD')  --2개의 날짜형식 값 간격(일)
	   FROM dual;   -- 2개의 날짜 간격(일). TRUNC(SYSDATE)는 일(day)까지로 변환
SELECT TRUNC(SYSDATE) FROM dual;  
SELECT  SYSDATE + 5 FROM dual;
SELECT  SYSDATE + 15 FROM dual;






















