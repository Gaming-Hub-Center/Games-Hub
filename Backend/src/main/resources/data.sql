INSERT INTO admin VALUES
(1,'Alice Johnson','1234567890','alice.johnson@example.com'),
(2,'Bob Smith','0987654321','bob.smith@example.com'),
(3,'Carol White','1122334455','carol.white@example.com'),
(4,'David Brown','2233445566','david.brown@example.com');

INSERT INTO buyer (Name, Phone, Email, Address, Password, Balance) VALUES
('John Doe', '+1234567890', 'john.doe@example.com', '123 Elm Street', 'password123', 3000),
('Jane Smith', '+9876543210', 'jane.smith@example.com', '456 Oak Avenue', 'mypassword', 4000),
('Emily Johnson', '+1122334455', 'emily.johnson@example.com', '789 Pine Road', 'emilypass', 3500),
('Michael Brown', '+1231231234', 'michael.brown@example.com', '101 Maple Drive', 'michaelpass', 5000),
('Linda Green', '+1414141414', 'linda.green@example.com', '202 Birch Lane', 'lindapass', 4500),
('Robert White', '+1515151515', 'robert.white@example.com', '303 Cedar Blvd', 'robertpass', 5500),
('Patricia Harris', '+1616161616', 'patricia.harris@example.com', '404 Spruce St', 'patriciapass', 6000),
('David Clark', '+1717171717', 'david.clark@example.com', '505 Aspen Way', 'davidpass', 6500),
('Susan Lewis', '+1818181818', 'susan.lewis@example.com', '606 Walnut Circle', 'susanpass', 7000),
('James Hall', '+1919191919', 'james.hall@example.com', '707 Pine Nut Ave', 'jamespass', 7500);

INSERT INTO seller (Vat_Registration_Number, Date_Joined, Name, Phone, Email, Password, SellerDescription, Balance, NationalID, Address) VALUES
('123456789A', '2023-01-01', 'Alice Blue', '+1029384756', 'alice.blue@example.com', 'alicepass', 'Description about Alice', 10000, 'ID12345A', '101 Red Street'),
('987654321B', '2023-02-02', 'Bob Green', '+5647382910', 'bob.green@example.com', 'bobpassword', 'Description about Bob', 15000, 'ID67890B', '202 Green Lane'),
('111222333C', '2023-03-03', 'Charlie Yellow', '+1239874560', 'charlie.yellow@example.com', 'charliepass', 'Description about Charlie', 12000, 'ID112233C', '303 Blue Boulevard'),
('444555666D', '2023-04-04', 'Diana White', '+0918273645', 'diana.white@example.com', 'dianapass', 'Description about Diana', 11000, 'ID445566D', '404 Yellow Path'),
('777888999E', '2023-05-05', 'Edward Black', '+0987654321', 'edward.black@example.com', 'edwardpass', 'Description about Edward', 13000, 'ID777888E', '505 Pink Road'),
('666777888F', '2023-06-06', 'Fiona Grey', '+0192837465', 'fiona.grey@example.com', 'fionapass', 'Description about Fiona', 14000, 'ID666777F', '606 Violet Street'),
('555666777G', '2023-07-07', 'George Teal', '+0183746552', 'george.teal@example.com', 'georgepass', 'Description about George', 16000, 'ID555666G', '707 Indigo Blvd'),
('444555666H', '2023-08-08', 'Hannah Brown', '+0123456789', 'hannah.brown@example.com', 'hannahpass', 'Description about Hannah', 17000, 'ID444555H', '808 Grey Lane'),
('333444555I', '2023-09-09', 'Ian Blackwood', '+0234567891', 'ian.blackwood@example.com', 'ianpass', 'Description about Ian', 18000, 'ID333444I', '909 Turquoise Way'),
('222333444J', '2023-10-10', 'Julia Redwood', '+0345678912', 'julia.redwood@example.com', 'juliapass', 'Description about Julia', 19000, 'ID222333J', '1010 Silver Street');