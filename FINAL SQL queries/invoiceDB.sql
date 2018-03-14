-- Delete any pre-existing tables to avoid potential erros
drop table if exists DayMembership;
drop table if exists ParkingPass;
drop table if exists YearMembership;
drop table if exists EquipmentRental;
drop table if exists Invoice;
drop table if exists Member;
drop table if exists Person;
drop table if exists Address;

-- Create the Address table
create table Address (
	id int auto_increment,
	street varchar(255), 
	city varchar(255),
	state varchar(255), 
	zip varchar(255), 
	country varchar(255),
	Primary key (id)
);

-- Create the Person table
create table Person (
	personCode varchar(255) unique not null, 
	Primary key (personCode), 
    firstName varchar (255), 
    lastName varchar(255), 
    address int(11), 
    foreign key (address) references Address (id), 
    emails varchar(255)
);
-- Create a composite index that prevents a duplicate Person with the same name and personCode from being added
create unique index Persons on Person (personCode,firstName,lastName);

-- Create the Member table
create table Member ( 
	memberCode varchar(255) unique not null, 
    Primary key (memberCode), 
    person varchar(255) not null, 
    foreign key (person) references Person (personCode) , 
    address int(11), 
    foreign key (address) references Address (id), 
    memName varchar(255),
    memType varchar(1)
);
-- Create a composite index that prevents a duplicate Member with the same name and memberCode from being added
create unique index Members on Member (memberCode, person, memName);

-- Create the Invoice table
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
/* Create a composite index that prevents a duplicate Invoice with the same 
   invoiceCode, member, and personalTrainer from being added */
create unique index Invoices on Invoice (invoiceCode, member, personalTrainer);

-- Create the Parking Pass table
create table ParkingPass ( 
	ID int (11) AUTO_INCREMENT not null,
    invoiceCode varchar(255), 
    foreign key (invoiceCode) references Invoice(invoiceCode),
    quantity int default 1,
    productCode varchar (255) not null, 
    Primary key (ID), 
    total double ,
    tax double, 
    discount double default 0.00, 
    identifier varchar(255) default 'P', 
    subtotal double , 
    check (total>subtotal),
    check (total>0),
    check (tax>0),
    check (subtotal>0)
    
);
/* Create a composite index that prevents a duplicate Parking Pass with the same 
   invoiceCode, and pproductCode from being added */
create unique index pPass on ParkingPass (invoiceCode,productCode);

-- Create the EquipmentRental table
create table EquipmentRental (
		ID int (11) AUTO_INCREMENT not null,
		invoiceCode varchar(255), 
        foreign key (invoiceCode) references Invoice(invoiceCode),
        quantity int default 1,
        productCode varchar (255) not null, 
        Primary key (ID), 
        total double, 
        tax double, 
        discount double default 0.00, 
        identifier varchar(255) default 'R', 
        subtotal double, 
        rentalName varchar(255),
        check (total>subtotal),
		check (total>0),
		check (tax>0),
		check (subtotal>0)
);
/* Create a composite index that prevents a duplicate EquipmentRental with the same 
   invoiceCode, productCode, id, and rentalName from being added */
create unique index Rentals on EquipmentRental (invoiceCode,productCode,id,rentalName);

