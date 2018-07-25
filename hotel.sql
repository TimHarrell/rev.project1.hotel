CREATE TABLE PROFILES (
    userId VARCHAR2(20),
    firstname VARCHAR2(15),
    lastname VARCHAR2(20),
    pssword VARCHAR2(15),
    CONSTRAINT PK_Profile PRIMARY KEY (userId)
);

COMMIT;