﻿/*Chạy nguyên cái database*/
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
	BID int identity(1,1),
	name nvarchar(50),
	primary key (BID)
)

create table Role (
	ID int identity(1,1) primary key,
	role_name nvarchar(50)
)

create table Account (
	AID int identity(1,1),
	phone nvarchar(15),
	email nvarchar(100),
	password nvarchar(50),
	name nvarchar(100),
	gender nvarchar(20) default('Male'),
	BID int,
	roleId int, 
	room nvarchar(20),
	status bit,
	primary key (AID),
	foreign key (roleId) references Role(ID),
	foreign key (BID) references BlockVin(BID)
)

create table Resource (
	RID int identity(1,1),
	name nvarchar(100),
	primary key (RID)
)

create table BlockResource (
	BID int, 
	RID int, 
	quantity int,
	primary key (BID, RID)
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
	name nvarchar(255),
	description nvarchar(255),
	lower_price decimal(10,2),
	upper_price decimal(10,2),
	rated decimal(3,1),
	supplier_id int,
	category_id int,
	primary key (service_id)
	/* 
		foreign key (supplier_id) references Supplier(SID),
		foreign key (category_id) references Category(CID)
	*/
)

create table ServiceResourceNeeded (
	SID int, 
	RID int, 
	quantity int,
	primary key (SID, RID)
)

create table Feedback (
	UID int,
	DID int, 
	/*
	foreign key UID references Resident(UID)
	foreign key DID references Service(DID)
	*/
	rate int default(1),
	message nvarchar(255),
	name nvarchar(100),
	contact_number varchar(20),
	email nvarchar(50)
)

create table Orders(
	OID int identity(1,1) PRIMARY KEY NOT NULL,
	time datetime,
	delivery_time DATETIME,
	[status] [varchar](30) NOT NULL default('Pending'),
	UID INT REFERENCES dbo.Account(AID), 
	EID INT REFERENCES dbo.Account(AID), 
	note nvarchar(255)
)
CREATE TABLE [dbo].[OrderDetail](
	[id] [int] IDENTITY(1,1) PRIMARY KEY NOT NULL,
	[orderHeader_id] [int] references Orders(OID) NOT NULL,
	[service_id] [int] references dbo.Service(service_id) NOT NULL,
	[category_id] [INT] NOT NULL,
	[price] [int]
)
GO

ALTER TABLE Account ADD salt nvarchar(50);
GO

alter table BlockResource 
	add foreign key (BID) references BlockVin(BID),
		foreign key (RID) references Resource(RID)
alter table Service 
	add foreign key (supplier_id) references Supplier(SID),
		foreign key (category_id) references Category(CID)
alter table Feedback 
	add FID int identity(1,1) primary key,
		foreign key (UID) references Account(AID),
		foreign key (DID) references Service(service_id)
alter table ServiceResourceNeeded
	add foreign key (SID) references Service(service_id),
	foreign key (RID) references Resource(RID)
GO

insert into Role(role_name) values 
('Resident'), ('Employee'), ('Manager'), ('Admin')

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

insert into Resource(name) values 
('Air conditioning'),
('Sweeper'),
('Light bulb'),
('Screw'),
('Water tank'),
('Shower head'),
('Electric wire'),
('Mirror'),
('Chair'),
('Table'),
('Tube bulb'),
('Desk'),
('Sofa'),
('Bed'),
('Toilet paper'),
('Garbage bag'),
('Wall decal')

