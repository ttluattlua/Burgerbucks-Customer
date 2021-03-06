--Admin 테이블

SELECT * FROM BB_ADMIN;

DROP TABLE BB_ADMIN
CASCADE CONSTRAINT;

DROP SEQUENCE BB_ADMIN_SEQ;

CREATE TABLE BB_ADMIN(
  SEQ NUMBER(10) PRIMARY KEY,
  ID VARCHAR2(20) UNIQUE,
  PASSWORD VARCHAR2(20) NOT NULL,
  NAME VARCHAR2(20) NOT NULL,
  PHONE VARCHAR2(20) NOT NULL,
  STORE_SEQ NUMBER(10) NOT NULL,
  DEL NUMBER(1) NOT NULL
);

CREATE SEQUENCE BB_ADMIN_SEQ
START WITH 1
INCREMENT BY 1;

ALTER TABLE BB_ADMIN
ADD CONSTRAINT FK_BB_STORE_SEQ FOREIGN KEY(STORE_SEQ)
REFERENCES BB_STORE(SEQ);

INSERT INTO BB_ADMIN(SEQ, ID, PASSWORD, NAME, PHONE, STORE_SEQ, DEL)
VALUES ( BB_ADMIN_SEQ.NEXTVAL,'admin', 'admin', 'admin', '111-1111-1111', 1, 0 )










