--BB_STORE 테이블

SELECT * FROM BB_STORE;

DROP TABLE BB_STORE
CASCADE CONSTRAINT;

DROP SEQUENCE BB_STORE_SEQ;

CREATE TABLE BB_STORE(
	SEQ NUMBER(10) PRIMARY KEY,
	NAME VARCHAR2(100) NOT NULL,
	PHONE VARCHAR2(20) NOT NULL,
	ADDRESS VARCHAR2(200) NOT NULL,
	LAT VARCHAR2(100) NOT NULL,
	LNG VARCHAR2(100) NOT NULL,
	DEL NUMBER(1) NOT NULL
);

CREATE SEQUENCE BB_STORE_SEQ
START WITH 1
INCREMENT BY 1;

INSERT INTO BB_STORE
VALUES ( BB_STORE_SEQ.NEXTVAL,'A', '123-1234-1234', '서울시', 1, 1, 0 );