insert into Account(email, phone, password, name, BID, roleId, room, status) values 
('johndoe@email.com', '0912345151','123456', N'John Doe', 2, 4, 'MA03B0156', 1),                 
('janesmith@email.com', '0987654321','123456', N'Jane Smith', 1, 4, 'MA03B0156', 0),
('boblee@email.com', '0934567890', '123456', N'Bob Lee', 3, 4, 'MA03B0156', 0),
('doquanghuy@email.com', '0978912345', '123456', N'Ðỗ Quang Huy', 7, 4, 'MA03B0156', 0),
('vumai@email.com', '0923456789', '123456', N'Vu Thị Mai', 6, 4, 'MA03B0156', 0),
('kellylee@email.com', '0923456789', '123456', N'Kelly Lee', 5, 4, 'MA03B0156', 0),
('hoangnam@email.com', '0912345678', '123456', N'Hoàng Văn Nam', 4, 4, 'MA03B0156', 0),
('emmalin@email.com', '0912345678', '123456', N'Emma Lin', 1, 3, 'MA03B0156', 1),
('sarachen@email.com', '0956789012', '123456',N'Sara Chen', 3, 3, 'MA03B0156', 0),
('tomchen@email.com', '0912345678', '123456',N'Tom Chen', 2, 3, 'MA03B0156', 0),
('nguyenvanan@email.com', '0912345678', '123456',N'Nguyễn Văn An', 7, 3, 'MA03B0156', 0),
('buihuong@email.com', '0987654321', '123456', N'Bùi Thị Hương', 6, 3, 'MA03B0156', 0),
('johnsmith@gmail.com', '0901234567', '123456', N'John Smith', 4, 2, 'MA01A0123', 1),
('sarahjohnson@gmail.com', '0912345678', '123456', N'Sarah Johnson', 7, 2, 'MA02B0345', 1),
('davidmiller@gmail.com', '0923456789', '123456', N'David Miller', 2, 2, 'MA03C0567', 1),
('emilydavis@gmail.com', '0934567890', '123456', N'Emily Davis', 10, 2, 'MA01A0123', 1),
('michaelwilson@gmail.com', '0945678901', '123456', N'Michael Wilson', 6, 2, 'MA02B0345', 1),
('thu1@gmail.com', '0987654321', '123456',N'Nguyễn Thị Thu', 3, 1, 'MA03B0156', 1),
('tung@gmail.com', '0912345678', '123456', N'Trần Thanh Tùng', 2, 1, 'OR11B0332', 1),
('tuan@gmail.com','0934567890', '123456',N'Vũ Anh Tuấn',3, 1, 'GP14B2073', 1),
('anh@gmail.com', '0909876543', '123456',N'Lê Thị Hồng Anh',1, 1, 'MA10B1147', 1),
('nam@gmail.com', '0943215678', '123456',N'Ðỗ Thành Nam',5, 1, 'GP20B4615', 1),
('anh1@gmail.com', '0965432198', '123456', N'Nguyễn Thị Ngọc Ánh',3, 1, 'MA12B0312', 1),
('quan@gmail.com', '0998765432', '123456', N'Phan Ðình Quân',2,1, 'OR16B1033', 1),
('trang@gmail.com', '0967890123', '123456',N'Trần Thị Trang',2, 1, 'GP01B2479', 1),
('toan@gmail.com', '0943567128', '123456',N'Nguyễn Van Toàn',4, 1, 'MA07B0821', 1),
('thu2@gmail.com', '0912876543', '123456', N'Võ Thị Kim Thu',1,1, 'OR18B1296', 1),
('hieu@gmail.com', '0987654321', '123456', N'Lê Minh Hiếu',7,1, 'GP09B3191', 1),
('long@gmail.com', '0934567890', '123456', N'Ngô Ðình Thiên Long',2, 1, 'MA15B2278', 1),
('phuong@gmail.com', '0956789012', '123456',N'Trần Thị Hoài Phương',1, 1, 'OR03B0045', 1),
('hong@gmail.com', '0987654321', '123456',N'Vũ Thị Bích Hồng',1, 1, 'GP08B4321', 1),
('duy@gmail.com', '0912345678', '123456', N'Lê Văn Khánh Duy',9,1, 'MA17B1654', 1),
('thu@gmail.com', '0975123456', '123456',N'Nguyễn Thị Anh Thư',10, 1, 'OR13B0903', 1),
('ta23m@gmail.com', '0912345678', '123456',N'Phạm Minh Tâm',2, 1, 'GP06B3102', 1),
('anh4@gmail.com', '0923456789', '123456', N'Ðỗ Ngọc Ánh',5,1, 'MA04B2019', 1),
('tuan23@gmail.com', '0965432198', '123456', N'Trần Thanh Tuấn',9,1, 'OR02B0074', 1),
('thu123@gmail.com', '0998765432', '123456', N'Võ Thành Thư',2, 1, 'GP11B1287', 1),
('tam123123@gmail.com', '0912876543', '123456', N'Lê Thị Hoàng Tâm',1,1, 'MA09B1111', 1),
('yen123123@gmail.com', '0943215678', '123456', N'Ngô Ðức Yên',3,1, 'OR19B1156', 1),
('thang1234@gmail.com', '0967890123', '123456',N'Trần Thị Ngọc Thăng',4, 1, 'GP15B3654', 1),
('anh123@gmail.com', '0987654321', '123456', N'Vũ Minh Anh',5,1, 'MA05B2468', 1),
('hieu12312@gmail.com', '0934567890', '123456', N'Trần Hoàng Hiếu',2,1, 'OR12B0678', 1),
('trang1231223@gmail.com', '0975123456', '123456', N'Thùy Trang',3,1, 'GP03B4921', 1),
('anh1231232@gmail.com', '0912345678', '123456', N'Phúc Anh',3,1, 'MA20B1974', 1),
('quan12312@gmail.com', '0355412154', '123456',N'Hoàng Văn Quân',1, 1, 'OR08B1589', 1),
('aduvip@gmail.com', '0355412154', '123456',N'Hoàng Văn Nghĩa',3, 1, 'GP17B3612', 1)

