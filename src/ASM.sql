﻿/*USE master
DROP DATABASE ASS_SOF3021
GO*/
CREATE DATABASE ASS_SOF3021
GO

USE ASS_SOF3021
GO

IF OBJECT_ID('OrderDetails') IS NOT NULL
DROP TABLE OrderDetails
GO

IF OBJECT_ID('Orders') IS NOT NULL
DROP TABLE Orders
GO

IF OBJECT_ID('Products') IS NOT NULL
DROP TABLE Products
GO

IF OBJECT_ID('Categories') IS NOT NULL
DROP TABLE Categories
GO

IF OBJECT_ID('Accounts') IS NOT NULL
DROP TABLE Accounts
GO

CREATE TABLE Accounts(
	Username nvarchar(50) NOT NULL primary key,
	Password nvarchar(50) NOT NULL,
	Fullname nvarchar(50) NOT NULL,
	Email nvarchar(50) NOT NULL,
	Photo nvarchar(255) NULL,
	Activated bit,
	Role bit
)

GO
CREATE TABLE Categories(
	ID long IDENTITY primary key,
	Name nvarchar(50) NOT NULL,
)

GO
CREATE TABLE Products(
	ID long IDENTITY primary key,
	Name nvarchar(50) NOT NULL,
	Image nvarchar(255) NULL,
	Price float,
	CreateDate date DEFAULT getdate(),
	Available bit,
	CategoryID int,

	FOREIGN KEY (CategoryID) REFERENCES Categories(ID),
)

GO
CREATE TABLE Orders(
	ID bigint IDENTITY primary key,
	Username nvarchar(50) NOT NULL,
	Phone char(10) NOT NULL,
	CreateDate datetime DEFAULT getdate(),
	Address nvarchar(100) NOT NULL,
	Note nvarchar(200) NULL,
	Status Integer default 0

	FOREIGN KEY (Username) REFERENCES Accounts(Username),
)

GO
CREATE TABLE OrderDetails(
	ID bigint IDENTITY primary key,
	OrderID bigint,
	ProductID int,
	Price float,
	Quantity int,

	FOREIGN KEY (OrderID) REFERENCES Orders(ID),
	FOREIGN KEY (ProductID) REFERENCES Products(ID),
)

--them thong tin cho bang Accounts
INSERT INTO Accounts (Username, Password, Fullname, Email, Photo, Activated, Role) VALUES 
(N'admin1', N'admin123', N'Admin One', N'admin1@email.com', N'admin1.png', 1, 1),
(N'admin2', N'admin123', N'Admin Two', N'admin2@email.com', N'admin2.png', 1, 1),
(N'admin3', N'admin123', N'Admin Three', N'admin3@email.com', N'admin3.png', 1, 1),
(N'admin4', N'admin123', N'Admin Four', N'admin4@email.com', N'admin4.png', 1, 1),
(N'admin5', N'admin123', N'Admin Five', N'admin5@email.com', N'admin5.png', 1, 1),

(N'user1', N'user123', N'User One', N'user1@email.com', N'user1.png', 1, 0),
(N'user2', N'user123', N'User Two', N'user2@email.com', N'user2.png', 1, 0),
(N'user3', N'user123', N'User Three', N'user3@email.com', N'user3.png', 1, 0),
(N'user4', N'user123', N'User Four', N'user4@email.com', N'user4.png', 1, 0),
(N'user5', N'user123', N'User Five', N'user5@email.com', N'user5.png', 1, 0);
--them thong tin cho bang Categories
INSERT INTO Categories (Name) VALUES 
(N'Game'), 
(N'Electronic Device'),
(N'Books'),
(N'Home Appliances'),
(N'Sports Equipment');

--them thong tin cho bang Products
SET IDENTITY_INSERT Products ON;

