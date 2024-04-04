-- MySQL Script generated by MySQL Workbench
-- Fri Dec 8 19:52:17 2023
-- Model: New Model Version: 1.0
-- MySQL Workbench Forward Engineering

-- -----------------------------------------------------
-- Schema GamesHub
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `GamesHub`;

CREATE SCHEMA IF NOT EXISTS `GamesHub` DEFAULT CHARACTER SET utf8 ;
USE `GamesHub` ;


-- -----------------------------------------------------
-- Table `GamesHub`.`Buyer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GamesHub`.`Buyer` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NULL,
  `Phone` VARCHAR(15) NULL,
  `Email` VARCHAR(255) NOT NULL,
  `Address` VARCHAR(255) NULL,
  `Password` VARCHAR(72) NOT NULL,
  `Balance` FLOAT NOT NULL,
  UNIQUE INDEX `EmailUnique` (`Email` ASC) VISIBLE,
  PRIMARY KEY (`Id`)
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamesHub`.`Seller`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GamesHub`.`Seller` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NULL,
  `Phone` VARCHAR(15) NULL,
  `Email` VARCHAR(255) NOT NULL,
  `Address` VARCHAR(255) NULL,
  `Password` VARCHAR(72) NOT NULL,
  `Balance` FLOAT NOT NULL,
  `NationalId` VARCHAR(20) NOT NULL,
  `VatRegistrationNumber` VARCHAR(255) NOT NULL,
  `DateJoined` DATE NULL,
  `SellerDescription` MEDIUMTEXT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `EmailUnique` (`Email` ASC) VISIBLE
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamesHub`.`Admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GamesHub`.`Admin` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NULL,
  `Phone` VARCHAR(15) NULL,
  `Email` VARCHAR(255) NOT NULL,
  `Password` VARCHAR(72) NOT NULL,
  UNIQUE INDEX `EmailUnique` (`Email` ASC) VISIBLE,
  PRIMARY KEY (`Id`)
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamesHub`.`PhysicalProduct`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GamesHub`.`PhysicalProduct` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Title` VARCHAR(255),
  `Price` FLOAT,
  `Description` TEXT,
  `SellerId` INT NOT NULL,
  `Count` INT,
  `Category` VARCHAR(20),
  `PostDate` DATE NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `SellerIdIdx` (`SellerId` ASC) VISIBLE,
  CONSTRAINT `SellerIdFK1`
    FOREIGN KEY (`SellerId`)
    REFERENCES `GamesHub`.`Seller` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamesHub`.`DigitalProduct`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GamesHub`.`DigitalProduct` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Title` VARCHAR(255),
  `Price` FLOAT,
  `Description` TEXT,
  `SellerId` INT NOT NULL,
  `Count` INT,
<<<<<<< Updated upstream
  `Category` VARCHAR(20),
  `Code` VARCHAR(100) NOT NULL,
=======
  `Category` VARCHAR(50),
>>>>>>> Stashed changes
  `PostDate` DATE NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `SellerIdIdx` (`SellerId` ASC) VISIBLE,
  CONSTRAINT `SellerIdFK2`
    FOREIGN KEY (`SellerId`)
    REFERENCES `GamesHub`.`Seller` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamesHub`.`PhysicalCart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GamesHub`.`PhysicalCart` (
  `BuyerId` INT NOT NULL,
  `ProductId` INT NOT NULL,
  `Count` INT NOT NULL,
  PRIMARY KEY (`BuyerId`, `ProductId`),
  INDEX `ProductIdIdx` (`ProductId` ASC) VISIBLE,
  CONSTRAINT `ProductIdFK1`
    FOREIGN KEY (`ProductId`)
    REFERENCES `GamesHub`.`PhysicalProduct` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `BuyerIdFK1`
    FOREIGN KEY (`BuyerId`)
    REFERENCES `GamesHub`.`Buyer` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamesHub`.`DigitalCart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GamesHub`.`DigitalCart` (
  `BuyerId` INT NOT NULL,
  `ProductId` INT NOT NULL,
  `Count` INT NOT NULL,
  PRIMARY KEY (`BuyerId`, `ProductId`),
  INDEX `ProductIdIdx` (`ProductId` ASC) VISIBLE,
  CONSTRAINT `ProductIdFK2`
    FOREIGN KEY (`ProductId`)
    REFERENCES `GamesHub`.`DigitalProduct` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `BuyerIdFK2`
    FOREIGN KEY (`BuyerId`)
    REFERENCES `GamesHub`.`Buyer` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamesHub`.`Order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GamesHub`.`Order` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `BuyerId` INT NOT NULL,
  `OrderDate` DATE,
  `OrderPrice` FLOAT NOT NULL,
  `OrderStatus` VARCHAR(45),
  PRIMARY KEY (`Id`),
  CONSTRAINT `OrderBuyerIdFK1`
    FOREIGN KEY (`BuyerId`)
    REFERENCES `GamesHub`.`Buyer` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamesHub`.`PhysicalOrderItem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GamesHub`.`PhysicalOrderItem` (
  `OrderId` INT NOT NULL,
  `ProductId` INT NOT NULL,
  `Count` INT NOT NULL,
  `UnitPrice` FLOAT NOT NULL,
  `TotalPrice` FLOAT NOT NULL,
  PRIMARY KEY (`OrderId`, `ProductId`),
  CONSTRAINT `PhysicalOrderIdFK1`
    FOREIGN KEY (`OrderId`)
    REFERENCES `GamesHub`.`Order` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `PhysicalProductIdFK1`
    FOREIGN KEY (`ProductId`)
    REFERENCES `GamesHub`.`PhysicalProduct` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamesHub`.`DigitalOrderItem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GamesHub`.`DigitalOrderItem` (
  `OrderId` INT NOT NULL,
  `ProductId` INT NOT NULL,
  `Count` INT NOT NULL,
  `UnitPrice` FLOAT NOT NULL,
  `TotalPrice` FLOAT NOT NULL,
  PRIMARY KEY (`OrderId`, `ProductId`),
  CONSTRAINT `DigitalOrderIdFK2`
    FOREIGN KEY (`OrderId`)
    REFERENCES `GamesHub`.`Order` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `DigitalProductIdFK2`
    FOREIGN KEY (`ProductId`)
    REFERENCES `GamesHub`.`DigitalProduct` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamesHub`.`PhysicalProductRequest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GamesHub`.`PhysicalProductRequest` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `DateReceived` DATE NOT NULL,
  `Status` VARCHAR(10) NOT NULL,
  `RequestType` VARCHAR(8) NOT NULL,
  `Title` VARCHAR(255) NOT NULL,
  `Price` INT NOT NULL,
  `Description` TEXT,
  `SellerId` INT,
  `Count` INT NOT NULL,
  `PostDate` DATE,
  `Category` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`Id`),
  FOREIGN KEY (`SellerId`) REFERENCES `GamesHub`.`Seller` (`Id`)
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamesHub`.`DigitalProductRequest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GamesHub`.`DigitalProductRequest` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `DateReceived` DATE NOT NULL,
  `Status` VARCHAR(10) NOT NULL,
  `RequestType` VARCHAR(8) NOT NULL,
  `Title` VARCHAR(255) NOT NULL,
  `Price` INT NOT NULL,
  `Description` TEXT,
  `SellerId` INT,
  `Count` INT NOT NULL,
  `PostDate` DATE,
  `Category` VARCHAR(20) NOT NULL,
  `Code` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`Id`),
  FOREIGN KEY (`SellerId`) REFERENCES `GamesHub`.`Seller` (`Id`)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `GamesHub`.`PhysicalProductRequestImagesUrl` (
    `Id` INT NOT NULL AUTO_INCREMENT,
    `Image` TEXT NOT NULL,
    `requestId` INT,
    PRIMARY KEY (`Id`),
    FOREIGN KEY (`requestId`) REFERENCES `GamesHub`.`PhysicalProductRequest` (`Id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `GamesHub`.`DigitalProductRequestImagesUrl` (
    `Id` INT NOT NULL AUTO_INCREMENT,
    `Image` TEXT NOT NULL,
    `requestId` INT,
    PRIMARY KEY (`Id`),
    FOREIGN KEY (`requestId`) REFERENCES `GamesHub`.`DigitalProductRequest` (`Id`)
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- End of Tables
-- -----------------------------------------------------