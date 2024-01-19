CREATE TABLE tbl_excustomer(
	ID varchar (20) PRIMARY key, 
	name nvarchar2 (10) NOT null,
	email nvarchar2 (30),
	age NUMBER(10),
	join_date DATE DEFAULT sysdate
);

CREATE TABLE tbl_exproduct(
	code nvarchar2(10),
	category nvarchar2(10)check(category IN ('A1','A2','B1','B2')) NOT null,
	pd_name nvarchar2(10) UNIQUE NOT null,
	price number(30) NOT NULL,
	PRIMARY KEY
	(code)
);
TRUNCATE TABLE TBL_EXPRODUCT; 

CREATE TABLE tbl_exbuy(
	ID varchar (20) NOT null,
	code nvarchar2(10) NOT null,
	b_quantity number(20) NOT null,
	buy DATE DEFAULT sysdate NOT NULL,
	FOREIGN key(ID)
	REFERENCES  tbl_excustomer(ID),
	FOREIGN KEY(code)
	REFERENCES tbl_exproduct
	(code)
);
TRUNCATE TABLE tbl_exbuy;
INSERT INTO TBL_EXCUSTOMER te values 
('mina012','김미나','kimm@gmail.com',20,TO_date('2022-03-10 14:23:25','yyyy-mm-dd hh24:mi:ss')); 
INSERT INTO TBL_EXCUSTOMER te values 
('hongGD','홍길동','gil@korea.com',32,TO_date('2021-10-21 11:12:23','yyyy-mm-dd hh24:mi:ss'));
INSERT INTO TBL_EXCUSTOMER te values 
('twice','박모모','momo@daum.net',29,TO_date('2021-12-25 19:23:45','yyyy-mm-dd hh24:mi:ss'));
INSERT INTO TBL_EXCUSTOMER te values 
('wonder','이나나','lee@naver.com',40,sysdate);
SELECT * FROM TBL_EXCUSTOMER te;


INSERT INTO tbl_exproduct VALUES 
('DOWON123a','B2','동원참치선물세트',54000);
INSERT INTO tbl_exproduct VALUES 
('CJBAb12g','B1','햇반 12개입',14500);
INSERT INTO tbl_exproduct VALUES 
('JINRMn5','B1','진라면 5개입',6350);
INSERT INTO tbl_exproduct VALUES 
('APLE5kg','A1','청송사과 5kg',66000);
INSERT INTO tbl_exproduct VALUES 
('MANGOTK4r','A2','애플망고 1kg',32000);
SELECT * FROM TBL_EXPRODUCT te ;

INSERT INTO TBL_EXBUY te  values
('mina012','CJBAb12g',5,TO_date('2022-03-10 14:33:15','yyyy-mm-dd hh24:mi:ss'));
INSERT INTO TBL_EXBUY te  values
('mina012','APLE5kg',2,TO_date('2022-03-10 14:33:15','yyyy-mm-dd hh24:mi:ss'));
INSERT INTO TBL_EXBUY te  values
('mina012','JINRMn5',2,TO_date('2022-04-16 10:13:15','yyyy-mm-dd hh24:mi:ss'));
INSERT INTO TBL_EXBUY te  values
('twice','JINRMn5',3,TO_date('2021-12-25 19:32:15','yyyy-mm-dd hh24:mi:ss'));
INSERT INTO TBL_EXBUY te  values
('twice','MANGOTK4r',2,TO_date('2021-12-25 19:32:15','yyyy-mm-dd hh24:mi:ss'));
INSERT INTO TBL_EXBUY te  values
('hongGD','DOWON123a',1,TO_date('2021-10-21 11:13:25','yyyy-mm-dd hh24:mi:ss'));
INSERT INTO TBL_EXBUY te  values
('hongGD','APLE5kg',1,TO_date('2022-04-21 11:13:25','yyyy-mm-dd hh24:mi:ss'));
SELECT * FROM TBL_EXBUY te;

Select * from TBL_EXCUSTOMER te  where months_between(SYSDATE , JOIN_DATE) >=24;
SELECT * FROM TBL_EXCUSTOMER te  WHERE email LIKE '%gmail%';
SELECT TO_date(sysdate,'yyyy-mm-dd')-to_date('2022-03-10 14:23:25','yyyy-mm-dd hh24:mi:ss')FROM TBL_EXCUSTOMER te
WHERE ID = 'mina012';--조하연
SELECT CATEGORY ,count(*)FROM TBL_EXPRODUCT te GROUP BY CATEGORY;--황병훈
SELECT * FROM TBL_EXBUY te  WHERE ID = 'twice';--한진만
SELECT code, sum(b_quantity) AS "BC" FROM TBL_EXBUY te GROUP by code ORDER BY "BC" DESC ; --한주영
SELECT * FROM TBL_EXCUSTOMER te ORDER BY NAME DESC ; --조지수