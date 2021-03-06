DROP TABLE IF EXISTS CITIZENS;
CREATE TABLE CITIZENS (
    ID LONG NOT NULL AUTO_INCREMENT,
    FIRSTNAME CHAR(100) NOT NULL,
    SURNAME CHAR(100) NOT NULL,
    CREATED_AT TIMESTAMP NOT NULL,
    UPDATED_AT TIMESTAMP NULL,
    CONSTRAINT CITIZENS_PK PRIMARY KEY (ID)
);
DROP TABLE IF EXISTS EMAILADDRESSES;
CREATE TABLE EMAILADDRESSES (
    ID LONG NOT NULL AUTO_INCREMENT,
    EMAIL_ADDRESS VARCHAR(100) NOT NULL,
    CREATED_AT TIMESTAMP NOT NULL,
    UPDATED_AT TIMESTAMP NULL,
    CITIZENS_ID LONG,
    CONSTRAINT EMAILADDRESSES_PK PRIMARY KEY (ID)
);
ALTER TABLE EMAILADDRESSES ADD CONSTRAINT EMAILADDRESSES_CITIZENS FOREIGN KEY (CITIZENS_ID) REFERENCES CITIZENS (ID)