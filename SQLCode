
drop table if exists Stock;
drop table if exists DepositAccount;
drop table if exists PrivateInvestment;
drop table if exists Asset;
drop table if exists Portfolio;
drop table if exists Broker;
drop table if exists Person;
drop table if exists State;
create table State (stateName varchar (255), abbreviation varchar (255), primary key (abbreviation));
create table Person (firstName varchar(255), lastName varchar (255), street varchar(255), city varchar (255), state varchar(255), foreign key (state) references State(abbreviation), country varchar (255), zip varchar (255), personCode varchar (255),  emailList varchar (255), primary key (personCode)) ;
create table Broker ( Identifier varchar (1),personCode varchar (255),foreign key(personCode) references Person (personCode), secID varchar (255), PRIMARY KEY (secID));
create table Portfolio (IdCode varchar (255), ownerCode varchar (255), foreign key (ownerCode) references Person (PersonCode), brokerCode varchar (255), foreign key  (brokerCode) references Broker (secID),fees double, comissions double, riskMeasure double, amountReturned double, total double, assetCount int, beneficiary varchar (255), foreign key (beneficiary) references Person (PersonCode), primary key (IdCode)); 
create table DepositAccount (count int not null auto_increment, IdCode varchar (255), Identifier varchar (1), Label varchar (255),  APR double, riskMeasure double, Total double,Primary key (count),portfolioCode varchar (255), foreign key (portfolioCode) references Portfolio (IdCode));
create table PrivateInvestment (count int not null auto_increment, IdCode varchar (255), Identifier varchar (1), Dividend double, RateofReturn double, OmegaMeasure double, TotalValue double, PercentOwned int,Primary key (count),portfolioCode varchar (255), foreign key (portfolioCode) references Portfolio (IdCode));
create table Stock (count int not null auto_increment ,IdCode varchar (255), Identifier varchar (1), Dividend double, RateofReturn double, BetaMeasure double, TotalValue double,Primary key (count),portfolioCode varchar (255), foreign key (portfolioCode) references Portfolio (IdCode));
Insert into Person (firstName,lastName,street,city,state,country,zip,personCode,emailList) values (' Starlin','Castro','1060 West Addison Street','Chicago',(select abbreviation from State where abbreviation= 'IL'),'USA','60613','944c','starlin_castro13@gmail.com');
Insert into Person (firstName,lastName,street,city,state,country,zip,personCode,emailList) values (' Brock','Sampson','123 N 1st Street','Omaha',(select abbreviation from State where abbreviation= 'NE'),'USA','68116','306a','bsampson@venture.com');
Insert into Person (firstName,lastName,street,city,state,country,zip,personCode,emailList) values (' Miles','OBrien','123 Friendly Street','Ottawa',(select abbreviation from State where abbreviation= 'ON'),'Canada','K1A 0G9','2342','null');
Insert into Person (firstName,lastName,street,city,state,country,zip,personCode,emailList) values (' Dale','Sveum','1060 West Addison Street','Chicago',(select abbreviation from State where abbreviation= 'IL'),'USA','60613','ma12','null');
Insert into Person (firstName,lastName,street,city,state,country,zip,personCode,emailList) values (' William','Hartnell','1060 West Addison Street','Chicago',(select abbreviation from State where abbreviation= 'IL'),'USA','60613','321nd','dr@who.com');
Insert into Person (firstName,lastName,street,city,state,country,zip,personCode,emailList) values (' Patrick','Troughton','1060 West Addison Street','Chicago',(select abbreviation from State where abbreviation= 'IL'),'USA','60613','nf32a','ptrou32@unl.edu');
Insert into Person (firstName,lastName,street,city,state,country,zip,personCode,emailList) values ('Sylvester','McCoy','126-01 Roosevelt Ave',' Flushing',(select abbreviation from State where abbreviation= 'NY'),'USA','11368','1svndr','mccoy@whofan.com');
Insert into Person (firstName,lastName,street,city,state,country,zip,personCode,emailList) values (' David','Tennant','700 E Grand Ave',' Chicago',(select abbreviation from State where abbreviation= 'IL'),'USA',' 60611','2ndbestd','tdavid@unl.edu');
Insert into Person (firstName,lastName,street,city,state,country,zip,personCode,emailList) values (' Matt','Smith','333 W 35th St',' Chicago',(select abbreviation from State where abbreviation= 'IL'),'USA','60616','wrddoc','thedoc@cse.unl.edu');
Insert into Person (firstName,lastName,street,city,state,country,zip,personCode,emailList) values (' Kaylee','Ehrmantraut','800 West 7th Street',' Albuquerque',(select abbreviation from State where abbreviation= 'NM'),'USA',' 87105','bbchar','null');
Insert into Person (firstName,lastName,street,city,state,country,zip,personCode,emailList) values (' Peter','Davison','123 Cabo San Lucas',' Los Cabos',(select abbreviation from State where abbreviation= 'BCS'),' Mexico',' ','doc05','null');
Insert into Person (firstName,lastName,street,city,state,country,zip,personCode,emailList) values (' Gordon','Gekko','1 Wall Street','New York',(select abbreviation from State where abbreviation= 'NY'),'10005-0012','USA','aef1','null');
Insert into Person (firstName,lastName,street,city,state,country,zip,personCode,emailList) values (' Jon','Pertwee','301 Front St W',' Toronto',(select abbreviation from State where abbreviation= 'ON'),' M5V 2T6',' Canada','321na','jpet@whofan.com');
Insert into Person (firstName,lastName,street,city,state,country,zip,personCode,emailList) values ('Tom','Baker','1 Blue Jays Way',' Toronto',(select abbreviation from State where abbreviation= 'ON'),' M5V 1J1',' Canada','231','famousdoc@who.comtbaker@cse.unl.edumostfamous@whovian.comthedoctor@bbc.com');
Insert into Person (firstName,lastName,street,city,state,country,zip,personCode,emailList) values (' Paul','McGann','1 MetLife Stadium Dr',' East Rutherford',(select abbreviation from State where abbreviation= 'NJ'),'07073','USA','123lst','pmcgann@mlb.comfoo@bar.compmc@unl.edu');
Insert into Person (firstName,lastName,street,city,state,country,zip,personCode,emailList) values (' Bud','Fox','321 Bronx Street','New York',(select abbreviation from State where abbreviation= 'NY'),'10004','USA','321f','bfox@gmail.comcsheen@crazy.net');
Insert into Person (firstName,lastName,street,city,state,country,zip,personCode,emailList) values (' Richard','Hurndall','Campos El290','Mexico City',(select abbreviation from State where abbreviation= 'FD'),'',' Mexico','6doc','rhurndall@cse.unl.edurichard@unl.edu');
Insert into Person (firstName,lastName,street,city,state,country,zip,personCode,emailList) values (' C.','Baker','Avery Hall','Lincoln',(select abbreviation from State where abbreviation= 'NE'),'68503','USA','321dr','dr@baker.com');
Insert into Person (firstName,lastName,street,city,state,country,zip,personCode,emailList) values (' Chris','Eccleston','1 E 161st St',' Bronx',(select abbreviation from State where abbreviation= 'NY'),'10451','USA','nwdoc1','newguy@whovian.com');
Insert into Broker (Identifier, secID, PersonCode ) values ('J','sec991',( select PersonCode from Person where PersonCode = '321f'));
Insert into Broker (Identifier, secID, PersonCode ) values ('J','sec982',( select PersonCode from Person where PersonCode = '6doc'));
Insert into Broker (Identifier, secID, PersonCode ) values ('J','sec543',( select PersonCode from Person where PersonCode = '321dr'));
Insert into Broker (Identifier, secID, PersonCode ) values ('J','sec953',( select PersonCode from Person where PersonCode = 'nwdoc1'));
Insert into Broker (Identifier, secID, PersonCode ) values ('E','sec001',( select PersonCode from Person where PersonCode ='aef1'));
Insert into Broker (Identifier, secID, PersonCode ) values ('J','sec125',( select PersonCode from Person where PersonCode ='321na'));
Insert into Broker (Identifier, secID, PersonCode ) values ('J','sec221',( select PersonCode from Person where PersonCode ='231'));
Insert into Broker (Identifier, secID, PersonCode ) values ('J','sec31x',( select PersonCode from Person where PersonCode ='123lst'));
Insert into Portfolio (IdCode, ownerCode,brokerCode, fees, comissions, riskMeasure, amountReturned, total, assetCount, beneficiary) values ('PT001',(select PersonCode from Person where PersonCode ='ma12'),( select secID from Broker where secID = 'sec001'),'0.0','0.0','0.25775712507268306','0.0','42203.78','3.0',(select PersonCode from Person where PersonCode ='ma12'));
Insert into DepositAccount (IdCode, Identifier, Label, APR, riskMeasure, Total, portfolioCode ) values ('AGTSAV','D','Savings Account','0.25','0','26534.21',(select IdCode from Portfolio where IdCode ='PT001'));
Insert into DepositAccount (IdCode, Identifier, Label, APR, riskMeasure, Total, portfolioCode ) values ('BX001-23','D','Money Market Savings','1.05','0','2403.32',(select IdCode from Portfolio where IdCode ='PT001'));
Insert into Stock (IdCode, Identifier, Dividend, RateofReturn, BetaMeasure, TotalValue, portfolioCode) values ( 'B0oWM','S','10.0','2.25','0.82','13266.25',(select IdCode from Portfolio where IdCode ='PT001'));
Insert into Portfolio (IdCode, ownerCode,brokerCode, fees, comissions, riskMeasure, amountReturned, total, assetCount, beneficiary) values ('PF002',(select PersonCode from Person where PersonCode ='1svndr'),( select secID from Broker where secID = 'sec221'),'0.0','0.0','0.2855120106566344','0.0','5.4960692E7','4.0',(select PersonCode from Person where PersonCode ='1svndr'));
Insert into Stock (IdCode, Identifier, Dividend, RateofReturn, BetaMeasure, TotalValue, portfolioCode) values ( 'BERK-B','S','0.0','7.2','0.29','112392.0',(select IdCode from Portfolio where IdCode ='PF002'));
Insert into PrivateInvestment (IdCode, Identifier, Dividend, RateofReturn, OmegaMeasure, TotalValue,PercentOwned, portfolioCode) values ('RENT445','P','2400.0','0.25','0.15','1.2E7','100.0',(select IdCode from Portfolio where IdCode ='PF002'));
Insert into PrivateInvestment (IdCode, Identifier, Dividend, RateofReturn, OmegaMeasure, TotalValue,PercentOwned, portfolioCode) values ('FOOD12','P','35000.0','3.0','0.32','4.24375E7','35.0',(select IdCode from Portfolio where IdCode ='PF002'));
Insert into Stock (IdCode, Identifier, Dividend, RateofReturn, BetaMeasure, TotalValue, portfolioCode) values ( '321CC','S','5.45','3.1','0.68','410800.0',(select IdCode from Portfolio where IdCode ='PF002'));
Insert into Portfolio (IdCode, ownerCode,brokerCode, fees, comissions, riskMeasure, amountReturned, total, assetCount, beneficiary) values ('PF003',(select PersonCode from Person where PersonCode ='1svndr'),( select secID from Broker where secID = 'sec221'),'0.0','0.0','1.28','0.0','743.4','1.0',(select PersonCode from Person where PersonCode ='1svndr'));
Insert into Stock (IdCode, Identifier, Dividend, RateofReturn, BetaMeasure, TotalValue, portfolioCode) values ( 'N0TPIX','S','0.0','1.25','1.28','743.4',(select IdCode from Portfolio where IdCode ='PF003'));
Insert into Portfolio (IdCode, ownerCode,brokerCode, fees, comissions, riskMeasure, amountReturned, total, assetCount, beneficiary) values ('PF004',(select PersonCode from Person where PersonCode ='bbchar'),( select secID from Broker where secID = 'sec982'),'0.0','0.0','0.0','0.0','120000.0','1.0',(select PersonCode from Person where PersonCode ='bbchar'));
Insert into DepositAccount (IdCode, Identifier, Label, APR, riskMeasure, Total, portfolioCode ) values ('RIRA01','D','Roth IRA','3.4','0','120000.0',(select IdCode from Portfolio where IdCode ='PF004'));
Insert into Portfolio (IdCode, ownerCode,brokerCode, fees, comissions, riskMeasure, amountReturned, total, assetCount, beneficiary) values ('PF006',(select PersonCode from Person where PersonCode ='null'),( select secID from Broker where secID = 'sec221'),'0.0','0.0','0.0','0.0','0.0','0.0',(select PersonCode from Person where PersonCode ='null'));
Insert into Portfolio (IdCode, ownerCode,brokerCode, fees, comissions, riskMeasure, amountReturned, total, assetCount, beneficiary) values ('PF007',(select PersonCode from Person where PersonCode ='bbchar'),( select secID from Broker where secID = 'sec221'),'0.0','0.0','0.25040963572138164','0.0','2.467832875E8','4.0',(select PersonCode from Person where PersonCode ='bbchar'));
Insert into Stock (IdCode, Identifier, Dividend, RateofReturn, BetaMeasure, TotalValue, portfolioCode) values ( '321CC','S','5.45','3.1','0.68','410800.0',(select IdCode from Portfolio where IdCode ='PF007'));
Insert into Stock (IdCode, Identifier, Dividend, RateofReturn, BetaMeasure, TotalValue, portfolioCode) values ( 'wOOWoo1S','S','2.75','4.6','0.79','4887.5',(select IdCode from Portfolio where IdCode ='PF007'));
Insert into PrivateInvestment (IdCode, Identifier, Dividend, RateofReturn, OmegaMeasure, TotalValue,PercentOwned, portfolioCode) values ('CMPROP0121','P','14240.0','5.35','0.25','2.45716E8','23.5',(select IdCode from Portfolio where IdCode ='PF007'));
Insert into PrivateInvestment (IdCode, Identifier, Dividend, RateofReturn, OmegaMeasure, TotalValue,PercentOwned, portfolioCode) values ('REALStr12','P','1232.0','1.23','0.13','651600.0','12.0',(select IdCode from Portfolio where IdCode ='PF007'));
Insert into Portfolio (IdCode, ownerCode,brokerCode, fees, comissions, riskMeasure, amountReturned, total, assetCount, beneficiary) values ('PF200',(select PersonCode from Person where PersonCode ='bbchar'),( select secID from Broker where secID = 'sec953'),'0.0','0.0','0.0','0.0','0.0','0.0',(select PersonCode from Person where PersonCode ='bbchar'));
INSERT into State(abbreviation, stateName) values ('AL', 'Alabama'),
('ON','Ontario'),
('BCS','Mexico'),
('FD','Mexico'),
('AK', 'Alaska'),
('AZ', 'Arizona'),
('AR', 'Arkansas'),
('CA', 'California'),
('CO', 'Colorado'),
('CT', 'Connecticut'),
('DE', 'Delaware'),
('DC', 'District of Columbia'),
('FL', 'Florida'),
('GA', 'Georgia'),
('HI', 'Hawaii'),
('ID', 'Idaho'),
('IL', 'Illinois'),
('IN', 'Indiana'),
('IA', 'Iowa'),
('KS', 'Kansas'),
('KY', 'Kentucky'),
('LA', 'Louisiana'),
('ME', 'Maine'),
('MD', 'Maryland'),
('MA', 'Massachusetts'),
('MI', 'Michigan'),
('MN', 'Minnesota'),
('MS', 'Mississippi'),
('MO', 'Missouri'),
('MT', 'Montana'),
('NE', 'Nebraska'),
('NV', 'Nevada'),
('NH', 'New Hampshire'),
('NJ', 'New Jersey'),
('NM', 'New Mexico'),
('NY', 'New York'),
('NC', 'North Carolina'),
('ND', 'North Dakota'),
('OH', 'Ohio'),
('OK', 'Oklahoma'),
('OR', 'Oregon'),
('PA', 'Pennsylvania'),
('PR', 'Puerto Rico'),
('RI', 'Rhode Island'),
('SC', 'South Carolina'),
('SD', 'South Dakota'),
('TN', 'Tennessee'),
('TX', 'Texas'),
('UT', 'Utah'),
('VT', 'Vermont'),
('VA', 'Virginia'),
('WA', 'Washington'),
('WV', 'West Virginia'),
('WI', 'Wisconsin'),
('WY', 'Wyoming');
-- Query 1 -----------------------------------------------------------------------------
Select * from Person left join Broker on Person.personCode= Broker.personCode;
-- Query 2 -----------------------------------------------------------------------------
Select  emailList from Person where lastName = 'McGann ';
-- Query 3 -----------------------------------------------------------------------------
update Person  set emailList= 'timelord99@tardis.time' where personCode= '2342' ;
-- Query 4 -----------------------------------------------------------------------------
SET SQL_SAFE_UPDATES = 0;

