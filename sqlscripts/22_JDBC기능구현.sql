--JDBC 프로젝트 위한 SQL 테스트

-- 회원 정보 수정 : email, age
UPDATE TBL_CUSTOM SET email = 'test@gmail.com',age = 22
	WHERE CUSTOM_ID = 'nayeon3';

-- 회원 탈퇴: 
DELETE FROM TBL_CUSTOM tc 
	WHERE CUSTOM_ID = 'sana77;'
	SELECT  * FROM TBL_CUSTOM tc WHERE CUSTOM_ID = "?";
	
--상품 검색하기
--1) 어떤 카테고리가 있는지 보여주기
SELECT DISTINCT CATEGORY  FROM TBL_PRODUCT tp ;  -- 중복된 값은 한번만, (중복제거)
--2) 특정 카테고리에 대해 상품 목록 보여주기
SELECT * FROM TBL_PRODUCT tp 
WHERE CATEGORY = 'B1';

--3) 상품명 유사(like)검색
SELECT *FROM TBL_PRODUCT tp 
WHERE PNAME LIKE '%'||'사과'||'%';				--프로그램에서 매개변수 처리를 위해 연결연산으로 합니다.