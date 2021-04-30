CREATE SCHEMA if not exists AssetDB;

USE AssetDB;

CREATE TABLE if not exists AssetType (
AssetTypeID int AUTO_INCREMENT NOT NULL PRIMARY KEY,
AssetTypeDesc varchar(255)
);

CREATE TABLE if not exists Employee (
EmployeeID int AUTO_INCREMENT NOT NULL PRIMARY KEY,
EmpFirstName varchar(255),
EmpLastName varchar(255)
)auto_increment = 100;
CREATE TABLE if not exists Room (
RoomNum int AUTO_INCREMENT NOT NULL PRIMARY KEY,
RoomName varchar(255)
)auto_increment = 1000;


CREATE TABLE if not exists Assets (
AssetID int AUTO_INCREMENT NOT NULL PRIMARY KEY,
RoomNum int,
EmployeeID int,
AssetTypeID int,
DatePurchased date,
DateAssigned date,
Brand varchar(255),
Model varchar (255),
Series varchar(255),
ServiceTag varchar(6),
SerialNum varchar(50),
Cost decimal(19, 2),
CONSTRAINT FK_Chk_RoomNum FOREIGN KEY (RoomNum) REFERENCES Room(RoomNum) ON UPDATE CASCADE,
CONSTRAINT FK_Chk_EmployeeID FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID) ON UPDATE CASCADE,
CONSTRAINT FK_Chk_AssetTypeID FOREIGN KEY (AssetTypeID) REFERENCES AssetType(AssetTypeID) ON UPDATE CASCADE
)auto_increment = 10000000;