INSERT INTO Products (ID, Name, Image, Price, CreateDate, Available, CategoryID) VALUES 
(1, 'Red Dead 2', '/images/photo-a.jpg', 100, GETDATE(), 1, 1),
(2, 'BloodBorne', '/images/photo-b.jpg', 200, GETDATE(), 1, 1),
(3, 'Dark Souls 3', '/images/photo-c.jpg', 50, GETDATE(), 1, 1),
(4, 'The Witcher 3', '/images/photo-d.jpg', 70, GETDATE(), 1, 1),
(5, 'Cyberpunk 2077', '/images/photo-e.jpg', 120, GETDATE(), 1, 1),
(6, 'Elden Ring', '/images/photo-f.jpg', 150, GETDATE(), 1, 1),
(7, 'Hollow Knight', '/images/photo-g.jpg', 30, GETDATE(), 1, 1),
(8, 'Sekiro', '/images/photo-h.jpg', 80, GETDATE(), 1, 1),
(9, 'God of War', '/images/photo-i.jpg', 90, GETDATE(), 1, 1),
(10, 'Horizon Zero Dawn', '/images/photo-k.jpg', 110, GETDATE(), 1, 1),

(11, 'Samsung Galaxy S21', '/images/photo-1.jpg', 800, GETDATE(), 1, 2),
(12, 'Laptop Dell XPS 13', '/images/photo-2.jpg', 1200, GETDATE(), 1, 2),
(13, 'Apple iPhone 13', '/images/photo-3.jpg', 999, GETDATE(), 1, 2),
(14, 'Sony WH-1000XM5', '/images/photo-4.jpg', 350, GETDATE(), 1, 2),
(15, 'Canon EOS R ', '/images/photo-5.jpg', 2500, GETDATE(), 1, 2),
(16, 'Apple iPad Pro', '/images/photo-6.jpg', 1100, GETDATE(), 1, 2),
(17, 'ASUS ROG Gaming', '/images/photo-7.jpg', 600, GETDATE(), 1, 2),
(18, 'Fitbit Charge 5', '/images/photo-8.jpg', 150, GETDATE(), 1, 2),
(19, 'Bose SoundLink', '/images/photo-9.jpg', 200, GETDATE(), 1, 2),
(20, 'DJI Mini 3 Pro Drone', '/images/photo-10.jpg', 700, GETDATE(), 1, 2),

(21, 'The Great Gatsby', '/images/book-1.jpg', 15, GETDATE(), 1, 3),
(22, '1984', '/images/book-2.jpg', 18, GETDATE(), 1, 3),
(23, 'To Kill a Mockingbird', '/images/book-3.jpg', 20, GETDATE(), 1, 3),
(24, 'Moby-Dick', '/images/book-4.jpg', 22, GETDATE(), 1, 3),
(25, 'War and Peace', '/images/book-5.jpg', 30, GETDATE(), 1, 3),
(26, 'The Catcher in the Rye', '/images/book-6.jpg', 25, GETDATE(), 1, 3),
(27, 'Pride and Prejudice', '/images/book-7.jpg', 17, GETDATE(), 1, 3),
(28, 'The Hobbit', '/images/book-8.jpg', 28, GETDATE(), 1, 3),
(29, 'Brave New World', '/images/book-9.jpg', 21, GETDATE(), 1, 3),
(30, 'The Lord of the Rings', '/images/book-10.jpg', 35, GETDATE(), 1, 3),

(31, 'Dyson V11 Vacuum', '/images/appliance-1.jpg', 600, GETDATE(), 1, 4),
(32, 'Instant Pot Duo', '/images/appliance-2.jpg', 100, GETDATE(), 1, 4),
(33, 'Samsung Smart TV', '/images/appliance-3.jpg', 900, GETDATE(), 1, 4),
(34, 'LG Washing Machine', '/images/appliance-4.jpg', 700, GETDATE(), 1, 4),
(35, 'Philips Air Fryer', '/images/appliance-5.jpg', 150, GETDATE(), 1, 4),
(36, 'Breville Espresso Machine', '/images/appliance-6.jpg', 800, GETDATE(), 1, 4),
(37, 'Roomba Robot Vacuum', '/images/appliance-7.jpg', 500, GETDATE(), 1, 4),
(38, 'KitchenAid Mixer', '/images/appliance-8.jpg', 400, GETDATE(), 1, 4),
(39, 'Sony Home Theater', '/images/appliance-9.jpg', 1200, GETDATE(), 1, 4),
(40, 'Nest Smart Thermostat', '/images/appliance-10.jpg', 250, GETDATE(), 1, 4),

