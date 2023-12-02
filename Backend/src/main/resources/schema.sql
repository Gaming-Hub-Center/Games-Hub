CREATE TABLE admin (
    AdminID int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    AdminName varchar(255),
    ContactNumber varchar(15),
    EmailAddress varchar(255) UNIQUE
);

CREATE TABLE buyer (
    ID int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Name varchar(255),
    Phone varchar(15),
    Email varchar(255) UNIQUE,
    Address varchar(255),
    Password varchar(64),
    Balance float
);

CREATE TABLE seller (
    ID int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Vat_Registration_Number varchar(255),
    Date_Joined date,
    Name varchar(255),
    Phone varchar(15),
    Email varchar(255) UNIQUE,
    Password varchar(64),
    SellerDescription text,
    Balance float,
    NationalID varchar(20),
    Address varchar(255)
);