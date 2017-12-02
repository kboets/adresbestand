CREATE TABLE ADDRESS (
	ID INT(10) NOT NULL AUTO_INCREMENT,
	STREET VARCHAR(250) NOT NULL,
	HOUSENUMBER VARCHAR(50) NOT NULL,
	BOX VARCHAR(50),
	ZIPCODE VARCHAR(50) NOT NULL,
	CITY VARCHAR(50) NOT NULL,
  PRIMARY KEY (ID),
  INDEX IDX_ADDRESS_STREET (STREET),
  INDEX IDX_ADDRESS_CITY (CITY)
	);


CREATE TABLE PERSON (
	ID INT(10) NOT NULL AUTO_INCREMENT,
	LAST_NAME VARCHAR(250) NOT NULL,
	FIRST_NAME  VARCHAR(50) ,
	ADDRESS_ID INT(10) NOT NULL,
	PRIMARY KEY (ID),
	INDEX IDX_PERSON_NAME (LAST_NAME),
	INDEX IDX_PERSON_FIRSTNAME (FIRST_NAME)
	);

-- add Foreign keys --
ALTER TABLE PERSON
	ADD CONSTRAINT FOREIGN KEY (ADDRESS_ID)  REFERENCES ADDRESS (ID);