(41, 'Treadmill ProForm', '/images/sports-1.jpg', 1200, GETDATE(), 1, 5),
(42, 'Bowflex Adjustable Dumbbells', '/images/sports-2.jpg', 400, GETDATE(), 1, 5),
(43, 'Yoga Mat Premium', '/images/sports-3.jpg', 50, GETDATE(), 1, 5),
(44, 'Wilson Tennis Racket', '/images/sports-4.jpg', 150, GETDATE(), 1, 5),
(45, 'Adidas Running Shoes', '/images/sports-5.jpg', 130, GETDATE(), 1, 5),
(46, 'Nike Soccer Ball', '/images/sports-6.jpg', 40, GETDATE(), 1, 5),
(47, 'Under Armour Gym Bag', '/images/sports-7.jpg', 60, GETDATE(), 1, 5),
(48, 'Garmin Smartwatch', '/images/sports-8.jpg', 300, GETDATE(), 1, 5),
(49, 'Everlast Punching Bag', '/images/sports-9.jpg', 250, GETDATE(), 1, 5),
(50, 'Schwinn Mountain Bike', '/images/sports-10.jpg', 800, GETDATE(), 1, 5);



			
-- Thêm dữ liệu vào bảng Orders
INSERT INTO Orders (Username, Phone, Address, Status) VALUES 
(N'user1', '0911111111', N'User One Address', 2),
(N'user2', '0922222222', N'User Two Address', 1),
(N'user3', '0933333333', N'User Three Address', 3),
(N'user4', '0944444444', N'User Four Address', 0),
(N'user5', '0955555555', N'User Five Address', 1),

(N'admin1', '0966666666', N'Admin One Address', 2),
(N'admin2', '0977777777', N'Admin Two Address', 1),
(N'admin3', '0988888888', N'Admin Three Address', 3),
(N'admin4', '0999999999', N'Admin Four Address', 0),
(N'admin5', '0900000000', N'Admin Five Address', 1);
--them thong tin cho bang OrderDetails
INSERT INTO OrderDetails (OrderID, ProductID, Price, Quantity) VALUES 
(1, 1, 100, 1), (1, 2, 200, 2),
(2, 3, 50, 1), (2, 4, 70, 1),
(3, 5, 120, 1), (3, 6, 150, 1),
(4, 7, 30, 2), (4, 8, 80, 1),
(5, 9, 90, 1), (5, 10, 110, 1);

SELECT * FROM Accounts
SELECT * FROM Categories
SELECT * FROM Products
SELECT * FROM Orders
SELECT * FROM OrderDetails


Select o.ID, o.Username, p.Name, od.Price, od.Quantity, SUM(od.price * od.Quantity) as ThanhTien
from Orders o join OrderDetails od 
on o.ID = od.OrderID join Products p
on od.ProductID = p.ID
where OrderID = '1'
Group by o.ID, o.Username, p.Name, od.Price, od.Quantity

Select o.ID, o.Username, o.Address, SUM(od.Price) as Tong_Tien
From Orders o join OrderDetails od 
on o.ID = od.OrderID
group by o.ID, o.Username, o.Address

SELECT TOP 3 p.* , sum(od.Quantity)
FROM Products p JOIN OrderDetails od
ON p.ID = od.ProductID
GROUP BY p.ID, p.Name, p.Price, p.Image, p.CreateDate, p.Available, p.CategoryID
ORDER BY sum(od.Quantity) DESC


SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME IN ('categories', 'orderdetails');
EXEC sp_columns 'categories';
EXEC sp_columns 'orderdetails';


EXEC sp_help 'Products'; -- kiểm tra kiểu dữ liệu của các cột trong bảng

ALTER TABLE orderdetails DROP CONSTRAINT FKaltatpxipsjtcih4d1h6bn0xr;


SELECT * 
FROM products
ORDER BY id
OFFSET 0 ROWS FETCH NEXT 5 ROWS ONLY;
  -- Fetch the first 10 records

  DECLARE @Page INT = 1;  -- Số trang hiện tại (có thể thay đổi động từ tham số đầu vào)
DECLARE @Size INT = 5;  -- Số mục mỗi trang (có thể thay đổi động từ tham số đầu vào)

SELECT * 
FROM Products
ORDER BY ID
OFFSET (@Page - 1) * @Size ROWS FETCH NEXT @Size ROWS ONLY;
