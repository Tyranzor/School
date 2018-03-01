
drop table if exists DayMembership;
drop table if exists ParkingPass;
drop table if exists YearMembership;
drop table if exists EquipmentRental;
drop table if exists Invoice;
drop table if exists Member;
drop table if exists Person;
drop table if exists Address;
create table Address (street varchar(255), city varchar(255),state varchar(255), zip varchar(255), country varchar(255) ,Primary key (street));
create table Person (personCode varchar(255), Primary key (personCode), firstName varchar (255), lastName varchar(255), address varchar(255), foreign key (address) references Address (street), emails varchar(255));
create table Member ( memberCode varchar(255), Primary key (memberCode), person varchar(255), foreign key (person) references Person (personCode), address varchar(255), foreign key (address) references Address(street), name varchar(255), type varchar(1));
create table Invoice (invoiceCode varchar (255), Primary key (invoiceCode), member varchar (255), foreign key (member) references  Member (memberCode), personalTrainer varchar (255), foreign key (personalTrainer) references Person (personCode), discount double, tax double, subtotal double, total double,
 timeDate varchar(255));
create table ParkingPass ( invoiceCode varchar(255), foreign key (invoiceCode) references Invoice(invoiceCode),quanitity int default 1,productCode varchar (255), Primary key (productCode), address varchar(255), foreign key (address) references Address (street), total double, tax double, discount double, identifier varchar(255), subtotal double, timeDate varchar(255));
create table EquipmentRental (invoiceCode varchar(255), foreign key (invoiceCode) references Invoice(invoiceCode),quanitity int default 1,productCode varchar (255), Primary key (productCode), address varchar(255), foreign key (address) references Address (street), total double, tax double, discount double, identifier varchar(255), subtotal double, timeDate varchar(255), name varchar(255));
create table DayMembership (invoiceCode varchar(255), foreign key (invoiceCode) references Invoice(invoiceCode), quanitity int default 1 ,productCode varchar (255), Primary key (productCode), address varchar(255), total double, tax double, discount double, identifier varchar(255), subtotal double, timeDate varchar(255));
create table YearMembership (invoiceCode varchar(255), foreign key (invoiceCode) references Invoice(invoiceCode),quanitity int default 1 ,productCode varchar (255), Primary key (productCode), address varchar(255), total double, tax double, discount double, identifier varchar(255), subtotal double, timeDateStart varchar(255),
 timeDateEnd  varchar(255));
 
