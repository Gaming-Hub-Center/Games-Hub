-- MySQL Script generated by MySQL Workbench
-- Fri Dec 8 19:52:17 2023
-- Model: New Model Version: 1.0
-- MySQL Workbench Forward Engineering

-- -----------------------------------------------------
-- Schema GamesHub
-- -----------------------------------------------------
USE `GamesHub` ;


-- -----------------------------------------------------
-- Inserting dummy data into `Buyer`
-- -----------------------------------------------------
INSERT INTO `Buyer` (`Name`, `Phone`, `Email`, `Address`, `Password`, `Balance`) VALUES
('John Doe', '+1234567890', 'john.doe@example.com', '123 Elm Street', '$2a$10$YJGLrNDJ0F.mE2E6IFWnDeDrkKlvQ3FuSYaOiUieGjTMkraZJoRBG', 1000),
('Jane Smith', '+9876543210', 'jane.smith@example.com', '456 Oak Avenue', '$2a$10$GMRwzM/Li/rDwHDw4RNbBeQFRHEcwVSEBZ18D4NkUo5BiHd/oawP6', 4000),
('Emily Johnson', '+1122334455', 'emily.johnson@example.com', '789 Pine Road', '$2a$10$FbE4XSzchqlJEwCE08.t7O4f77XE81uezyeA9cw4sHVIZc86YNiU.', 3500),
('Michael Brown', '+1231231234', 'michael.brown@example.com', '101 Maple Drive', '$2a$10$lrNzq9BhIMhFhUrjpjRyce7juS6CPb3t.BU6Ee2kndU7lVrzKqibq', 5000),
('Linda Green', '+1414141414', 'linda.green@example.com', '202 Birch Lane', '$2a$10$bA7uWFaiCARhieWSHwBGte3viMqO0u0xH91nVGcF08hCKRFN.TZ1O', 4500),
('Robert White', '+1515151515', 'robert.white@example.com', '303 Cedar Blvd', '$2a$10$S5PHAZICpF/p993Ex2KjEulNdSHUgRzPsuVGu2h1qtY3LoPJaLLLW', 5500),
('Patricia Harris', '+1616161616', 'patricia.harris@example.com', '404 Spruce St', '$2a$10$vfhdBh5IaUPtX0owvTgW8ule.WLgNBWeosLACGPAowr.Qc3x/9HTu', 6000),
('David Clark', '+1717171717', 'david.clark@example.com', '505 Aspen Way', '$2a$10$RdVEttpFV.U1HgiFpT3IhemaxI6tZ5tYFQrhKsPd4jZuySwreSNSK', 6500),
('Susan Lewis', '+1818181818', 'susan.lewis@example.com', '606 Walnut Circle', '$2a$10$2ntEBToBOfQkqdQnBQybSe.mBCu6RBPTiD6.twa5iyIODbM2uq0Iy', 7000),
('James Hall', '+1919191919', 'james.hall@example.com', '707 Pine Nut Ave', '$2a$10$4Cwz/fDYCHeKF3N.3CV.deLCcEUQgO3s/5bEsFzLfGCsBQAb3I85C', 7500);


-- -----------------------------------------------------
-- Inserting dummy data into `Seller`
-- -----------------------------------------------------
INSERT INTO `Seller` (`VatRegistrationNumber`, `DateJoined`, `Name`, `Phone`, `Email`, `Password`, `SellerDescription`, `Balance`, `NationalId`, `Address`) VALUES
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


