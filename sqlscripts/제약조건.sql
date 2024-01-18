/*
 * 제약 조건 constraint - 테이블에 저장되는 '요구사항에 대한 부적절한 값'을 갖지 않도록 규정.
 * 1)반드시 값이 있어야한다  null 허용안함.
 * 2)같은 값을 가질수 없다	UNIQUE          *무결성 : 데이터 정확성 유지
 * 3)1~4 범위로만 한다		CHECK
 * ------------------
 * 4)기본키
 * 4)....
 * 
 * */
--제약조건 1컬럼레벨 설정하기
CREATE TABLE tbl1_constr( 
   aname varchar2(20) NOT NULL,
   bname varchar2(20) UNIQUE, --NULL 허용
   cno NUMBER(4) NOT NULL UNIQUE,
   dno NUMBER(4) CHECK (dno BETWEEN 1 AND 4)
);

INSERT INTO TBL1_CONSTR (aname,cno)VAlUES('apple',3);  --필수 입력 컬럼
--1) 입력 오류 발생하는 경우 : unique 위반
--INSERT INTO TBL1_CONSTR (aname,bname,cno) VALUES('apple','banana',3); -- cno 컬럼값 중복
INSERT INTO TBL1_CONSTR (aname,bname,cno) VALUES('apple','banana',4); 
--INSERT INTO TBL1_CONSTR (aname,bname,cno) VALUES('apple','banana',5); -- bname 컬럼값 중복
SELECT * FROM TBL1_CONSTR ;

--2) 입력 오류 발생하는 경우 : not null 위반

INSERT INTO TBL1_CONSTR  (cno) Values(3);

--3) 입력 오류 발생하는 경우 : check 위반

INSERT INTO  TBL1_CONSTR (aname,cno,dno)VALUES ('apple',7,3);
INSERT INTO  TBL1_CONSTR (aname,cno,dno)VALUES ('apple',3,13);


