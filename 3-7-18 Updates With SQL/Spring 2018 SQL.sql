
drop table if exists DayMembership;
drop table if exists ParkingPass;
drop table if exists YearMembership;
drop table if exists EquipmentRental;
drop table if exists Invoice;
drop table if exists Member;
drop table if exists Person;
drop table if exists Address;
create table Address (
	street varchar(255) unique, 
	city varchar(255) unique,
	state varchar(255) unique, 
	zip varchar(255) unique, 
	country varchar(255)  unique,
	Primary key (street)
);
create table Person (
	personCode varchar(255) unique not null, 
	Primary key (personCode), 
    firstName varchar (255), 
    lastName varchar(255), 
    address varchar(255), 
    foreign key (address) references Address (street), 
    emails varchar(255)
);
create table Member ( 
	memberCode varchar(255) unique not null, 
    Primary key (memberCode), 
    person varchar(255) not null, 
    foreign key (person) references Person (personCode) , 
    address varchar(255), 
    foreign key (address) references Address(street), 
    memName varchar(255), 
    memType varchar(1)
);
create table Invoice (
	invoiceCode varchar (255) unique not null, 
	Primary key (invoiceCode), 
	member varchar (255), 
    foreign key (member) references  Member (memberCode), 
    personalTrainer varchar (255), 
    foreign key (personalTrainer) references Person (personCode), 
    discount double, 
    tax double, 
    subtotal double, 
    total double,
	timeDate varchar(255),
    check (total>subtotal),
    check (total>0),
    check (tax>0),
    check (subtotal>0)
);
create table ParkingPass ( 
	ID int (11) AUTO_INCREMENT not null,
    invoiceCode varchar(255), 
    foreign key (invoiceCode) references Invoice(invoiceCode),
    quanitity int default 1,
    productCode varchar (255) not null, 
    Primary key (ID), 
    address varchar(255), 
    foreign key (address) references Address (street), 
    total double ,
    tax double, 
    discount double default 0.00, 
    identifier varchar(255) default 'P', 
    subtotal double , 
    timeDate varchar(255),
    check (total>subtotal),
    check (total>0),
    check (tax>0),
    check (subtotal>0),
    
);
create table EquipmentRental (
		ID int (11) AUTO_INCREMENT not null,
		invoiceCode varchar(255), 
        foreign key (invoiceCode) references Invoice(invoiceCode),
        quanitity int default 1,
        productCode varchar (255) not null, 
        Primary key (ID), 
        address varchar(255), 
        foreign key (address) references Address (street), 
        total double, 
        tax double, 
        discount double default 0.00, 
        identifier varchar(255) default 'R', 
        subtotal double, 
        timeDate varchar(255), 
        RentalName varchar(255),
        check (total>subtotal),
		check (total>0),
		check (tax>0),
		check (subtotal>0)
);
create table DayMembership (
	ID int (11) AUTO_INCREMENT not null,
	invoiceCode varchar(255), 
    foreign key (invoiceCode) references Invoice(invoiceCode), 
    quanitity int default 1 ,
    productCode varchar (255) not null, 
    Primary key (ID), 
    address varchar(255), 
    total double, 
    tax double, 
    discount double default 0.00, 
    identifier varchar(255) default 'D', 
    subtotal double, 
    timeDate varchar(255),
    check (total>subtotal),
    check (total>0),
    check (tax>0),
    check (subtotal>0)
);
create table YearMembership (
	ID int (11) AUTO_INCREMENT not null,
	invoiceCode varchar(255), 
    foreign key (invoiceCode) references Invoice(invoiceCode),
    quanitity int default 1 ,
    productCode varchar (255) not null, 
    Primary key (ID), 
    address varchar(255), 
    total double, 
    tax double, 
    discount double default 0.00, 
    identifier varchar(255) default 'Y', 
    subtotal double, 
    timeDateStart varchar(255),
	timeDateEnd  varchar(255),
    check (total>subtotal),
    check (total>0),
    check (tax>0),
    check (subtotal>0)
);

-- Assignment 4 Queries
-- 1
select * from Person;
-- 2
update Person set emails = 'hello@gmail.com' where PersonCode= '';
-- 3
update YearMembership set Address= (Select Street from Address where Street ='') where ID=4;
-- 4
Delete from YearMembership where ID= 7;
-- 5
Select * from ParkingPass where invoiceCode ='';
Select * from EquipmentRental where invoiceCode='';
select * from DayMembership where invoiceCode='';
select * from YearMembership where invoiceCode= '';
-- 6
select * from Invoice where member = '';
-- 7
Insert into ParkingPass (invoiceCode, quantity, productCode, address,
total,tax,discount,identifier,subtotal,timeDate) values (
(select invoiceCode from Invoice where  invoiceCode= ''), 1, 'hello',(select street from Address where Street =''),
0.00,0.00,0.00,'P', 0.00,2018-09-23);
-- 8
select (subtotal/quantity) from YearMemberhsip;
-- 9
select * from YearMembership where timeDate='';
-- 10 
select * from Invoice order by personalTrainer;
-- 11
select * from Invoice where InvoiceCode = (Select invoiceCode from YearMembership where productCode ='');
-- 12
select sum(total) from YearMembership where timeDate= '';
-- 13
select(select sum(quantity) from ParkingPass) + (select sum(quantity) from EquipmentRental);
-- 14
select * from YearMembership where incvoiceCode='' having count >1;
select * from DayMembership where incvoiceCode='' having count >1;
-- 15
select * from Invoice where member=PersonalTrainer;
-- end