-- -----------------------------------------------------
-- Inserting dummy data into `Admin`
-- -----------------------------------------------------
INSERT INTO `Admin` (`Name`, `Phone`, `Email`, `Password`) VALUES
('Alice Johnson','1234567890','alice.johnson@example.com', 'alicepass'),
('Bob Smith','0987654321','bob.smith@example.com', 'bobpass'),
('Carol White','1122334455','carol.white@example.com', 'carolpass'),
('David Brown','2233445566','david.brown@example.com', 'davidpass'),
('Eva Davis','3344556677','eva.davis@example.com', 'evapass'),
('Frank Miller','4455667788','frank.miller@example.com', 'frankpass'),
('Grace Wilson','5566778899','grace.wilson@example.com', 'gracepass'),
('Harry Taylor','6677889900','harry.taylor@example.com', 'harrypass'),
('Ivy Clark','7788990011','ivy.clark@example.com', 'ivypass'),
('Jack Turner','8899001122','jack.turner@example.com', 'jackpass'),
('Karen Evans','9900112233','karen.evans@example.com', 'karenpass'),
('Liam Anderson','1122334455','liam.anderson@example.com', 'liampass'),
('Mia Garcia','2233445566','mia.garcia@example.com', 'miapass'),
('Noah Brown','3344556677','noah.brown@example.com', 'noahpass'),
('Olivia Taylor','4455667788','olivia.taylor@example.com', 'oliviapass'),
('Paul Robinson','5566778899','paul.robinson@example.com', 'paulpass'),
('Quinn Harris','6677889900','quinn.harris@example.com', 'quinnpass'),
('Rose Carter','7788990011','rose.carter@example.com', 'rosepass'),
('Samuel Davis','8899001122','samuel.davis@example.com', 'samuelpass'),
('Taylor Smith','9900112233','taylor.smith@example.com', 'taylorpass');


-- -----------------------------------------------------
-- Inserting dummy data into `PhysicalProduct`
-- -----------------------------------------------------
INSERT INTO `PhysicalProduct` (`Title`, `Price`, `Description`, `SellerId`, `Count`, `Category`, `PostDate`) VALUES
('VR Headset', 200.00, 'Immersive VR experience with high resolution.', 1, 15, 'Gaming PC', '2023-12-08'),
('Gaming Console', 300.00, 'Latest model of our popular game console.', 1, 10, 'Gaming PC', '2023-12-08'),
('Gaming Laptop', 800.00, 'High-performance gaming laptop designed for gaming.', 1, 8, 'Gaming LAPTOP', '2023-12-08'),
('Gaming Chair', 150.00, 'Comfortable gaming chair for long gaming sessions.', 1, 20, 'Gaming Furniture', '2023-12-08'),
('Wireless Gaming Mouse', 50.00, 'Ergonomic wireless mouse for gaming.', 2, 30, 'Mouse', '2023-12-08'),
('Mechanical Gaming Keyboard', 80.00, 'Mechanical keyboard for precise gaming control.', 2, 25, 'Keyboard', '2023-12-08'),
('Gaming PC', 1200.00, 'Powerful gaming PC for ultimate gaming experience.', 2, 5, 'Gaming PC', '2023-12-08'),
('Gaming Desk', 100.00, 'Spacious gaming desk for multiple monitors.', 2, 15, 'Gaming Furniture', '2023-12-08'),
('Gaming Laptop', 900.00, 'High-performance gaming laptop designed for gaming.', 3, 10, 'Gaming LAPTOP', '2023-12-08'),
('Gaming Chair', 120.00, 'Comfortable gaming chair for long gaming sessions.', 3, 18, 'Gaming Furniture', '2023-12-08'),
('Wireless Gaming Mouse', 55.00, 'Ergonomic wireless mouse for gaming.', 3, 25, 'Mouse', '2023-12-08'),
('Mechanical Gaming Keyboard', 75.00, 'Mechanical keyboard for precise gaming control.', 3, 20, 'Keyboard', '2023-12-08'),
('Gaming PC', 1100.00, 'Powerful gaming PC for ultimate gaming experience.', 4, 7, 'Gaming PC', '2023-12-08'),
('Gaming Desk', 80.00, 'Spacious gaming desk for multiple monitors.', 4, 12, 'Gaming Furniture', '2023-12-08'),
('Wireless Gaming Mouse', 60.00, 'Ergonomic wireless mouse for gaming.', 4, 22, 'Mouse', '2023-12-08'),
('Mechanical Gaming Keyboard', 90.00, 'Mechanical keyboard for precise gaming control.', 4, 18, 'Keyboard', '2023-12-08'),
('Gaming Laptop', 950.00, 'High-performance gaming laptop designed for gaming.', 5, 12, 'Gaming LAPTOP', '2023-12-08'),
('Gaming Chair', 130.00, 'Comfortable gaming chair for long gaming sessions.', 5, 15, 'Gaming Furniture', '2023-12-08'),
('Wireless Gaming Mouse', 65.00, 'Ergonomic wireless mouse for gaming.', 5, 20, 'Mouse', '2023-12-08'),
('Mechanical Gaming Keyboard', 85.00, 'Mechanical keyboard for precise gaming control.', 5, 15, 'Keyboard', '2023-12-08');



