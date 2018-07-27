DROP TABLE PROFILES;

CREATE TABLE PROFILES (
    userId VARCHAR2(20),
    firstname VARCHAR2(15),
    lastname VARCHAR2(20),
    pssword VARCHAR2(15),
    hst INTEGER CHECK(hst BETWEEN 0 AND 1),
    CONSTRAINT PK_Profile PRIMARY KEY (userId)
);
INSERT INTO PROFILES (userId, firstname, lastname, pssword, hst) VALUES ('JackTorrance', 'Jack', 'Torrance', 'overlook', 1);
SELECT * FROM PROFILES;

COMMIT;