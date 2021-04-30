INSERT INTO Employee (employee.EmpFirstName, employee.EmpLastName)
VALUE("John","Deere");
INSERT INTO Employee (employee.EmpFirstName, employee.EmpLastName)
VALUE("Barry","Allen");
INSERT INTO Employee (employee.EmpFirstName, employee.EmpLastName)
VALUE("Tom","Brady");
INSERT INTO Employee (employee.EmpFirstName, employee.EmpLastName)
VALUE("Thomas","Wayne");
INSERT INTO Employee (employee.EmpFirstName, employee.EmpLastName)
VALUE("Hal","Jordan");


INSERT INTO assettype (assettype.AssetTypeDesc)
VALUE("Laptop");
INSERT INTO assettype (assettype.AssetTypeDesc)
VALUE("Desktop");
INSERT INTO assettype (assettype.AssetTypeDesc)
VALUE("Docking Station");
INSERT INTO assettype (assettype.AssetTypeDesc)
VALUE("Monitor");

INSERT INTO room (room.RoomName)
VALUE("Conference Room");
INSERT INTO room (room.RoomName)
VALUE("Training Room");
INSERT INTO room (room.RoomName)
VALUE("Service Room");
INSERT INTO room (room.RoomName)
VALUE("Reception");

INSERT INTO assets (assets.RoomNum, assets.EmployeeID, assets.AssetTypeID, assets.DatePurchased, assets.DateAssigned, assets.Brand, assets.Model, assets.Series, assets.ServiceTag, assets.SerialNum, assets.Cost)
VALUE(1001, 101, 1, "2020-08-01", "2020-11-06", "Dell", "Latitude", "9410", "A3FH68", NULL, 3289.23);
INSERT INTO assets (assets.RoomNum, assets.EmployeeID, assets.AssetTypeID, assets.DatePurchased, assets.DateAssigned, assets.Brand, assets.Model, assets.Series, assets.ServiceTag, assets.SerialNum, assets.Cost)
VALUE(1002, 102, 2, "2020-09-01", "2020-10-02", "Dell", "Precision", "3432", "G3JH22", NULL, 600.63);


select * from assets;