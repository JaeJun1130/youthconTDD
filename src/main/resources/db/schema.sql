DROP TABLE IF EXISTS REVIEW;

CREATE TABLE REVIEW
(
    ID INT PRIMARY KEY,
    CONTENT VARCHAR(255),
    PHONE_NUMBER VARCHAR(255),
    SENT BIT(1)
);