-- -----------------------------------------------------
-- Inserting dummy data into `DigitalProduct`
-- -----------------------------------------------------
INSERT INTO `DigitalProduct` (`Title`, `Price`, `Description`, `SellerId`, `Count`, `Category`, `Code`, `PostDate`) VALUES
('Digital Game Bundle', 300.00, 'Get the latest digital game bundle with multiple titles.', 1, 10, 'Games', 'DG001', '2023-12-08'),
('Virtual Reality Gaming Experience', 200.00, 'Immersive VR gaming with a high-resolution headset.', 1, 15, 'VR Games', 'VR001', '2023-12-08'),
('Wireless Gaming Controller Pack', 60.00, 'Bundle of ergonomic wireless controllers for various gaming platforms.', 2, 25, 'Action', 'WG001', '2023-12-08'),
('High-Performance Gaming Laptop', 1000.00, 'Top-notch gaming laptop for an exceptional gaming experience.', 2, 5, 'PC', 'HL001', '2023-12-08'),
('Epic Quests Strategy Game', 50.00, 'Embark on epic quests with our latest strategy game.', 2, 2, 'Software and Utilities', 'EQ001', '2023-12-08'),
('Digital Game Bundle', 300.00, 'Get the latest digital game bundle with multiple titles.', 3, 10, 'Mobile Gaming', 'DG002', '2023-12-08'),
('Virtual Reality Gaming Experience', 200.00, 'Immersive VR gaming with a high-resolution headset.', 3, 15, 'VR Games', 'VR002', '2023-12-08'),
('Wireless Gaming Controller Pack', 60.00, 'Bundle of ergonomic wireless controllers for various gaming platforms.', 3, 25, 'Action', 'WG002', '2023-12-08'),
('High-Performance Gaming Laptop', 1000.00, 'Top-notch gaming laptop for an exceptional gaming experience.', 3, 5, 'PC', 'HL002', '2023-12-08'),
('Epic Quests Strategy Game', 50.00, 'Embark on epic quests with our latest strategy game.', 3, 2, 'Games', 'EQ002', '2023-12-08'),
('Digital Game Bundle', 300.00, 'Get the latest digital game bundle with multiple titles.', 4, 10, 'Software and Utilities', 'DG003', '2023-12-08'),
('Virtual Reality Gaming Experience', 200.00, 'Immersive VR gaming with a high-resolution headset.', 4, 15, 'VR Games', 'VR003', '2023-12-08'),
('Wireless Gaming Controller Pack', 60.00, 'Bundle of ergonomic wireless controllers for various gaming platforms.', 4, 25, 'Mobile Gaming', 'WG003', '2023-12-08'),
('High-Performance Gaming Laptop', 1000.00, 'Top-notch gaming laptop for an exceptional gaming experience.', 4, 5, 'Sports', 'HL003', '2023-12-08'),
('Epic Quests Strategy Game', 50.00, 'Embark on epic quests with our latest strategy game.', 4, 2, 'Mobile Gaming', 'EQ003', '2023-12-08');


-- -----------------------------------------------------
-- Inserting dummy data into `PhysicalCart`
-- -----------------------------------------------------
INSERT INTO `PhysicalCart` (`BuyerId`, `ProductId`, `Count`) VALUES
(1, 1, 5),
(1, 3, 2),
(1, 5, 2),
(2, 2, 2),
(2, 4, 7); 


