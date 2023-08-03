/*Chạy nguyên cái database*/
RAISERROR('Creating Vinhomes 4 database....',0,1)
SET NOCOUNT ON
GO

USE [master]
DROP DATABASE IF EXISTS [Vinhomes4]
GO

CREATE DATABASE [Vinhomes4]
GO

USE [Vinhomes4]
GO

create table BlockVin (
	BID int PRIMARY KEY IDENTITY(1,1),
	name nvarchar(50)
)

create table Role (
	ID int identity(1,1) primary key,
	role_name nvarchar(50)
)

create table Account (
	AID INT PRIMARY KEY identity(1,1),
	phone nvarchar(15),
	email nvarchar(100),
	password nvarchar(50),
	name nvarchar(100),
	gender nvarchar(20) default('Male'),
	roleId INT references Role(ID),
	status bit
)

CREATE TABLE Resident (
	ID [INT] PRIMARY KEY references [Account](AID) NOT NULL,
	BID INT references BlockVin(BID),
	room nvarchar(20),
)

CREATE TABLE Coordinator (
	ID [INT] PRIMARY KEY references [Account](AID) NOT NULL,
	[enabled] [bit] NOT NULL default(1)
)

create table Supplier (
	SID int identity(1,1),
	name nvarchar(100),
	phone nvarchar(15),
	email nvarchar(100), 
	address nvarchar(255)
	primary key (SID)
)

create table Category (
	CID int identity(1,1),
	name nvarchar(100),
	primary key (CID)
)

create table Service (
	service_id int identity(1,1),
	[name] nvarchar(255),
	[description] nvarchar(255),
	lower_price decimal(10,2),
	upper_price decimal(10,2),
	rated decimal(3,1),
	category_id INT REFERENCES dbo.Category(CID),
	primary key (service_id)

)


create table Feedback (
	fid INT identity(1,1) PRIMARY KEY NOT NULL,
	RID INT REFERENCES dbo.Resident (ID),
	service_id INT REFERENCES dbo.Service (service_id), 
	rate int default(1),
	message nvarchar(255),
	name nvarchar(100),
	contact_number varchar(20),
	email nvarchar(50)
)

create table Orders(
	OID int identity(1,1) PRIMARY KEY NOT NULL,
	[time] DATETIME,
	delivery_time DATETIME,
	[status] [varchar](30) NOT NULL default('Pending'),
	RID INT REFERENCES dbo.Resident(ID), 
	CID INT REFERENCES dbo.Coordinator(ID), 
	BID INT REFERENCES dbo.BlockVin (BID),
	note nvarchar(255)
)

CREATE TABLE [dbo].[OrderDetail](
	[id] [int] IDENTITY(1,1) PRIMARY KEY NOT NULL,
	[orderHeader_id] [int] references Orders(OID) NOT NULL,
	[service_id] [int] references dbo.Service(service_id) NOT NULL,
	[category_id] [INT] NOT NULL,
	[price] [INT],
	[supplier_id] [INT] REFERENCES dbo.Supplier(SID) DEFAULT NULL,
)
GO

ALTER TABLE Account ADD salt nvarchar(50);
GO

insert into Role(role_name) values 
('Resident'), ('Coordinator'), ('Manager'), ('Admin')

insert into Category(name) values 
('Cleaning'), ('Maintenance'), ('Security'), ('Pest Control')

INSERT INTO BlockVin(name) VALUES 
('Toa S102'),
('Toa S101'),
('Toa S103'),
('Toa S104'),
('Toa S105'),
('Toa S106'),
('Toa S107'),
('Toa S201'),
('Toa S202'),
('Toa S203'),
('Toa S204'),
('Toa S205'),
('Toa S206'),
('Toa S207'),
('Toa S303'),
('Toa S304'),
('Toa S305');

insert into Account(email, phone, password, name, roleId, status) values 
('johndoe@email.com', '0912345151','123456', N'John Doe', 4, 1), 
('janesmith@email.com', '0987654321','123456', N'Jane Smith', 3, 0),
('boblee@email.com', '0934567890', '123456', N'Bob Lee', 3, 0),
('doquanghuy@email.com', '0978912345', '123456', N'Ðỗ Quang Huy', 3, 0),
('vumai@email.com', '0923456789', '123456', N'Vu Thị Mai', 3, 0),
('kellylee@email.com', '0923456789', '123456', N'Kelly Lee', 3, 0),
('hoangnam@email.com', '0912345678', '123456', N'Hoàng Văn Nam', 3, 0),
('emmalin@email.com', '0912345678', '123456', N'Emma Lin', 3, 1),
('johnsmith@gmail.com', '0901234567', '123456', N'John Smith', 2, 1),
('sarahjohnson@gmail.com', '0912345678', '123456', N'Sarah Johnson', 2, 1),
('davidmiller@gmail.com', '0923456789', '123456', N'David Miller', 2, 1),
('emilydavis@gmail.com', '0934567890', '123456', N'Emily Davis', 2, 1),
('michaelwilson@gmail.com', '0945678901', '123456', N'Michael Wilson', 2, 1),
('maryjane@gmail.com', '0912345678', '654321', N'Mary Jane', 2, 1),
('peterparker@gmail.com', '0987654321', '987654', N'Peter Parker', 2, 1),
('janedoe@gmail.com', '0977123456', '456123', N'Jane Doe', 2, 1),
('thu1@gmail.com', '0987654321', '123456',N'Nguyễn Thị Thu', 1, 1),
('tung@gmail.com', '0912345678', '123456', N'Trần Thanh Tùng', 1, 1),
('tuan@gmail.com','0934567890', '123456',N'Vũ Anh Tuấn', 1, 1),
('anh@gmail.com', '0909876543', '123456',N'Lê Thị Hồng Anh', 1, 1),
('nam@gmail.com', '0943215678', '123456',N'Ðỗ Thành Nam', 1, 1)
GO

