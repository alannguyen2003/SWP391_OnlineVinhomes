/*Chạy hai lệnh này trước để tạo database*/
create database Vinhomes ;
use Vinhomes;
/*Chạy hết tất cả đoạn sau này*/
create table BlockVin (
	BID int identity(1,1),
	name nvarchar(50),
	primary key (BID)
)

create table Employee (
	EID int identity(1,1),
	name nvarchar(100),
	password nvarchar(40),
	phone nvarchar(15),
	role bit,
	email nvarchar(100),
	manager_id int,
	BID int,
	primary key (EID)
	/*  foreign key manager_id -> Employee(EID)
		foreign key BID -> BlockVin(BID)
	*/
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
	password nvarchar(40),
	room nvarchar(10),
	phone nvarchar(15),
	primary key (UID),
	BID int
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

create table Orders(
	OID int identity(1,1),
	time datetime, 
	note nvarchar(255),
	UID int, 
	EID int, 
	DID int
	/*
		foreign key UID references Resident(UID),
		foreign key EID references Employee(EID),
		foreign key DID references Service(DID)
	*/
)

alter table Employee 
	add foreign key (manager_id) references Employee(EID),
		foreign key (BID) references BlockVin(BID)
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
alter table Orders 
	add foreign key (UID) references Resident(UID), 
		foreign key (EID) references Employee(EID),
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

insert into Employee(name, email, phone, role, password, manager_id, BID) values 
(N'John Doe', 'johndoe@email.com', '0912345151',1 ,'123456', null, 2),
(N'Jane Smith', 'janesmith@email.com', '0987654321',1 ,'123456', null, 1), 
(N'Bob Lee', 'boblee@email.com', '0934567890', 1,'123456', null, 3), 
(N'Đỗ Quang Huy', 'doquanghuy@email.com', '0978912345', 1,  '123456' , null, 7),
(N'Vũ Thị Mai', 'vumai@email.com', '0923456789', 1, '123456', null, 6),
(N'Kelly Lee', 'kellylee@email.com', '0923456789', 1, '123456', null, 5),
(N'Hoàng Văn Nam', 'hoangnam@email.com', '0912345678',1 , '123456', null, 4),
(N'Emma Lin', 'emmalin@email.com', '0912345678', 0, '123456', 2, 1),
(N'Sara Chen', 'sarachen@email.com', '0956789012', 0, '123456', 3, 3),
(N'Tom Chen', 'tomchen@email.com', '0912345678', 0, '123456', 1, 2),
(N'Nguyễn Văn An', 'nguyenvanan@email.com', '0912345678', 0, '123456', 4, 7),
(N'Bùi Thị Hương', 'buihuong@email.com', '0987654321', 0, '123456', 5, 6)

insert into Resident(name, phone, password, room, BID) values 
(N'Nguyễn Thị Thu', '0987654321', '123456', 'MA03B0156', 3),
(N'Trần Thanh Tùng', '0912345678', '123456', 'OR11B0332', 2), 
(N'Vũ Anh Tuấn', '0934567890', '123456', 'GP14B2073', 3),
(N'Lê Thị Hồng Nhung', '0975123456','123456', 'MA10B1147', 1),
(N'Hoàng Thục Anh', '0909876543', '123456', 'GP20B4615', 5),
(N'Đỗ Thành Nam', '0943215678', '123456', 'MA12B0312', 6),
(N'Nguyễn Thị Ngọc Ánh', '0965432198', '123456', 'OR16B1033', 3),
(N'Phan Đình Quân', '0998765432', '123456', 'GP01B2479', 2),
(N'Trần Thị Trang', '0967890123', '123456', 'MA07B0821', 2),
(N'Nguyễn Văn Toàn', '0943567128', '123456', 'OR18B1296', 4),
(N'Võ Thị Kim Thư', '0912876543', '123456', 'GP09B3191', 1),
(N'Lê Minh Hiếu', '0987654321', '123456', 'MA15B2278', 7),
(N'Ngô Đình Thiên Long', '0934567890', '123456', 'OR03B0045', 2),
(N'Trần Thị Hoài Phương', '0956789012', '123456', 'GP08B4321', 1),
(N'Vũ Thị Bích Hồng', '0987654321', '123456', 'MA17B1654', 1),
(N'Lê Văn Khánh Duy', '0912345678', '123456', 'OR13B0903', 9),
(N'Nguyễn Thị Anh Thư', '0975123456', '123456', 'GP06B3102', 10),
(N'Phạm Minh Tâm', '0912345678', '123456', 'MA04B2019', 2),
(N'Hoàng Thị Hồng Nhung', '0909876543', '123456', 'OR02B0074', 3),
(N'Đỗ Ngọc Ánh', '0923456789', '123456', 'GP11B1287', 5),
(N'Nguyễn Văn Tuấn', '0965432198', '123456', 'MA09B1111', 8),
(N'Trần Thị Thu', '0998765432', '123456', 'OR19B1156', 9),
(N'Võ Thành Tâm', '0912876543', '123456', 'GP15B3654', 2),
(N'Lê Thị Hoàng Yến', '0943215678', '123456', 'MA05B2468', 1),
(N'Ngô Đức Thắng', '0967890123', '123456', 'OR12B0678', 3),
(N'Trần Thị Ngọc Ánh', '0987654321', '123456', 'GP03B4921', 4),
(N'Vũ Minh Hiếu', '0934567890', '123456', 'MA20B1974', 5),
(N'Phạm Thị Trang', '0975123456', '123456', 'OR08B1589', 2),
(N'Hoàng Văn Quân', '0912345678', '123456', 'GP17B3612', 1)