-- Create the DayMembership table
create table DayMembership (
	ID int (11) AUTO_INCREMENT not null,
	invoiceCode varchar(255), 
    foreign key (invoiceCode) references Invoice(invoiceCode), 
    quantity int default 1 ,
    productCode varchar (255) not null, 
    Primary key (ID), 
    address int(11), 
    foreign key (address) references Address (id), 
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
/* Create a composite index that prevents a duplicate DayMembership with the same 
   invoiceCode, and productCode from being added */
create unique index dPass on DayMembership (invoiceCode,productCode);

-- Create the YearMembership table
create table YearMembership (
	ID int (11) AUTO_INCREMENT not null,
	invoiceCode varchar(255), 
    membershipName varchar(255),
    foreign key (invoiceCode) references Invoice(invoiceCode),
    quantity int default 1 ,
    productCode varchar (255) not null, 
    Primary key (ID), 
    address int(11),
    foreign key (address) references Address (id), 
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
/* Create a composite index that prevents a duplicate YearMembership with the same 
   invoiceCode, productCode, and membershipName from being added */
create unique index Years on YearMembership (invoiceCode,productCode, membershipName);



-- Queries to populate the tables with test cases

-- Add addresses
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
insert into Address (street, city, state, zip, country) values ('1060 West Addison','Chicago','IL','60601','USA');
insert into Address (street, city, state, zip, country) values ('456 West 7th St.','Omaha','NE','68500','USA');
insert into Address (street, city, state, zip, country) values ('179 West Lane','Lincoln','NE','68588-0115','USA'); 
insert into Address (street, city, state, zip, country) values ('3555 South 140th Plaza','Omaha', 'NE', '68144', 'USA'); -- 25
insert into Address (street, city, state, zip, country) values ('755 Willard Drive','Ashwaubenon', 'WI', '54304', 'USA');
insert into Address (street, city, state, zip, country) values ('6301 University Ave','Cedar Falls', 'IA', '50613', 'USA');
insert into Address (street, city, state, zip, country) values ('449 3rd Ave','Omaha','NE','68521-5593','USA');
insert into Address (street, city, state, zip, country) values ('375 Northwoods','Souix City', 'IA', '44982', 'USA');

-- Add Persons
insert into Person (personCode, lastName, firstName, address, emails) values ('944c','Castro', 'Starlin',1,'scastro@cubs.com,starlin_castro13@gmail.com');
insert into Person (personCode, lastName, firstName, address, emails) values ('306a','Sampson', 'Brock',2,'brock_f_sampson@gmail.com,bsampson@venture.com');
insert into Person (personCode, lastName, firstName, address, emails) values ('55bb','McCarthy', 'Miles',3,'obrien@ds9.com,obrien@enterprise.gov');
insert into Person (personCode, lastName, firstName, address) values ('2342','Spalding', 'Jeff',4);
insert into Person (personCode, lastName, firstName, address) values ('aef1','Gekko', 'Gordon',5);
insert into Person (personCode, lastName, firstName, address, emails) values ('321f','Fox','Bud',6,'bfox@gmail.com,csheen@crazy.net');
insert into Person (personCode, lastName, firstName, address, emails) values ('ma12','Sveum', 'Dale',1,'sveum@cubs.com');
insert into Person (personCode, lastName, firstName, address, emails) values ('321nd','Hartnell', 'William',1,'whartnell@doctors.com,dr@who.com');
insert into Person (personCode, lastName, firstName, address, emails) values ('nf32a','Troughton', 'Patrick',1,'ptroug@cse.unl.edu,ptrou32@unl.edu');
insert into Person (personCode, lastName, firstName, address, emails) values ('321na','Pertwee', 'Jon',7,'jpet@whofan.com');
insert into Person (personCode, lastName, firstName, address, emails) values ('231','Baker','Tom',8,'famousdoc@who.com,tbaker@cse.unl.edu,mostfamous@whovian.com,thedoctor@bbc.com');
insert into Person (personCode, lastName, firstName, address, emails) values ('6doc','Hurndall', 'Richard',9,'rhurndall@cse.unl.edu,richard@unl.edu');
insert into Person (personCode, lastName, firstName, address, emails) values ('321dr','Baker', 'C.',10,'dr@baker.com');
insert into Person (personCode, lastName, firstName, address, emails) values ('1svndr','McCoy','Sylvester',11,'slyguy@hotmail.com,mccoy@whofan.com');
insert into Person (personCode, lastName, firstName, address, emails) values ('123lst','McGann', 'Paul',12,'pmcgann@mlb.com,foo@bar.com,pmc@unl.edu');
insert into Person (personCode, lastName, firstName, address, emails) values ('nwdoc1','Eccleston', 'Chris',13,'newguy@whovian.com');
insert into Person (personCode, lastName, firstName, address, emails) values ('2ndbestd','Tennant', 'David',14,'actor@shakespeare.com,tdavid@unl.edu');
insert into Person (personCode, lastName, firstName, address, emails) values ('wrddoc','Smith', 'Matt',15,'msmith@who.com,thedoc@cse.unl.edu');
insert into Person (personCode, lastName, firstName, address) values ('bbchar','Ehrmantraut', 'Kaylee',16);
insert into Person (personCode, lastName, firstName, address) values ('doc05','Davison', 'Peter',17);

-- Add Members
insert into Member (memberCode, memType, person, memName, address) values ('M001','G','231','Clark Consultants',18);
insert into Member (memberCode, memType, person, memName, address) values ('M002','S','944c','CAS International Fellows',19);
insert into Member (memberCode, memType, person, memName, address) values ('M003','S','306a','Valueless Club',20);
insert into Member (memberCode, memType, person, memName, address) values ('M004','G','321f','Stony Brook',21);
insert into Member (memberCode, memType, person, memName, address) values ('M005','G','bbchar','Hewlett Industries',22);
insert into Member (memberCode, memType, person, memName, address) values ('M006','S','321dr','Gregory Smith',23);

-- Add Products
insert into YearMembership (productCode, identifier, timeDateStart, timeDateEnd, address, membershipName, subtotal) 
	values ('b29e','Y','2017-01-13','2018-01-12',24,'Gold Package',450.00);
insert into EquipmentRental (productCode, identifier, rentalName, subtotal) values ('ff23','R','Boxing Glove-Large',4.99);
insert into DayMembership (productCode, identifier, timeDate, address, subtotal) 
	values ('fp12','D','2016-10-21 13:10',25,21.50);
insert into ParkingPass (productCode, identifier, subtotal) values ('90fa','P',25.00);
insert into DayMembership (productCode, identifier, timeDate, address, subtotal) 
	values ('1239','D','2018-01-16 15:30',26,11.00);
insert into DayMembership (productCode, identifier, timeDate, address, subtotal) 
	values ('782g','D','2016-11-10 10:00',27,25.00);
insert into ParkingPass (productCode, identifier, subtotal) values ('3289','P',55.00);
insert into EquipmentRental (productCode, identifier, rentalName, subtotal) values ('32f4','R','Tennis Racket',5.50);
insert into YearMembership (productCode, identifier, timeDateStart, timeDateEnd, address, membershipName, subtotal) 
	values ('3y92','Y','2017-11-24','2018-11-23',28,'Ultimate Workout',550.0);
insert into ParkingPass (productCode, identifier, subtotal) values ('90fb','P',20.00);
insert into YearMembership (productCode, identifier, timeDateStart, timeDateEnd, address, membershipName, subtotal) 
	values ('xer4','Y','2016-10-07','2017-10-06',29,'Bronze Fit',185.0);
insert into EquipmentRental (productCode, identifier, rentalName, subtotal) values ('yp23','R','Cross Country Skis-Large',19.50);

-- Add Invoices
insert into Invoice (invoiceCode, member, personalTrainer, timeDate) values ('INV001','M001','nwdoc1','2016-09-03');
	insert into DayMembership (productCode, quantity, invoiceCode) values ('fp12', 2, 'INV001');
    update DayMembership set identifier = 'D', timeDate = '2016-10-21 13:10', address = 25, subtotal = 21.50 where invoiceCode = 'INV001';
	insert into ParkingPass (productCode, quantity, invoiceCode) values ('3289', 1, 'INV001');
    update ParkingPass set identifier = 'P', subtotal = 55.00 where invoiceCode = 'INV001';
    insert into EquipmentRental (productCode, quantity, invoiceCode) values ('ff23', 4, 'INV001');
	update EquipmentRental set identifier = 'R', rentalName = 'Boxing Glove-Large', subtotal = 4.99 where invoiceCode = 'INV001';
insert into Invoice (invoiceCode, member, personalTrainer, timeDate) values ('INV002','M002','2ndbestd','2016-11-10');
	insert into YearMembership (productCode, quantity, invoiceCode) values ('b29e', 23, 'INV002');
    update YearMembership set identifier = 'Y', timeDateStart = '2017-01-13', timeDateEnd = '2018-01-12', address = 24, membershipName = 'Gold Package', subtotal = 450.00 where invoiceCode = 'INV002';
	insert into DayMembership (productCode, quantity, invoiceCode) values ('1239', 31, 'INV002');
    update DayMembership set identifier = 'D', timeDate = '2018-01-16 15:30', address = 26, subtotal = 11.00 where invoiceCode = 'INV002';
	insert into ParkingPass (productCode, quantity, invoiceCode) values ('90fa', 30, 'INV002');
    update ParkingPass set identifier = 'P', subtotal = 25.00 where invoiceCode = 'INV002';
    insert into EquipmentRental (productCode, quantity, invoiceCode) values ('32f4', 17, 'INV002');
	update EquipmentRental set identifier = 'R', rentalName = 'Tennis Racket', subtotal = 5.50 where invoiceCode = 'INV002';
insert into Invoice (invoiceCode, member, personalTrainer, timeDate) values ('INV003','M005','wrddoc','2016-11-26');
	insert into YearMembership (productCode, quantity, invoiceCode) values ('3y92', 6, 'INV003');
    update YearMembership set identifier = 'Y', timeDateStart = '2017-11-24', timeDateEnd = '2018-11-23', address = 28, membershipName = 'Ultimate Workout', subtotal = 550.00 where invoiceCode = 'INV003';
	insert into DayMembership (productCode, quantity, invoiceCode) values ('782g', 7, 'INV003');
    update DayMembership set identifier = 'D', timeDate = '2016-11-10 10:00', address = 27, subtotal = 25.00 where invoiceCode = 'INV003';
	insert into ParkingPass (productCode, quantity, invoiceCode) values ('3289', 2, 'INV003');
    update ParkingPass set identifier = 'P', subtotal = 55.00 where invoiceCode = 'INV003';
    insert into EquipmentRental (productCode, quantity, invoiceCode) values ('yp23', 4, 'INV003');
	update EquipmentRental set identifier = 'R', rentalName = 'Cross Country Skis-Large', subtotal = 19.50 where invoiceCode = 'INV003';
insert into Invoice (invoiceCode, member, personalTrainer, timeDate) values ('INV004','M003','6doc','2016-10-16');
    insert into ParkingPass (productCode, quantity, invoiceCode) values ('90fb', 3, 'INV004');
    update ParkingPass set identifier = 'P', subtotal = 20.00 where invoiceCode = 'INV004';
    insert into EquipmentRental (productCode, quantity, invoiceCode) values ('32f4', 3, 'INV004');
	update EquipmentRental set identifier = 'R', rentalName = 'Tennis Racket', subtotal = 5.50 where invoiceCode = 'INV004';