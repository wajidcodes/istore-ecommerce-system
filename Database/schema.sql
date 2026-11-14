-- IStore E-Commerce Management System — database schema
-- Database: loginaccounts (MySQL 8.0+)
-- Run it in one step from the repo root:
--   mysql -u root -p < Database/schema.sql
-- The same statements are also listed step by step in INSTALLATION.md
-- (Database Setup, Steps 2-3). Sample data (admin / customer / supplier
-- accounts) is optional and stays in INSTALLATION.md, Step 4.

-- Create the database
CREATE DATABASE loginaccounts;

-- Use the database
USE loginaccounts;

-- Accounts table
CREATE TABLE accounts (
    Email VARCHAR(100) PRIMARY KEY,
    Password VARCHAR(255) NOT NULL,
    AccountType ENUM('Customer', 'Supplier', 'Admin') NOT NULL,
    SupplierID VARCHAR(50) NULL,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Customer accounts table
CREATE TABLE customeraccounts (
    CustomerID INT AUTO_INCREMENT PRIMARY KEY,
    Email VARCHAR(100) UNIQUE NOT NULL,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    PhoneNumber VARCHAR(20),
    DateOfBirth DATE,
    Address TEXT,
    City VARCHAR(50),
    Country VARCHAR(50),
    FOREIGN KEY (Email) REFERENCES accounts(Email) ON DELETE CASCADE
);

-- Supplier accounts table
CREATE TABLE supplieraccounts (
    SupplierID VARCHAR(50) PRIMARY KEY,
    Email VARCHAR(100) UNIQUE NOT NULL,
    BusinessName VARCHAR(100) NOT NULL,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    PhoneNumber VARCHAR(20),
    BusinessAddress TEXT,
    City VARCHAR(50),
    Country VARCHAR(50),
    BusinessType VARCHAR(50),
    TaxID VARCHAR(50),
    BankAccountNumber VARCHAR(50),
    ApprovalStatus ENUM('Pending', 'Approved', 'Rejected') DEFAULT 'Approved',
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (Email) REFERENCES accounts(Email) ON DELETE CASCADE
);

-- Supplier products table
CREATE TABLE supplierproducts (
    ProductID INT AUTO_INCREMENT PRIMARY KEY,
    SupplierID VARCHAR(50) NOT NULL,
    ProductTitle VARCHAR(200) NOT NULL,
    Description TEXT,
    Price DECIMAL(10, 2) NOT NULL,
    ComparePrice DECIMAL(10, 2),
    Size VARCHAR(50),
    Weight VARCHAR(50),
    Color VARCHAR(50),
    Quantity INT DEFAULT 0,
    DeliveryCost DECIMAL(10, 2) DEFAULT 0.00,
    Category VARCHAR(50),
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (SupplierID) REFERENCES supplieraccounts(SupplierID) ON DELETE CASCADE
);

-- Supplier product images table
CREATE TABLE supplierproductimages (
    ImageID INT AUTO_INCREMENT PRIMARY KEY,
    ProductID INT NOT NULL,
    Images LONGBLOB,
    ImageOrder INT DEFAULT 1,
    FOREIGN KEY (ProductID) REFERENCES supplierproducts(ProductID) ON DELETE CASCADE
);

-- Customer cart table
CREATE TABLE customercart (
    CartID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerEmail VARCHAR(100) NOT NULL,
    ProductID INT NOT NULL,
    Quantity INT DEFAULT 1,
    AddedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (CustomerEmail) REFERENCES accounts(Email) ON DELETE CASCADE,
    FOREIGN KEY (ProductID) REFERENCES supplierproducts(ProductID) ON DELETE CASCADE
);

-- Customer orders table
CREATE TABLE customerorders (
    OrderID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerEmail VARCHAR(100) NOT NULL,
    ProductID INT NOT NULL,
    SupplierID VARCHAR(50) NOT NULL,
    Quantity INT NOT NULL,
    TotalPrice DECIMAL(10, 2) NOT NULL,
    ShippingAddress TEXT,
    OrderStatus ENUM('Pending', 'Processing', 'Shipped', 'Delivered', 'Cancelled') DEFAULT 'Pending',
    OrderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (CustomerEmail) REFERENCES accounts(Email) ON DELETE CASCADE,
    FOREIGN KEY (ProductID) REFERENCES supplierproducts(ProductID) ON DELETE CASCADE,
    FOREIGN KEY (SupplierID) REFERENCES supplieraccounts(SupplierID) ON DELETE CASCADE
);

-- Events table (discount/promotional events)
CREATE TABLE events (
    EventID INT AUTO_INCREMENT PRIMARY KEY,
    SupplierID VARCHAR(50) NOT NULL,
    EventTitle VARCHAR(200) NOT NULL,
    Description TEXT,
    DiscountPercentage DECIMAL(5, 2),
    StartDate DATE,
    EndDate DATE,
    IsActive BOOLEAN DEFAULT TRUE,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (SupplierID) REFERENCES supplieraccounts(SupplierID) ON DELETE CASCADE
);

-- Supplier applications table
CREATE TABLE supplierapplications (
    ApplicationID INT AUTO_INCREMENT PRIMARY KEY,
    Email VARCHAR(100) NOT NULL,
    BusinessName VARCHAR(100) NOT NULL,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    PhoneNumber VARCHAR(20),
    BusinessAddress TEXT,
    ApplicationStatus ENUM('Pending', 'Approved', 'Rejected') DEFAULT 'Pending',
    SubmittedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
