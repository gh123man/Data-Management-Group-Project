CREATE TABLE IF NOT EXISTS Address (
    ID      INT PRIMARY KEY AUTO_INCREMENT,
    Street1 VARCHAR(128) NOT NULL,
    Street2 VARCHAR(128) NOT NULL,
    City    VARCHAR(128) NOT NULL,
    State   CHAR(2)      NOT NULL,
    ZipCode CHAR(5)      NOT NULL
);

CREATE TABLE IF NOT EXISTS Person (
    ID              INT PRIMARY KEY AUTO_INCREMENT,
    FirstName       VARCHAR(64) NOT NULL,
    MiddleInitial   VARCHAR(1)  NOT NULL,
    LastName        VARCHAR(64) NOT NULL,
    AddressID       INT         NOT NULL,
    FOREIGN KEY(AddressID) REFERENCES Address(ID)
);

CREATE TABLE IF NOT EXISTS Membership (
    ID              INT PRIMARY KEY AUTO_INCREMENT,
    PersonID        INT     NOT NULL UNIQUE,
    ExpirationDate  DATE    NOT NULL,
    FOREIGN KEY(PersonID) REFERENCES Person(ID)
);

CREATE TABLE IF NOT EXISTS JobType (
    ID      INT PRIMARY KEY AUTO_INCREMENT,
    Name    VARCHAR(64)     NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS Employee (
    ID          INT PRIMARY KEY AUTO_INCREMENT,
    PersonID    INT NOT NULL,
    Salary      INT NOT NULL,
    Job         INT NOT NULL,
    FOREIGN KEY(Job) References JobType(ID),
    FOREIGN KEY(PersonID) References Person(ID)
);

CREATE TABLE IF NOT EXISTS Location (
    ID              INT PRIMARY KEY AUTO_INCREMENT,
    Name            VARCHAR(64) NOT NULL,
    ExhibitCapacity INT         NOT NULL
);

CREATE TABLE IF NOT EXISTS Exhibit (
    ID              INT PRIMARY KEY AUTO_INCREMENT,
    Location        INT NOT NULL,
    AnimalCapacity  INT NOT NULL,
    FOREIGN KEY(Location) REFERENCES Location(ID)
);

CREATE TABLE IF NOT EXISTS Employee_Exhibit ( /* works in? */
    EmployeeID  INT NOT NULL,
    ExhibitID   INT NOT NULL,
    PRIMARY KEY(EmployeeID, ExhibitID),
    FOREIGN KEY(EmployeeID) REFERENCES Employee(ID),
    FOREIGN KEY(ExhibitID) REFERENCES Exhibit(ID)
);

CREATE TABLE IF NOT EXISTS Food (
    ID          INT PRIMARY KEY AUTO_INCREMENT,
    Name        VARCHAR(64) NOT NULL,
    UnitCost    DECIMAL(4,2)
);

CREATE TABLE IF NOT EXISTS AnimalClass (
    ID      INT PRIMARY KEY AUTO_INCREMENT,
    Name    VARCHAR(64) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS Animal (
    ID              INT PRIMARY KEY AUTO_INCREMENT,
    Name            VARCHAR(64) NOT NULL,
    AnimalClassID   INT         NOT NULL,
    ExhibitID       INT         NOT NULL,
    Gender          CHAR(1)     NOT NULL,
    Age             SMALLINT    NOT NULL,
    FOREIGN KEY(AnimalClassID) REFERENCES AnimalClass(ID),
    FOREIGN KEY(ExhibitID) REFERENCES Exhibit(ID)
);

CREATE TABLE IF NOT EXISTS Eats (
    AnimalID        INT NOT NULL,
    FoodID          INT NOT NULL,
    DailyQuantity   INT NOT NULL,
    PRIMARY KEY(AnimalID, FoodID),
    FOREIGN KEY(AnimalID) REFERENCES Animal(ID),
    FOREIGN KEY(FoodID) REFERENCES Food(ID)
);
