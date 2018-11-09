DROP TABLE IF EXISTS CITIZENS;
CREATE TABLE CITIZENS (
    ID LONG NOT NULL,
    FIRSTNAME CHAR(100) NOT NULL,
    SURNAME CHAR(100) NOT NULL,
    CREATED_AT TIMESTAMP NOT NULL,
    UPDATED_AT TIMESTAMP NULL,
    CONSTRAINT CITIZENS_PK PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS EMAILADDRESSES;
CREATE TABLE EMAILADDRESSES (
    ID LONG NOT NULL,
    EMAILADDRESSES VARCHAR(254) NOT NULL,
    CREATED_AT TIMESTAMP NOT NULL,
    UPDATED_AT TIMESTAMP NULL
    CONSTRAINT EMAILADDRESSES_PK PRIMARY KEY (ID)
);


ALTER TABLE EMAILADDRESSES ADD CONSTRAINT EMAILADDRESSES_CITIZENS FOREIGN KEY EMAILADDRESSES_CITIZENS (CITIZENS_ID)
    REFERENCES CITIZENS (ID);
    
INSERT INTO CITIZENS (CREATED_AT, UPDATED_AT, FIRSTNAME, SURNAME) VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'WIESLAW', 'BATYSKAF');