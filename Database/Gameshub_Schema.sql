CREATE DATABASE  IF NOT EXISTS `gameshub` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gameshub`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: gamehub
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQsellerbuyerL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `AdminID` int NOT NULL AUTO_INCREMENT,
  `AdminName` varchar(255) DEFAULT NULL,
  `ContactNumber` varchar(15) DEFAULT NULL,
  `EmailAddress` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`AdminID`),
  UNIQUE KEY `EmailAddress` (`EmailAddress`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'Alice Johnson','1234567890','alice.johnson@example.com'),(2,'Bob Smith','0987654321','bob.smith@example.com'),(3,'Carol White','1122334455','carol.white@example.com'),(4,'David Brown','2233445566','david.brown@example.com');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buyer`
--

DROP TABLE IF EXISTS `buyer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buyer` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `Phone` varchar(15) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `Password` varchar(72) DEFAULT NULL,
  `Balance` float DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buyer`
--

LOCK TABLES `buyer` WRITE;
/*!40000 ALTER TABLE `buyer` DISABLE KEYS */;
INSERT INTO `buyer` (`Name`, `Phone`, `Email`, `Address`, `Password`, `Balance`) VALUES
('John Doe', '+1234567890', 'john.doe@example.com', '123 Elm Street', '$2a$10$YJGLrNDJ0F.mE2E6IFWnDeDrkKlvQ3FuSYaOiUieGjTMkraZJoRBG', 3000),
('Jane Smith', '+9876543210', 'jane.smith@example.com', '456 Oak Avenue', '$2a$10$GMRwzM/Li/rDwHDw4RNbBeQFRHEcwVSEBZ18D4NkUo5BiHd/oawP6', 4000),
('Emily Johnson', '+1122334455', 'emily.johnson@example.com', '789 Pine Road', '$2a$10$FbE4XSzchqlJEwCE08.t7O4f77XE81uezyeA9cw4sHVIZc86YNiU.', 3500),
('Michael Brown', '+1231231234', 'michael.brown@example.com', '101 Maple Drive', '$2a$10$lrNzq9BhIMhFhUrjpjRyce7juS6CPb3t.BU6Ee2kndU7lVrzKqibq', 5000),
('Linda Green', '+1414141414', 'linda.green@example.com', '202 Birch Lane', '$2a$10$bA7uWFaiCARhieWSHwBGte3viMqO0u0xH91nVGcF08hCKRFN.TZ1O', 4500),
('Robert White', '+1515151515', 'robert.white@example.com', '303 Cedar Blvd', '$2a$10$S5PHAZICpF/p993Ex2KjEulNdSHUgRzPsuVGu2h1qtY3LoPJaLLLW', 5500),
('Patricia Harris', '+1616161616', 'patricia.harris@example.com', '404 Spruce St', '$2a$10$vfhdBh5IaUPtX0owvTgW8ule.WLgNBWeosLACGPAowr.Qc3x/9HTu', 6000),
('David Clark', '+1717171717', 'david.clark@example.com', '505 Aspen Way', '$2a$10$RdVEttpFV.U1HgiFpT3IhemaxI6tZ5tYFQrhKsPd4jZuySwreSNSK', 6500),
('Susan Lewis', '+1818181818', 'susan.lewis@example.com', '606 Walnut Circle', '$2a$10$2ntEBToBOfQkqdQnBQybSe.mBCu6RBPTiD6.twa5iyIODbM2uq0Iy', 7000),
('James Hall', '+1919191919', 'james.hall@example.com', '707 Pine Nut Ave', '$2a$10$4Cwz/fDYCHeKF3N.3CV.deLCcEUQgO3s/5bEsFzLfGCsBQAb3I85C', 7500);
/*!40000 ALTER TABLE `buyer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seller`
--

DROP TABLE IF EXISTS `seller`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seller` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Vat_Registration_Number` varchar(255) DEFAULT NULL,
  `Date_Joined` date DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Phone` varchar(15) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Password` varchar(72) DEFAULT NULL,
  `SellerDescription` text,
  `Balance` float DEFAULT NULL,
  `NationalID` varchar(20) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seller`