insert into BlockResource(BID, RID, quantity) values 
(1, 1, 10), (1, 2, 43), (1, 16, 125), (1, 3, 42), (1, 7, 86), (1, 8, 23), (1, 10, 2),
(2, 3, 4), (2, 5, 23), (2, 6, 13), (2, 8, 13), (2, 16, 13),
(3, 4, 20), (3, 11, 20),
(4, 2, 3), (4, 4, 20), (4, 9, 10), (4, 15, 19), (4, 16, 19),
(5, 3, 23), (5, 9, 11),
(6, 8, 13), (6, 6, 23), (6, 16, 23), (6, 11, 1), (6, 2, 1),
(7, 11, 8), (7, 3, 12), (7, 2, 3), (7, 1, 3)

insert into Supplier(name, email, phone, address) values 
('VinHomes Cleaning Service', 'vinhomes@gmail.com', '0283123456', N' Vinhomes Grandpark, Thành phố Thủ Ðức, Thành phố Hồ Chí Minh'),
('Clean & Fix Home Services', 'cleanfixhomes@gmail.com', '0283123457', N'456 Nguyễn Duy Trinh, Phường Linh Chiểu, Thành phố Thủ Ðức, Thành phố Hồ Chí Minh'),
('Fresh Living Home Solution', 'freshlivingsolution@gmail.com', '0283123458', N'789 Phạm Văn Đồng, Phường Bình Thọ, Thành phố Thủ Ðức, Thành phố Hồ Chí Minh'),
('Neat & Tidy Home Care', 'neattidyhomes@gmail.com', '0283123459', N'321 Bung Ông Thoàn, Phường Tam Bình, Thành phố Thủ Ðức, Thành phố Hồ Chí Minh'),
('Spotless Home Services', 'spotlesshomes@gmail.com', '0283123460', N'654 Tăng Nhơn Phú A, Phường Tăng Nhơn Phú A, Thành phố Thủ Ðức, Thành phố Hồ Chí Minh'),
('Homecare Solutions Co.', 'homecaresolutions@gmail.com', '0283123461', N'987 Đường Lê Văn Việt, Phường Hiệp Bình Chánh, Thành phố Thủ Ðức, Thành phố Hồ Chí Minh'),
('FreshStart Cleaning & Repairs', 'freshstartservices@gmail.com', '0283123462', N'753 Lê Văn Quới, Phường Tân Phú, Thành phố Thủ Ðức, Thành phố Hồ Chí Minh'),
('BrightHome Repair & Maintenance', 'brighthomerepair@gmail.com', '0283123463', N'159 Linh Ðông, Phường Linh Ðông, Thành phố Thủ Ðức, Thành phố Hồ Chí Minh'),
('QuickFix Home Services', 'quickfixhomeservices@gmail.com', '0283123464', N'495 Nguyễn Duy Trinh, Phường Linh Trung, Thành phố Thủ Ðức, Thành phố Hồ Chí Minh'),
('ShinePro Cleaning & Handyman Services', 'shineproservices@gmail.com', '0283123465', N'732 Phạm Văn Đồng, Phường Linh Xuân, Thành phố Thủ Ðức, Thành phố Hồ Chí Minh')