INSERT INTO dbo.Resident(ID, BID, room) VALUES
(17, 1, 'MA03B0156'),
(18, 1, 'MA03B0156'),
(19, 1, 'MA03B0156'),
(20, 1, 'MA03B0156'),
(21, 1, 'MA03B0156')
GO

INSERT INTO dbo.Coordinator(ID, enabled) VALUES
(9, 0),
(10, 0),
(11, 1),
(12, 0),
(13, 1),
(14, 1),
(15, 1),
(16, 1)
GO

insert into Service(name, lower_price, upper_price, description, category_id) values 
('Carpet Cleaning', 50, 100, 'We clean carpets using steam and eco-friendly products.', 1),
('Window Cleaning', 40, 80, 'We clean windows inside and outside using professional tools.', 1),
('Plumbing Repair', 60, 120, 'We fix leaks, clogs, and other plumbing issues.', 2),
('Electrical Repair', 70, 140, 'We repair wiring, outlets, switches, and other electrical problems.', 2),
('Security Guard', 100, 200, 'We provied trained security guards for your building.', 3),
('CCTV Installation', 150, 300, 'We install and maintain CCTV cameras for your building.', 3),
('Lawn Mowing', 30, 60, 'We mow your lawn and trim the edges.', 4),
('Tree Pruning', 50, 100, 'We prune your tree, and remove dead branches.', 4),
('Termite Treatment', 200, 400, 'We treat your building for termites and prevent infestation.', 4),
('Carpet Cleaning', 50, 100, 'We clean carpets using steam and eco-friendly products.', 1),
('Upholstery Cleaning', 60, 120, 'We clean sofas, chairs, and other upholdstery using steam and eco-friendly products.', 1),
('Floor Waxing', 80, 160, 'We wax and polish your floors using high-quality products.', 1),
('HVAC Repair', 90, 180, 'We repair and service your heating ventilation, and air conditioning systems.', 2),
('Painting Service', 100, 200, 'We paint your walls, ceilings, and doors using professional painters.', 2),
('Alarm System', 120, 240, 'We install and maintain alarm systems for your building.', 3),
('Fire Safety', 140, 280, 'We install and maintain fire extinguishers, sprinklers, and smoke directors for your building.', 3),
('Garden Design', 150, 300, 'We design and create beautiful gardens for your building.', 4),
('Snow Removal', 40, 80, 'We remove snow from your sidewalks, driveways, and parking lots.', 4),
('Rodent Control', 180, 360, 'We eliminate rodents from your building and prevent them from coming back.', 3),
('Carpet Cleaning', 50, 100, 'We clean carpets using steam and eco-friendly products.', 1),
('Window Cleaning', 40, 80, 'We clean windows inside and outside using professional tools.', 1),
('Plumbing Repair', 60, 120, 'We fix leaks, clogs, and other plumbing issues.', 2),
('Electrical Repair', 70, 140, 'We repair wiring, outlets, switches, and other electrical problems.', 2),
('Security Guard', 100, 200, 'We provied trained security guards for your building.', 3)

insert into Supplier(name, email, phone, address) values 
('Bigball Chunky Cleaning Service', 'bigballchunky@gmail.com', '0283123456', N' Vinhomes Grandpark, Thành phố Thủ Ðức, Thành phố Hồ Chí Minh'),
('Clean & Fix Home Services', 'cleanfixhomes@gmail.com', '0283123457', N'456 Nguyễn Duy Trinh, Phường Linh Chiểu, Thành phố Thủ Ðức, Thành phố Hồ Chí Minh'),
('Fresh Living Home Solution', 'freshlivingsolution@gmail.com', '0283123458', N'789 Phạm Văn Đồng, Phường Bình Thọ, Thành phố Thủ Ðức, Thành phố Hồ Chí Minh'),
('Neat & Tidy Home Care', 'neattidyhomes@gmail.com', '0283123459', N'321 Bung Ông Thoàn, Phường Tam Bình, Thành phố Thủ Ðức, Thành phố Hồ Chí Minh'),
('Spotless Home Services', 'spotlesshomes@gmail.com', '0283123460', N'654 Tăng Nhơn Phú A, Phường Tăng Nhơn Phú A, Thành phố Thủ Ðức, Thành phố Hồ Chí Minh'),
('Homecare Solutions Co.', 'homecaresolutions@gmail.com', '0283123461', N'987 Đường Lê Văn Việt, Phường Hiệp Bình Chánh, Thành phố Thủ Ðức, Thành phố Hồ Chí Minh'),
('FreshStart Cleaning & Repairs', 'freshstartservices@gmail.com', '0283123462', N'753 Lê Văn Quới, Phường Tân Phú, Thành phố Thủ Ðức, Thành phố Hồ Chí Minh'),
('BrightHome Repair & Maintenance', 'brighthomerepair@gmail.com', '0283123463', N'159 Linh Ðông, Phường Linh Ðông, Thành phố Thủ Ðức, Thành phố Hồ Chí Minh'),
('QuickFix Home Services', 'quickfixhomeservices@gmail.com', '0283123464', N'495 Nguyễn Duy Trinh, Phường Linh Trung, Thành phố Thủ Ðức, Thành phố Hồ Chí Minh'),
('ShinePro Cleaning & Handyman Services', 'shineproservices@gmail.com', '0283123465', N'732 Phạm Văn Đồng, Phường Linh Xuân, Thành phố Thủ Ðức, Thành phố Hồ Chí Minh')


