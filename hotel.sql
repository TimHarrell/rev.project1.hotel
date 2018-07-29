DROP TABLE PROFILES;

CREATE TABLE PROFILES (
    userId VARCHAR2(20),
    firstname VARCHAR2(15),
    lastname VARCHAR2(20),
    pssword VARCHAR2(15),
    hst INTEGER CHECK(hst BETWEEN 0 AND 1),
    CONSTRAINT PK_Profile PRIMARY KEY (userId)
);

CREATE TABLE RESERVATIONS (
    reservationDate DATE,
    userId VARCHAR(20),
    roomNumber INTEGER,
    CONSTRAINT FK_userId FOREIGN KEY (userId) REFERENCES PROFILS(userId)
);
CREATE TABLE ROOMS (
    roomNumber INTEGER,
    numBeds INTEGER CHECK(numBeds BETWEEN 1 AND 2),
    smoking INTEGER CHECK(smoking BETWEEN 0 AND 1)
);

CREATE TABLE PENDING_RESERVATIONS (
    transactionNumber INTEGER,
    userId VARCHAR(20),
    reservationDate DATE,
    roomNumber INTEGER,
    CONSTRAINT PK_transactionNumber PRIMARY KEY (transaction_number),
    CONSTRAINT FK_userId FOREIGN KEY(userId) REFERENCES PROFILES(userId)
)

CREATE SEQUENCE seq_transactionNumber MINVALUE 1 START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER transaction_on_insert
  BEFORE INSERT ON PENDING_RESERVATIONS
  FOR EACH ROW
BEGIN
  SELECT seq_transactionNumber.nextval
  INTO :new.id
  FROM dual;
END;

INSERT INTO PROFILES (userId, firstname, lastname, pssword, hst) VALUES ('JackTorrance', 'Jack', 'Torrance', 'overlook', 1);
SELECT * FROM PROFILES;

COMMIT;