CREATE TABLE ADDRESS (
	ID NUMBER(10,0) NOT NULL,
	STREET VARCHAR2(250 BYTE) NOT NULL,
	HOUSENUMBER VARCHAR2(50 BYTE) NOT NULL,
	BOX VARCHAR2(50 BYTE),
	ZIPCODE VARCHAR2(50 BYTE) NOT NULL,
	CITY VARCHAR2(50 BYTE) NOT NULL
	)
TABLESPACE ADRESBESTAND_TS;

CREATE TABLE PERSON (
	ID NUMBER(10,0) NOT NULL,
	LAST_NAME VARCHAR2(250 BYTE) NOT NULL,
	FIRST_NAME  VARCHAR2(50 BYTE) NOT NULL,
	ADDRESS_ID NUMBER(10,0) NOT NULL
	)
TABLESPACE ADRESBESTAND_TS;

-- add Primary keys --
ALTER TABLE ADDRESS ADD (CONSTRAINT PK_ADDRESS PRIMARY KEY  (ID)
USING INDEX  TABLESPACE ADRESBESTAND_TS);

ALTER TABLE PERSON ADD (CONSTRAINT PK_PERSON PRIMARY KEY  (ID)
USING INDEX  TABLESPACE ADRESBESTAND_TS);

-- add Foreign keys --
ALTER TABLE PERSON
	ADD (CONSTRAINT FK_PERSON_ADDRESS_ID FOREIGN KEY (ID)
	  REFERENCES ADDRESS (ID));

-- add unique keys --
ALTER TABLE ADDRESS
   ADD CONSTRAINT addressUniqueConstraint UNIQUE(CITY, STREET, HOUSENUMBER, BOX);

--- add INDEXEN --
CREATE UNIQUE INDEX IDX_PERSON_NAME ON PERSON (LAST_NAME) TABLESPACE ADRESBESTAND_TS;
CREATE UNIQUE INDEX IDX_PERSON_FIRSTNAME ON PERSON (FIRST_NAME) TABLESPACE ADRESBESTAND_TS;
CREATE UNIQUE INDEX IDX_ADDRESS_STREET ON ADDRESS (STREET) TABLESPACE ADRESBESTAND_TS;
CREATE UNIQUE INDEX IDX_ADDRESS_CITY ON ADDRESS (CITY) TABLESPACE ADRESBESTAND_TS;

--- add sequences --
CREATE SEQUENCE ADDRESS_S
	INCREMENT BY 1
	START WITH 1
	NOMAXVALUE
 	NOMINVALUE
 	NOCYCLE;


CREATE SEQUENCE PERSON_S
	INCREMENT BY 1
	START WITH 1
	NOMAXVALUE
 	NOMINVALUE
	NOCYCLE;

