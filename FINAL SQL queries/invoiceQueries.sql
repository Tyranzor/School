-- Query to retrieve the major fields for every person
select * from Person;

-- Query to add an email to a specific person
update Person set emails = 'hello@gmail.com' where PersonCode = '306a';

-- Query to change the address of a fitness in a Year-long-member ship record
update YearMembership set Address = (Select id from Address where id = 1) where ID = 1;

-- Query (or series of queries) to remove a given Year-long-member ship record
delete from YearMembership where ID = 2;

-- Query to get all the products in a particular invoice
Select * from ParkingPass where invoiceCode = 'INV002';
Select * from EquipmentRental where invoiceCode ='INV002';
select * from DayMembership where invoiceCode = 'INV002';
select * from YearMembership where invoiceCode = 'INV002';

-- Query to get all the invoices of a particular member
select * from Invoice where member = 'M003';

-- Query that “adds” a particular product to a particular invoice
Insert into ParkingPass (invoiceCode, quantity, productCode,
total,tax,discount,identifier,subtotal) values (
(select invoiceCode from Invoice where invoiceCode = 'INV004'), 1, '3289',
0.00,0.00,0.00,'P',0.00);

-- Query to find the total of all per-unit costs of all Year-long-member ship
select (subtotal/quantity) from YearMembership;

-- Query to find the total number of Year-long-member ship sold on a particular date
select * from YearMembership where timeDateStart='2016-10-07';

-- Query to find the total number of invoices for every personal trainer
select * from Invoice order by personalTrainer;

-- Query to find the total number of invoices for a particular Year-long-member ship
select count(Invoice.invoiceCode) from Invoice join YearMembership on Invoice.invoiceCode = 
YearMembership.invoiceCode where YearMembership.invoiceCode = 'b29e';

/* Query to find the total revenue generated (excluding fees and taxes) on a particular date from
all Year-long-member ship */
select sum(total) from YearMembership where timeDateStart = '2016-10-07';

/* Query to find the total quantities sold (excluding fees and taxes) of each category of services
(parking-passes and equipment rental) in all the existing invoices */
select(select sum(quantity) from ParkingPass) + (select sum(quantity) from EquipmentRental);

/* Query to detect invalid data in invoices as follows. In a single invoice, a particular ticket should
only appear once (since any number of units can be consolidated to a single record). Write a
query to find any invoice that includes multiple instances of the same membership */
select * from YearMembership where invoiceCode = 'INV003' having count(*)>1;
select * from DayMembership where invoiceCode = 'INV003' having count(*)>1;

/* Query to detect a possible conflict of interest as follows. No distinction is made in this
system between a person who is the primary contact of a member and a person who is also a
personal trainer. Write a query to find any and all invoices where the personal trainer is the
same as the primary contact of the invoice’s member */
select * from Invoice where member = PersonalTrainer;