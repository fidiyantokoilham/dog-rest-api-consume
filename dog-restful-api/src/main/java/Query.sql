CREATE TABLE BREED (
    BREED_ID int NOT NULL ,
    NAME varchar(255) NOT NULL,
    PRIMARY KEY (BREED_ID)
);

CREATE TABLE SUB_BREED (
    SUB_BREED_ID int NOT NULL ,
    FK_BREED_ID int NOT NULL,
    NAME varchar(255) NOT NULL,
    PRIMARY KEY (SUB_BREED_ID)
);

CREATE TABLE IMAGE (
    IMAGE_ID   int NOT NULL ,
    FK_BREED_ID int NOT NULL,
    URL varchar(255) NOT NULL,
    PRIMARY KEY (IMAGE_ID)
);