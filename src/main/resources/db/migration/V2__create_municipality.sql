CREATE TABLE MUNICIPALITY (
	ID INT(10) NOT NULL,
	ZIPCODE INT(4) NOT NULL,
	CITY VARCHAR(100) NOT NULL,
	PRIMARY KEY (ID)
	);



CREATE SEQUENCE MUNICIPALITY_S
	INCREMENT BY 1
	START WITH 1
	NOMAXVALUE
 	NOMINVALUE
 	NOCYCLE;

