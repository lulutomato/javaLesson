/*
 * 		SQL의 집계(개수,최대값,최소값,합계,평균) 함수 - 그룹 함수
 * 										   ㄴ GROUP BY 구문에서 많이 사용됩니다.
 * 										   ㄴ 집계함수는 다른 컬럼과 함께 조회하려면 group by 가 필요	
 * */
SELECT count(*) "count",sum(JUMSU)"합",avg(JUMSU)"평균"FROM TBL_SCORE ts ;
-- 1) '성적' 테이블의 전체 행 개수
SELECT COUNT(*) AS "count" FROM TBL_SCORE; 

-- 조회된 함수 결과의 컬럼명을 주려면 as(생략가능) "컬럼명"으로 합니다.
-- 2) '성적' 테이블의 과목 컬럼 '국어' 값을 갖는 전체 행 개수
SELECT COUNT(*)FROM TBL_SCORE WHERE SUBJECT  = '국어';

--3) '성적' 테이블의 'jumsu' 컬럼의 합계
SELECT SUM(JUMSU) FROM TBL_SCORE ts ;
--4)'성적' 테이블의 '국어' 과목 'jumsu'컬럼의 합계
SELECT SUM(JUMSU) FROM TBL_SCORE ts WHERE SUBJECT = '국어' ;
--5)평균값
SELECT AVG(JUMSU)FROM TBL_SCORE ts;
--6)국어의 평균값
SELECT AVG(JUMSU)FROM TBL_SCORE ts WHERE SUBJECT = '국어'; 
--7)최대값
SELECT Max(JUMSU)FROM TBL_SCORE ts;
--8)영어 과목 최대값
SELECT Max(JUMSU)FROM TBL_SCORE ts WHERE SUBJECT = '영어';






-- 오류 : 집계함수는 다른 컬럼과 함께 조회하려면 group by 가 필요
--SELECT subject , COUNT(*) FROM TBL_SCORE ts ; 
--SELECT SUBJECT , COUNT(*)FROM TBL_SCORE WHERE SUBJECT  = '국어';

-- Group BY SUBJECT 구문이 있으므로 조회 컬럼에 subject를 추가할 수 있습니다.
SELECT SUBJECT, count(*) FROM TBL_SCORE ts GROUP BY SUBJECT;

---- Group BY SUBJECT 구문이 있으므로 조회 컬럼에 stuno를 추가할 수 없습니다.
SELECT SUBJECT count(*) FROM TBL_SCORE ts GROUP BY STUNO;


--GROUP BY는 그룹화 컬럼명1, 그룹화컬럼명2,,..... 로 할수 있습니다.
