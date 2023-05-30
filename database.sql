/*Chạy hai lệnh này trước để tạo database*/
create database Vinhomes2 ;
Go
use Vinhomes2;
Go
/*Chạy hết tất cả đoạn sau này*/
create table BlockVin (
	BID int identity(1,1),
	name nvarchar(50),
	primary key (BID)
)

create table [Role] (
	RID int identity(1,1),
	name nvarchar(50),
	primary key(RID)
)

create table Account (
	AID int identity(1,1),
	phone nvarchar(15),
	email nvarchar(100),
	password nvarchar(50),
	roleId int,
	primary key (AID),
	foreign key (roleId) references Role(RID)
)



create table Employee (
	EID int identity(1,1),
	name nvarchar(100),
	manager_id int,
	BID int,
	AID int,
	primary key (EID),
	foreign key (AID) references Account(AID),
	foreign key (manager_id) references Employee(EID),
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

create table Resident (
	UID int identity(1,1),
	name nvarchar(255),
	room nvarchar(10),
	BID int,
	AID int,
	primary key (UID),
	foreign key (AID) REFERENCES Account(AID)
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
	lower_price int,
	upper_price int,
	supplier_id int,
	category_id int,
	primary key (service_id)
	/* 
		foreign key (supplier_id) references Supplier(SID),
		foreign key (category_id) references Category(CID)
	*/
)

create table Feedback (
	UID int,
	DID int, 
	/*
	foreign key UID references Resident(UID)
	foreign key DID references Service(DID)
	*/
	message nvarchar(255),
	name nvarchar(100),
	contact_number varchar(20),
	email nvarchar(50)
)

--create table Orders(
--	OID int identity(1,1),
--	time datetime, 
--	note nvarchar(255),
--	UID int, 
--	EID int, 
--	DID int
--	/*
--		foreign key UID references Resident(UID),
--		foreign key EID references Employee(EID),
--		foreign key DID references Service(DID)
--	*/
--)
create table Orders(
	OID int identity(1,1) PRIMARY KEY NOT NULL,
	time datetime, 
	[status] [varchar](30) NOT NULL default('Pending'),
	UID INT REFERENCES dbo.Resident(UID), 
	EID INT REFERENCES dbo.Employee(EID), 
	note nvarchar(255)

)
CREATE TABLE [dbo].[OrderDetail](
	[id] [int] IDENTITY(1,1) PRIMARY KEY NOT NULL,
	[orderHeaderId] [int] references Orders(OID) NOT NULL,
	[serviceId] [int] references dbo.Service(service_id) NOT NULL,
	[categoryId] [INT] NOT NULL,
	[min_price] [int] NOT NULL,
	[max_price] [int] NOT NULL
)


alter table BlockResource 
	add foreign key (BID) references BlockVin(BID),
		foreign key (RID) references Resource(RID)
alter table Resident 
	add foreign key (BID) references BlockVin(BID)
alter table Service 
	add foreign key (supplier_id) references Supplier(SID),
		foreign key (category_id) references Category(CID)
alter table Feedback 
	add FID int identity(1,1) primary key,
		foreign key (UID) references Resident(UID),
		foreign key (DID) references Service(service_id)


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

insert into BlockResource(BID, RID, quantity) values 
(1, 1, 10), (1, 2, 43), (1, 16, 125), (1, 3, 42), (1, 7, 86), (1, 8, 23), (1, 10, 2),
(2, 3, 4), (2, 5, 23), (2, 6, 13), (2, 8, 13), (2, 16, 13),
(3, 4, 20), (3, 11, 20),
(4, 2, 3), (4, 4, 20), (4, 9, 10), (4, 15, 19), (4, 16, 19),
(5, 3, 23), (5, 9, 11),
(6, 8, 13), (6, 6, 23), (6, 16, 23), (6, 11, 1), (6, 2, 1),
(7, 11, 8), (7, 3, 12), (7, 2, 3), (7, 1, 3)
 
insert into Supplier(name, email, phone, address) values 
('Sparkling Homes Cleaning Service', 'sparklinghomes@gmail.com', '0283123456', N'123 Đường Trường Sinh, Phường Hiệp Phú, Thành phố Thủ Đức, Thành phố Hồ Chí Minh'),
('Clean & Fix Home Services', 'cleanfixhomes@gmail.com', '0283123457', N'456 Nguyễn Duy Trinh, Phường Linh Chiểu, Thành phố Thủ Đức, Thành phố Hồ Chí Minh'),
('Fresh Living Home Solution', 'freshlivingsolution@gmail.com', '0283123458', N'789 Phạm Văn Đồng, Phường Bình Thọ, Thành phố Thủ Đức, Thành phố Hồ Chí Minh'),
('Neat & Tidy Home Care', 'neattidyhomes@gmail.com', '0283123459', N'321 Bưng Ông Thoàn, Phường Tam Bình, Thành phố Thủ Đức, Thành phố Hồ Chí Minh'),
('Spotless Home Services', 'spotlesshomes@gmail.com', '0283123460', N'654 Tăng Nhơn Phú A, Phường Tăng Nhơn Phú A, Thành phố Thủ Đức, Thành phố Hồ Chí Minh'),
('Homecare Solutions Co.', 'homecaresolutions@gmail.com', '0283123461', N'987 Đường Lê Văn Việt, Phường Hiệp Bình Chánh, Thành phố Thủ Đức, Thành phố Hồ Chí Minh'),
('FreshStart Cleaning & Repairs', 'freshstartservices@gmail.com', '0283123462', N'753 Lê Văn Quới, Phường Tân Phú, Thành phố Thủ Đức, Thành phố Hồ Chí Minh'),
('BrightHome Repair & Maintenance', 'brighthomerepair@gmail.com', '0283123463', N'159 Linh Đông, Phường Linh Đông, Thành phố Thủ Đức, Thành phố Hồ Chí Minh'),
('QuickFix Home Services', 'quickfixhomeservices@gmail.com', '0283123464', N'495 Nguyễn Duy Trinh, Phường Linh Trung, Thành phố Thủ Đức, Thành phố Hồ Chí Minh'),
('ShinePro Cleaning & Handyman Services', 'shineproservices@gmail.com', '0283123465', N'732 Phạm Văn Đồng, Phường Linh Xuân, Thành phố Thủ Đức, Thành phố Hồ Chí Minh')

insert into Category(name) values 
('Cleaning'), ('Maintenance'), ('Security'), ('Pest Control')

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


insert into Role(name) values
(N'Resident'),
(N'Employee'),
(N'Block Manager'),
(N'Admin')

insert into Account(email, phone, password, roleId) values 
('johndoe@email.com', '0912345151','123456', 4),
('janesmith@email.com', '0987654321','123456', 4),
('boblee@email.com', '0934567890', '123456', 4),
('doquanghuy@email.com', '0978912345', '123456', 4),
('vumai@email.com', '0923456789', '123456', 4),
('kellylee@email.com', '0923456789', '123456', 4),
('hoangnam@email.com', '0912345678', '123456', 4),
('emmalin@email.com', '0912345678', '123456', 3),
('sarachen@email.com', '0956789012', '123456', 3),
('tomchen@email.com', '0912345678', '123456', 3),
('nguyenvanan@email.com', '0912345678', '123456', 3),
('buihuong@email.com', '0987654321', '123456', 3),
('thu1@gmail.com', '0987654321', '123456',1),
('tung@gmail.com', '0912345678', '123456', 1),
('tuan@gmail.com','0934567890', '123456', 1),
('anh@gmail.com', '0909876543', '123456', 1),
('nam@gmail.com', '0943215678', '123456', 1),
('anh1@gmail.com', '0965432198', '123456', 1),
('quan@gmail.com', '0998765432', '123456', 1),
('trang@gmail.com', '0967890123', '123456', 1),
('toan@gmail.com', '0943567128', '123456', 1),
('thu2@gmail.com', '0912876543', '123456', 1),
('hieu@gmail.com', '0987654321', '123456', 1),
('long@gmail.com', '0934567890', '123456', 1),
('phuong@gmail.com', '0956789012', '123456', 1),
('hong@gmail.com', '0987654321', '123456', 1),
('duy@gmail.com', '0912345678', '123456', 1),
('thu@gmail.com', '0975123456', '123456', 1),
('ta23m@gmail.com', '0912345678', '123456', 1),
('nhung2@gmail.com', '0909876543', '123456', 1),
('anh4@gmail.com', '0923456789', '123456', 1),
('tuan23@gmail.com', '0965432198', '123456', 1),
('thu123@gmail.com', '0998765432', '123456', 1),
('tam123123@gmail.com', '0912876543', '123456', 1),
('yen123123@gmail.com', '0943215678', '123456', 1),
('thang1234@gmail.com', '0967890123', '123456', 1),
('anh123@gmail.com', '0987654321', '123456', 1),
('hieu12312@gmail.com', '0934567890', '123456', 1),
('trang1231223@gmail.com', '0975123456', '123456', 1),
('anh1231232@gmail.com', '0912345678', '123456', 1),
('quan12312@gmail.com', '0355412154', '123456', 1)


insert into Employee(name, manager_id, BID, AID) values
(N'John Doe', null, 2, 1),
(N'Jane Smith', null, 1, 2), 
(N'Bob Lee', null, 3, 3), 
(N'Đỗ Quang Huy', null, 7, 4),
(N'Vũ Thị Mai', null, 6, 5),
(N'Kelly Lee', null, 5, 6),
(N'Hoàng Văn Nam',  null, 4, 7),
(N'Emma Lin', null, 1, 8),
(N'Sara Chen', null, 3,9),
(N'Tom Chen', null, 2, 10),
(N'Nguyễn Văn An', null, 7, 11),
(N'Bùi Thị Hương', null, 6, 12)


insert into Resident(name, room, BID, AID) values 
(N'Nguyễn Thị Thu', 'MA03B0156', 3, 13),
(N'Trần Thanh Tùng', 'OR11B0332', 2, 14), 
(N'Vũ Anh Tuấn', 'GP14B2073', 3, 15),
(N'Lê Thị Hồng Nhung', 'MA10B1147', 1, 16),
(N'Hoàng Thục Anh', 'GP20B4615', 5, 17),
(N'Đỗ Thành Nam', 'MA12B0312', 6, 18),
(N'Nguyễn Thị Ngọc Ánh', 'OR16B1033', 3, 19),
(N'Phan Đình Quân', 'GP01B2479', 2, 20),
(N'Trần Thị Trang', 'MA07B0821', 2, 21),
(N'Nguyễn Văn Toàn', 'OR18B1296', 4, 22),
(N'Võ Thị Kim Thư', 'GP09B3191', 1, 23),
(N'Lê Minh Hiếu', 'MA15B2278', 7, 24),
(N'Ngô Đình Thiên Long', 'OR03B0045', 2, 25),
(N'Trần Thị Hoài Phương', 'GP08B4321', 1, 26),
(N'Vũ Thị Bích Hồng', 'MA17B1654', 1, 27),
(N'Lê Văn Khánh Duy', 'OR13B0903', 9, 28),
(N'Nguyễn Thị Anh Thư', 'GP06B3102', 10, 29),
(N'Phạm Minh Tâm', 'MA04B2019', 2, 30),
(N'Hoàng Thị Hồng Nhung', 'OR02B0074', 3, 31),
(N'Đỗ Ngọc Ánh', 'GP11B1287', 5, 32),
(N'Nguyễn Văn Tuấn', 'MA09B1111', 8, 33),
(N'Trần Thị Thu', 'OR19B1156', 9, 34),
(N'Võ Thành Tâm', 'GP15B3654', 2, 35),
(N'Lê Thị Hoàng Yến', 'MA05B2468', 1, 36),
(N'Ngô Đức Thắng', 'OR12B0678', 3, 37),
(N'Trần Thị Ngọc Ánh', 'GP03B4921', 4, 38),
(N'Vũ Minh Hiếu', 'MA20B1974', 5, 39),
(N'Phạm Thị Trang', 'OR08B1589', 2, 40),
(N'Hoàng Văn Quân', 'GP17B3612', 1, 41)

INSERT INTO dbo.Orders(time, status, UID, EID, note)
VALUES
	('2019-01-29', 'Failed', 5, 1, NULL),
	('2019-02-17', 'Pending', 6, 3, NULL),
	('2019-04-01', 'Completed', 8, 2, NULL)
    GO

INSERT INTO dbo.OrderDetail (orderHeaderId, serviceId, categoryId, min_price, max_price)
	VALUES
	    (4, 3, 2, 60, 120),
		(4, 5, 3, 100, 200),
		(4, 2, 1, 40, 80),

		(2, 4, 2, 70, 140),
		(2, 8, 4, 50, 100),
		(2, 6, 3, 150, 300),

		(3, 1, 1, 50, 100),
		(3, 7, 4, 30, 60),
		(3, 9, 4, 200, 400),

		(3, 1, 1, 50, 100),
		(3, 7, 4, 30, 60),
		(3, 9, 4, 200, 400)

INSERT INTO dbo.OrderDetail (orderHeaderId, serviceId, categoryId, min_price, max_price)
	VALUES
		(1, 2, 1, 40, 80),
		(1, 7, 4, 30, 60),
		(1, 8, 4, 50, 100),

		(7, 4, 2, 70, 140),
		(7, 8, 4, 50, 100),
		(7, 6, 3, 150, 300)

INSERT INTO dbo.OrderDetail (orderHeaderId, serviceId, categoryId, min_price, max_price)
	VALUES
		(8, 3, 2, 60, 120),
		(8, 5, 3, 100, 200),
		(8, 2, 1, 40, 80)
	GO