update Person set emailList= 'timeLord99@tardis.time,JohnSmith@blank.blank' where emailList= 'timelord99@tardis.time';
SET SQL_SAFE_UPDATES = 1;
-- Query 5 and 6 -----------------------------------------------------------------------
Insert into Person (firstName,lastName,street,city,state,country,zip,personCode,emailList) values (' Timey','Wimey','1060 West Addison Street','Chicago','IL','USA','60612','TaRdIs','null');
delete from Person where personCode= 'TaRdIs';
-- Query 7 ****************************************************************
select IdCode, TotalValue from Stock where portfolioCode= 'PF004';
select IdCode, TotalValue from PrivateInvestment where portfolioCode= 'PF004';
select IdCode, Total from DepositAccount where portfolioCode= 'PF004';
-- Query 8 ****************************************************************
select Stock.IdCode, TotalValue from Stock join Portfolio on  Stock.portfolioCode = Portfolio.IdCode where Portfolio.ownerCode ='1svndr';
select PrivateInvestment.IdCode, TotalValue from PrivateInvestment join Portfolio on  PrivateInvestment.portfolioCode = Portfolio.IdCode where Portfolio.ownerCode ='1svndr';
select DepositAccount.IdCode, DepositAccount.Total from DepositAccount join Portfolio on  DepositAccount.portfolioCode = Portfolio.IdCode where Portfolio.ownerCode ='1svndr';
-- Query 9 & 10 & 11---------------------------------------------------------------------
Insert into Portfolio (IdCode, ownerCode,brokerCode, fees, comissions, riskMeasure, amountReturned, total, assetCount, beneficiary) values ('PF201',(select PersonCode from Person where PersonCode ='bbchar'),( select secID from Broker where secID = 'sec953'),'1000.0','50.0','340.0','2250.0','70.0','0.50',(select PersonCode from Person where PersonCode ='bbchar'));

Insert into DepositAccount (IdCode, Identifier, Label, APR, riskMeasure, Total, portfolioCode ) values ('BXJ20','D','Money Market Savings','1.05','0','2403.32',(select IdCode from Portfolio where IdCode ='null'));
SET SQL_SAFE_UPDATES = 0;

update DepositAccount set portfolioCode= 'PF201' where IdCode= 'BXJ20';

delete from DepositAccount where IdCode= 'BXJ20';
delete from Portfolio where IdCode= 'PF201';
SET SQL_SAFE_UPDATES = 1;
-- Query 12------------------------------------------------------------------------------
select ownerCode, count(*) from Portfolio group by ownerCode;
-- Query 13 -----------------------------------------------------------------------------
select brokerCode, count(*) from Portfolio group by brokerCode;
-- Query 14 -----------------------------------------------------------------------------
select portfolioCode, sum(TotalValue) from Stock group by portfolioCode;
-- Query 15------------------------------------------------------------------------------
select IdCode, sum(PercentOwned) from PrivateInvestment group by IdCode having sum(PercentOwned)>100 ;

select Identifier.Stock as Identifier, Identifier.PrivateInvestment as PIdentifier, Identifier from Stock,PrivateInvestment, DepositAccount where IdCode= '321CC';