INSERT INTO dbo.Orders(time, delivery_time, status, RID, CID, BID, note)
VALUES
    ('2023-07-31 08:00:00', '2023-08-01 15:00:00', 'Pending', 17, 9, 1, 'Order pending'),
	('2023-01-29 08:00:00', '2023-01-29 15:00:00', 'Completed', 17, 16, 1, 'Order pending'),
    ('2023-07-31 09:30:00', '2023-08-01 16:30:00', 'Pending', 18, 10, 1, 'Waiting for confirmation'),
    ('2023-01-29 10:15:00', '2023-01-29 17:15:00', 'Completed', 19, 11, 1, 'Order processing'),
    ('2023-07-31 11:45:00', '2023-08-01 18:45:00', 'Pending', 17, 12, 1, NULL),
    ('2023-01-29 12:30:00', '2023-01-29 19:30:00', 'Completed', 18, 13, 1, NULL),
	('2023-04-01 16:30:00', '2023-04-02 23:30:00', 'Cancel', 17, 9, 1, 'Wrong Delivery time choosing'),
	('2023-04-01 17:30:00', '2023-04-02 23:30:00', 'Cancel', 17, 9, 1, 'Wrong Service choosing'),
	('2023-04-01 18:30:00', '2023-04-02 23:30:00', 'Cancel', 21, 10, 1, 'No need anymore'),
    ('2023-01-29 15:45:00', '2023-01-29 22:45:00', 'Failed', 18, 12, 1, 'Delivery failed'),
    ('2023-01-29 17:00:00', '2023-01-30 00:00:00', 'Failed', 17, 11, 1, NULL),
    ('2023-04-01 18:15:00', '2023-04-01 01:15:00', 'Failed', 17, 13, 1, 'Item out of stock'),
    ('2023-01-29 19:30:00', '2023-01-30 02:30:00', 'Completed', 18, 9, 1, 'Order delivered on time'),
    ('2023-04-02 10:00:00', '2023-04-02 17:00:00', 'Completed', 19, 9, 1, 'Customer satisfied with the product'),
    ('2023-01-30 11:15:00', '2023-01-30 18:15:00', 'Completed', 20, 11, 1, 'Positive feedback received'),
    ('2023-04-02 14:45:00', '2023-04-02 21:45:00', 'Completed', 21, 12, 1, 'Great service provided'),
    ('2023-01-30 17:30:00', '2023-01-31 00:30:00', 'Completed', 17, 13, 1, 'Order completed successfully'); 


INSERT INTO OrderDetail (orderHeader_id, service_id, category_id, price, supplier_id) 
VALUES
(1, 3, 2, 120, NULL),
(1, 2, 1, 70, NULL),
(1, 5, 3, 150, NULL),

(2, 5, 3, 200, NULL),

(3, 2, 1, 80, NULL),

(4, 4, 2, 140, NULL),

(5, 8, 4, 100, NULL),

(6, 3, 2, 120, NULL),
(6, 5, 3, 200, NULL),
(6, 2, 1, 80, NULL),

(7, 4, 2, 140 , NULL),

(8, 8, 4, 100, 1),

(9, 6, 3, 300, 2),

(10, 1, 1, 100, 3),

(11, 7, 4, 60, 1),
(11, 7, 4, 40, 2),
(11, 8, 4, 90, 3),
(12, 9, 4, 400, 5),

(14, 3, 2, 120, 4),
(14, 5, 3, 200, 1),
(14, 2, 1, 80, 2),

(15, 2, 1, 80, 3),
(15, 4, 2, 140, 4),
(15, 8, 4, 100, 5),

(16, 4, 2, 140, 1),
(16, 8, 4, 100, 3),
(16, 6, 3, 300, 2),
(16, 1, 1, 100, 4),

(17, 7, 4, 60, 1),
(17, 7, 4, 40, 2),
(17, 8, 4, 90, 3),
(17, 9, 4, 400, 5)
GO

SET NOCOUNT OFF
raiserror('The Vinhomes4 database in now ready for use.',0,1)
GO