insert into Service(name, lower_price, upper_price, description, category_id, supplier_id) values 
('Carpet Cleaning', 50, 100, 'We clean carpets using steam and eco-friendly products.', 1, 1),
('Window Cleaning', 40, 80, 'We clean windows inside and outside using professional tools.', 1, 1),
('Plumbing Repair', 60, 120, 'We fix leaks, clogs, and other plumbing issues.', 2, 2),
('Electrical Repair', 70, 140, 'We repair wiring, outlets, switches, and other electrical problems.', 2, 1),
('Security Guard', 100, 200, 'We provied trained security guards for your building.', 3, 3),
('CCTV Installation', 150, 300, 'We install and maintain CCTV cameras for your building.', 3, 4),
('Lawn Mowing', 30, 60, 'We mow your lawn and trim the edges.', 4, 5),
('Tree Pruning', 50, 100, 'We prune your tree, and remove dead branches.', 4, 4),
('Termite Treatment', 200, 400, 'We treat your building for termites and prevent infestation.', 4, 1),
('Carpet Cleaning', 50, 100, 'We clean carpets using steam and eco-friendly products.', 1, 3),
('Upholstery Cleaning', 60, 120, 'We clean sofas, chairs, and other upholdstery using steam and eco-friendly products.', 1, 5),
('Floor Waxing', 80, 160, 'We wax and polish your floors using high-quality products.', 1, 2),
('HVAC Repair', 90, 180, 'We repair and service your heating ventilation, and air conditioning systems.', 2, 4),
('Painting Service', 100, 200, 'We paint your walls, ceilings, and doors using professional painters.', 2, 6),
('Alarm System', 120, 240, 'We install and maintain alarm systems for your building.', 3, 5),
('Fire Safety', 140, 280, 'We install and maintain fire extinguishers, sprinklers, and smoke directors for your building.', 3, 2),
('Garden Design', 150, 300, 'We design and create beautiful gardens for your building.', 4, 3),
('Snow Removal', 40, 80, 'We remove snow from your sidewalks, driveways, and parking lots.', 4, 1),
('Rodent Control', 180, 360, 'We eliminate rodents from your building and prevent them from coming back.', 3, 8),
('Carpet Cleaning', 50, 100, 'We clean carpets using steam and eco-friendly products.', 1, 8),
('Window Cleaning', 40, 80, 'We clean windows inside and outside using professional tools.', 1, 5),
('Plumbing Repair', 60, 120, 'We fix leaks, clogs, and other plumbing issues.', 2, 4),
('Electrical Repair', 70, 140, 'We repair wiring, outlets, switches, and other electrical problems.', 2, 3),
('Security Guard', 100, 200, 'We provied trained security guards for your building.', 3, 6)

INSERT INTO dbo.Orders(time, delivery_time, status, UID, EID, note)
VALUES
    ('2023-01-29 08:00:00', '2023-01-29 15:00:00', 'Pending', 18, 1, 'Order pending'),
	('2023-01-29 08:00:00', '2023-01-29 15:00:00', 'Pending', 18, NULL, 'Order pending'),
    ('2023-04-01 09:30:00', '2023-04-01 16:30:00', 'Pending', 19, 3, 'Waiting for confirmation'),
    ('2023-01-29 10:15:00', '2023-01-29 17:15:00', 'Pending', 20, 4, 'Order processing'),
    ('2023-04-01 11:45:00', '2023-04-01 18:45:00', 'Pending', 22, 6, NULL),
    ('2023-01-29 12:30:00', '2023-01-29 19:30:00', 'Pending', 19, 7, NULL),
    ('2023-04-01 14:00:00', '2023-04-01 21:00:00', 'Pending', 25, 9, NULL),
    ('2023-01-29 15:45:00', '2023-01-29 22:45:00', 'Failed', 26, 12, 'Delivery failed'),
    ('2023-04-01 16:30:00', '2023-04-01 23:30:00', 'Failed', 28, 10, NULL),
    ('2023-01-29 17:00:00', '2023-01-30 00:00:00', 'Failed', 25, 3, NULL),
    ('2023-04-01 18:15:00', '2023-04-01 01:15:00', 'Failed', 18, 6, 'Item out of stock'),
    ('2023-01-29 19:30:00', '2023-01-30 02:30:00', 'Completed', 19, 1, 'Order delivered on time'),
    ('2023-04-02 10:00:00', '2023-04-02 17:00:00', 'Completed', 20, 3, 'Customer satisfied with the product'),
    ('2023-01-30 11:15:00', '2023-01-30 18:15:00', 'Completed', 22, 4, 'Positive feedback received'),
    ('2023-04-02 14:45:00', '2023-04-02 21:45:00', 'Completed', 18, 6, 'Great service provided'),
    ('2023-01-30 17:30:00', '2023-01-31 00:30:00', 'Completed', 18, 7, 'Order completed successfully');
	   	 


INSERT INTO OrderDetail (orderHeader_id, service_id, category_id, price) 
VALUES
(1, 3, 2, 120),
(1, 2, 1, 70),
(1, 5, 3, 150),

(2, 5, 3, 200),
(3, 2, 1, 80),
(4, 4, 2, 140),
(5, 8, 4, 100),
(6, 3, 2, 120),
(6, 5, 3, 200),
(6, 2, 1, 80),

(7, 4, 2, 140),
(8, 8, 4, 100),
(9, 6, 3, 300),
(10, 1, 1, 100),

(11, 7, 4, 60),
(11, 7, 4, 40),
(11, 8, 4, 90),
(12, 9, 4, 400),

(14, 3, 2, 120),
(14, 5, 3, 200),
(14, 2, 1, 80),

(15, 2, 1, 80),
(15, 4, 2, 140),
(15, 8, 4, 100)
GO

SET NOCOUNT OFF
raiserror('The Vinhomes4 database in now ready for use.',0,1)
GO