--BB_BEVERAGE

SELECT * FROM BB_BEVERAGE;

DROP TABLE BB_BEVERAGE
CASCADE CONSTRAINT;

DROP SEQUENCE BB_BEVERAGE_SEQ;

CREATE TABLE BB_BEVERAGE(
	SEQ NUMBER(10) PRIMARY KEY,
	NAME VARCHAR2(100) NOT NULL,
	PRICE NUMBER(20) NOT NULL,
	CAL NUMBER(20) NOT NULL,
	DEL NUMBER(1) NOT NULL
);

CREATE SEQUENCE BB_BEVERAGE_SEQ
START WITH 1
INCREMENT BY 1;



