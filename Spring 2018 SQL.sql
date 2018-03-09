
drop table if exists DayMembership;
drop table if exists ParkingPass;
drop table if exists YearMembership;
drop table if exists EquipmentRental;
drop table if exists Invoice;
drop table if exists Member;
drop table if exists Person;
drop table if exists Address;
create table Address (
	id int auto_increment,
	street varchar(255), 
	city varchar(255),
	state varchar(255), 
	zip varchar(255), 
	country varchar(255),
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
    check (subtotal>0)
    
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

-- Population queries

-- Address
insert into Address (street, city, state, zip, country) values ('1060 West Addison Street','Chicago','IL','60613','USA');
insert into Address (street, city, state, zip, country) values ('123 N 1st Street','Omaha','NE','68116','USA');
insert into Address (street, city, state, zip, country) values ('8753 West 3rd Ave.','Dallas','TX','75001','USA');
insert into Address (street, city, state, zip, country) values ('123 Friendly Street','Ottawa','ON','K1A 0G9','Canada');
insert into Address (street, city, state, zip, country) values ('1 Wall Street','New York','NY','10005-0012','USA');
insert into Address (street, city, state, zip, country) values ('321 Bronx Street','New York','NY','10004','USA');
insert into Address (street, city, state, zip, country) values ('301 Front St W','Toronto','ON','M5V 2T6','Canada');
insert into Address (street, city, state, zip, country) values ('1 Blue Jays Way', 'Toronto', 'ON', 'M5V 1J1', 'Canada');
insert into Address (street, city, state, country) values ('Campos El290','Mexico City', 'FD', 'Mexico');
insert into Address (street, city, state, zip, country) values ('Avery Hall','Lincoln','NE','68503','USA');
insert into Address (street, city, state, zip, country) values ('126-01 Roosevelt Ave', 'Flushing', 'NY','11368','USA');
insert into Address (street, city, state, zip, country) values ('1 MetLife Stadium Dr', 'East Rutherford', 'NJ','07073','USA');
insert into Address (street, city, state, zip, country) values ('1 E 161st St', 'Bronx', 'NY','10451','USA');
insert into Address (street, city, state, zip, country) values ('700 E Grand Ave', 'Chicago', 'IL', '60611','USA');
insert into Address (street, city, state, zip, country) values ('333 W 35th St', 'Chicago', 'IL','60616','USA');
insert into Address (street, city, state, zip, country) values ('800 West 7th Street', 'Albuquerque', 'NM', '87105','USA');
insert into Address (street, city, state, zip, country) values ('123 Cabo San Lucas', 'Los Cabos', 'BCS','11111', 'Mexico');
insert into Address (street, city, state, zip, country) values ('259 Concorde Suites','Lincoln','NE','68588-0115','USA');
insert into Address (street, city, state, zip, country) values ('1223 Oldfather Hall','Lincoln','NE','68503','USA');
insert into Address (street, city, state, zip, country) values ('123 Venture Way','Culver City','CA','90230','USA');
insert into Address (street, city, state, zip, country) values ('9800 Savage Rd','Fort Meade','MD','20755','USA');
insert into Address (street, city, state, zip, country) values ('456 West 7th St.','Omaha','NE','68500','USA');
insert into Address (street, city, state, zip, country) values ('1060 West Addison,Chicago,IL,60601,USA');

-- Person
-- change addresses to Address.id
insert into Person (personCode, lastName, firstName, address, emails) values ('944c','Castro', 'Starlin','1060 West Addison Street','scastro@cubs.com,starlin_castro13@gmail.com');
insert into Person (personCode, lastName, firstName, address, emails) values ('306a','Sampson', 'Brock','123 N 1st Street','brock_f_sampson@gmail.com,bsampson@venture.com');
insert into Person (personCode, lastName, firstName, address, emails) values ('55bb','McCarthy', 'Miles','8753 West 3rd Ave.','obrien@ds9.com,obrien@enterprise.gov');
insert into Person (personCode, lastName, firstName, address) values ('2342','Spalding', 'Jeff','123 Friendly Street');
insert into Person (personCode, lastName, firstName, address) values ('aef1','Gekko', 'Gordon','1 Wall Street');
insert into Person (personCode, lastName, firstName, address, emails) values ('321f','Fox','Bud','321 Bronx Street','bfox@gmail.com,csheen@crazy.net');
insert into Person (personCode, lastName, firstName, address, emails) values ('ma12','Sveum', 'Dale','1060 West Addison Street','sveum@cubs.com');
insert into Person (personCode, lastName, firstName, address, emails) values ('321nd','Hartnell', 'William','1060 West Addison Street','whartnell@doctors.com,dr@who.com');
insert into Person (personCode, lastName, firstName, address, emails) values ('nf32a','Troughton', 'Patrick','1060 West Addison Street','ptroug@cse.unl.edu,ptrou32@unl.edu');
insert into Person (personCode, lastName, firstName, address, emails) values ('321na','Pertwee', 'Jon','301 Front St W','jpet@whofan.com');
insert into Person (personCode, lastName, firstName, address, emails) values ('231','Baker','Tom','1 Blue Jays Way','famousdoc@who.com,tbaker@cse.unl.edu,mostfamous@whovian.com,thedoctor@bbc.com');
insert into Person (personCode, lastName, firstName, address, emails) values ('6doc','Hurndall', 'Richard','Campos El290','rhurndall@cse.unl.edu,richard@unl.edu');
insert into Person (personCode, lastName, firstName, address, emails) values ('321dr','Baker', 'C.','Avery Hall','dr@baker.com');
insert into Person (personCode, lastName, firstName, address, emails) values ('1svndr','McCoy','Sylvester','126-01 Roosevelt Ave','slyguy@hotmail.com,mccoy@whofan.com');
insert into Person (personCode, lastName, firstName, address, emails) values ('123lst','McGann', 'Paul','1 MetLife Stadium Dr','pmcgann@mlb.com,foo@bar.com,pmc@unl.edu');
insert into Person (personCode, lastName, firstName, address, emails) values ('nwdoc1','Eccleston', 'Chris','1 E 161st St','newguy@whovian.com');
insert into Person (personCode, lastName, firstName, address, emails) values ('2ndbestd','Tennant', 'David','700 E Grand Ave','actor@shakespeare.com,tdavid@unl.edu');
insert into Person (personCode, lastName, firstName, address, emails) values ('wrddoc','Smith', 'Matt','333 W 35th St','msmith@who.com,thedoc@cse.unl.edu');
insert into Person (personCode, lastName, firstName, address) values ('bbchar','Ehrmantraut', 'Kaylee','800 West 7th Street');
insert into Person (personCode, lastName, firstName, address) values ('doc05','Davison', 'Peter','123 Cabo San Lucas');

-- Member
-- change addresses to Address.id
insert into Member (memberCode, memType, person, memName, address) values ('M001','G','231','Clark Consultants','259 Concorde Suites');
insert into Member (memberCode, memType, person, memName, address) values ('M002','S','944c','CAS International Fellows');
insert into Member (memberCode, memType, person, memName, address) values ('M003','S','306a','Valueless Club');
insert into Member (memberCode, memType, person, memName, address) values ('M004','G','321f','Stony Brook');
insert into Member (memberCode, memType, person, memName, address) values ('M001','G','231','Clark Consultants','259 Concorde Suites');






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