--

LOCK TABLES `seller` WRITE;
/*!40000 ALTER TABLE `seller` DISABLE KEYS */;
INSERT INTO `seller` (`Vat_Registration_Number`, `Date_Joined`, `Name`, `Phone`, `Email`, `Password`, `SellerDescription`, `Balance`, `NationalID`, `Address`) VALUES
('123456789A', '2023-01-01', 'Alice Blue', '+1029384756', 'alice.blue@example.com', '$2a$10$HaID.XdQm../yady9rA2k.EoY4oiL/In32c/cLRa3DWyW/Nn6DXcG', 'Description about Alice', 10000, 'ID12345A', '101 Red Street'),
('987654321B', '2023-02-02', 'Bob Green', '+5647382910', 'bob.green@example.com', '$2a$10$bM/Om9b6r/7qklzXJCCAauNotDkmKEsD2W.BA32PvOw5nIcOsRc3q', 'Description about Bob', 15000, 'ID67890B', '202 Green Lane'),
('111222333C', '2023-03-03', 'Charlie Yellow', '+1239874560', 'charlie.yellow@example.com', '$2a$10$pl2u29r6.o8BauM/IGZu2e7E2PqMwKj9VnNiRe.9LiMccgQzF.lsS', 'Description about Charlie', 12000, 'ID112233C', '303 Blue Boulevard'),
('444555666D', '2023-04-04', 'Diana White', '+0918273645', 'diana.white@example.com', '$2a$10$Q94utPVflSd/EawCQXHzaenWs.9/86jwi1lKAwXgKEc2eKXZv/zjG', 'Description about Diana', 11000, 'ID445566D', '404 Yellow Path'),
('777888999E', '2023-05-05', 'Edward Black', '+0987654321', 'edward.black@example.com', '$2a$10$hKEWBhE75ypAxjI885ZlJeGyhsmYNH8mIumx6y4KgbAt74eJn3aT.', 'Description about Edward', 13000, 'ID777888E', '505 Pink Road'),
('666777888F', '2023-06-06', 'Fiona Grey', '+0192837465', 'fiona.grey@example.com', '$2a$10$e254jdKhuTe3U9zE6yNZdedvTKCB4K0VaaInRt0ctjvYYj79Y9yd2', 'Description about Fiona', 14000, 'ID666777F', '606 Violet Street'),
('555666777G', '2023-07-07', 'George Teal', '+0183746552', 'george.teal@example.com', '$2a$10$56S/HNupCbkB.cX3Fn6uVOytWHMNFWZgZZlEtNcbrVyRilC6C0wGq', 'Description about George', 16000, 'ID555666G', '707 Indigo Blvd'),
('444555666H', '2023-08-08', 'Hannah Brown', '+0123456789', 'hannah.brown@example.com', '$2a$10$9tkn5WO6jE5GljdEwAEuju6Xm9m00hk.cD1xfnOH8RNCLyYGeUBI.', 'Description about Hannah', 17000, 'ID444555H', '808 Grey Lane'),
('333444555I', '2023-09-09', 'Ian Blackwood', '+0234567891', 'ian.blackwood@example.com', '$2a$10$4JwC2mVkIU9cMYX3WdJIGOy3/C0hPyHptuxxRZ6PbPCp/a2I6dso6', 'Description about Ian', 18000, 'ID333444I', '909 Turquoise Way'),
('222333444J', '2023-10-10', 'Julia Redwood', '+0345678912', 'julia.redwood@example.com', '$2a$10$dwOZrATKkn5NAGZK.G./DeeBMaXO7gxtzQaVMe.okrfYjG47QUE1W', 'Description about Julia', 19000, 'ID222333J', '1010 Silver Street');
/*!40000 ALTER TABLE `seller` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `price` INT,
  `image` BLOB,
  `description` TEXT,
  `sellerID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`sellerID`) REFERENCES `seller` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seller`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`price`, `image`, `description`, `sellerID`) VALUES
(100, null, 'this is a product', 1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

SELECT * FROM product WHERE ID = 1;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLbuyerD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-14  2:37:23