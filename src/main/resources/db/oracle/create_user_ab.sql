drop user ab cascade;

CREATE USER AB  IDENTIFIED BY ab
  DEFAULT TABLESPACE ADRESBESTAND_TS
  TEMPORARY TABLESPACE TEMP
  PROFILE DEFAULT
  ACCOUNT UNLOCK;
  -- 2 Roles for AB
  GRANT DEVELOPER_ACCESS TO AB;
  GRANT PLUSTRACE TO AB;
  GRANT CREATE ANY PROCEDURE TO AB;
  GRANT CREATE ANY SEQUENCE TO AB;
  GRANT CREATE ANY TABLE TO AB;
  GRANT CREATE ANY VIEW TO AB;
  GRANT CREATE TABLE TO AB;
  GRANT DROP ANY TABLE TO AB;
  GRANT DROP ANY VIEW TO AB;
  GRANT EXECUTE ANY PROCEDURE TO AB;
  GRANT SELECT ANY SEQUENCE TO AB;
  GRANT SELECT ANY TABLE TO AB;
  GRANT CREATE ANY SYNONYM TO AB;
  GRANT CREATE SESSION to AB;

  ALTER USER AB DEFAULT ROLE ALL;
  -- 5 System Privileges for AB
  GRANT CREATE SYNONYM TO AB;
  GRANT CREATE MATERIALIZED VIEW TO AB;
  GRANT CREATE JOB TO AB;
  GRANT DEBUG ANY PROCEDURE TO AB;
  GRANT DEBUG CONNECT SESSION TO AB;
  -- 8 Tablespace Quotas for AB
  ALTER USER AB QUOTA UNLIMITED ON ADRESBESTAND_TS;