-- -----------------------------------------------------
-- Inserting dummy data into `DigitalCart`
-- -----------------------------------------------------
INSERT INTO `DigitalCart` (`BuyerId`, `ProductId`, `Count`) VALUES
(1, 1, 5),
(1, 3, 2),
(1, 5, 2),
(2, 2, 2),
(2, 4, 7);


-- -----------------------------------------------------
-- Inserting dummy data into `Order`
-- -----------------------------------------------------
INSERT INTO `Order` (`BuyerId`, `OrderDate`, `OrderPrice`, `PaymentMethod`, `OrderStatus`) VALUES
(1, '2023-01-15', 800.00, 'Wallet', 'Shipped'),
(2, '2023-02-20', 300.00, 'COD', 'Returned'),
(1, '2023-01-15', 800.00, 'COD', 'Shipped'),
(2, '2023-02-20', 300.00, 'Wallet', 'Returned');


-- -----------------------------------------------------
-- Inserting dummy data into `PhysicalOrderItem`
-- -----------------------------------------------------
INSERT INTO `PhysicalOrderItem` (`OrderId`, `ProductId`, `Count`, `UnitPrice`, `TotalPrice`) VALUES
(1, 1, 2, 300.00, 600.00),
(1, 2, 1, 200.00, 200.00),
(2, 3, 5, 60.00, 300.00);


-- -----------------------------------------------------
-- Inserting dummy data into `DigitalOrderItem`
-- -----------------------------------------------------
INSERT INTO `DigitalOrderItem` (`OrderId`, `ProductId`, `Count`, `UnitPrice`, `TotalPrice`) VALUES
(3, 1, 2, 300.00, 600.00),
(3, 2, 1, 200.00, 200.00),
(4, 3, 5, 60.00, 300.00);


-- -----------------------------------------------------
-- Inserting dummy data into `DigitalCode`
-- -----------------------------------------------------
INSERT INTO `DigitalCode` (`OrderId`, `ProductId`, `Code`) VALUES
(3, 1, 'AAAAAAAA'),
(3, 1, 'BBBBBBBB'),
(3, 2, 'CCCCCCCC'),
(4, 3, 'DDDDDDDD'),
(4, 3, 'EEEEEEEE'),
(4, 3, 'FFFFFFFF'),
(4, 3, 'GGGGGGGG'),
(4, 3, 'HHHHHHHH');


-- -----------------------------------------------------
-- Inserting dummy data into `PhysicalProductRequest`
-- -----------------------------------------------------
INSERT INTO `PhysicalProductRequest` (`DateReceived`, `Status`, `RequestType`, `Title`, `Price`, `Description`, `SellerId`, `Count`, `PostDate`, `Category`) VALUES
('2023-12-08', 'Pending', 'New', 'Board Game', 40, 'Family board game for all ages.', 1, 20, '2023-12-15', 'Games'),
('2023-12-09', 'Denied', 'Update', 'Cycling Helmet', 150, 'High-quality helmet for cycling enthusiasts.', 2, 10, '2023-12-16', 'Sports');


-- -----------------------------------------------------
-- Inserting dummy data into `DigitalProductRequest`
-- -----------------------------------------------------
INSERT INTO `DigitalProductRequest` (`DateReceived`, `Status`, `RequestType`, `Title`, `Price`, `Description`, `SellerId`, `Count`, `PostDate`, `Category`, `Code`) VALUES
('2023-12-10', 'Pending', 'New', 'Digital Artwork', 500, 'High-quality digital art for game development.', 1, 5, '2023-12-12', 'Art', 'ART001'),
('2023-12-11', 'Approved', 'Update', 'E-book Collection', 300, 'Collection of fantasy e-books.', 2, 10, '2023-12-13', 'Books', 'EBK002');


-- -----------------------------------------------------
-- End of Tables
-- -----------------------------------------------------