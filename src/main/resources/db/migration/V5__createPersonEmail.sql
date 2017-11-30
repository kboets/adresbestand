CREATE TABLE PERSON_EMAILS (
	PERSON_ID INT(10) NOT NULL,
	EMAIL VARCHAR(250) NOT NULL,
	PRIMARY KEY  (PERSON_ID, EMAIL)
	);


ALTER TABLE PERSON_EMAILS
	ADD CONSTRAINT FOREIGN KEY (PERSON_ID) REFERENCES PERSON (ID);