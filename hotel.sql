CREATE TABLE PROFILES (
    userId VARCHAR2(20),
    firstname VARCHAR2(15),
    lastname VARCHAR2(20),
    pssword VARCHAR2(15),
    ad INTEGER CHECK(ad BETWEEN 0 AND 1),
    CONSTRAINT PK_Profile PRIMARY KEY (userId)
);
INSERT INTO PROFILES (userId, firstname, lastname, pssword) VALUES ('JackTorrance', 'Jack', 'Torrance', 'overlook');
SELECT * FROM PROFILES;

COMMIT;