<div align="center">
  <img src="EcommerceStore1/EcommerceStore/src/images/store2.png" alt="IStore Logo" width="120" />
  <h1>📦 Installation Guide</h1>
  <p><b>Everything you need to get IStore up and running.</b></p>
</div>

---


This guide will walk you through the complete installation and setup process for the IStore E-Commerce Management System.

## 📋 Table of Contents

1. [System Requirements](#system-requirements)
2. [Software Installation](#software-installation)
3. [Database Setup](#database-setup)
4. [Project Configuration](#project-configuration)
5. [Running the Application](#running-the-application)
6. [Troubleshooting](#troubleshooting)

## System Requirements

Before installing, ensure your system meets the following requirements:

### Hardware Requirements
- **Processor**: Intel Core i3 or equivalent (minimum)
- **RAM**: 4 GB (minimum), 8 GB (recommended)
- **Storage**: 500 MB free disk space
- **Display**: 1200x650 minimum resolution

### Software Requirements
- **Operating System**: Windows 10/11, macOS 10.14+, or Linux (Ubuntu 20.04+)
- **Java Development Kit (JDK)**: Version 23 or higher
- **MySQL Server**: Version 8.0 or higher
- **MySQL Connector/J**: Version 9.3.0
- **IDE** (recommended): IntelliJ IDEA, Eclipse, or NetBeans

## Software Installation

### Step 1: Install Java Development Kit (JDK)

#### Windows:
1. Download JDK 23 from [Oracle's official website](https://www.oracle.com/java/technologies/downloads/#jdk23)
2. Run the installer and follow the installation wizard
3. Set JAVA_HOME environment variable:
   ```cmd
   setx JAVA_HOME "C:\Program Files\Java\jdk-23"
   setx PATH "%PATH%;%JAVA_HOME%\bin"
   ```
4. Verify installation:
   ```cmd
   java -version
   javac -version
   ```

#### macOS:
1. Using Homebrew:
   ```bash
   brew install openjdk@23
   ```
2. Add to PATH:
   ```bash
   echo 'export PATH="/opt/homebrew/opt/openjdk@23/bin:$PATH"' >> ~/.zshrc
   source ~/.zshrc
   ```
3. Verify installation:
   ```bash
   java -version
   ```

#### Linux (Ubuntu/Debian):
```bash
sudo apt update
sudo apt install openjdk-23-jdk
java -version
```

### Step 2: Install MySQL Server

#### Windows:
1. Download MySQL Installer from [MySQL official website](https://dev.mysql.com/downloads/installer/)
2. Run the installer and choose "Developer Default" setup type
3. Set root password (remember this for later)
4. Complete the installation wizard

#### macOS:
```bash
brew install mysql
brew services start mysql
mysql_secure_installation
```

#### Linux:
```bash
sudo apt update
sudo apt install mysql-server
sudo systemctl start mysql
sudo mysql_secure_installation
```

### Step 3: Download MySQL Connector/J

1. Download MySQL Connector/J 9.3.0 from [MySQL official website](https://dev.mysql.com/downloads/connector/j/)
2. Extract the downloaded archive
3. Locate the `mysql-connector-j-9.3.0.jar` file
4. Save it to a known location (e.g., `C:\mysql-connector\` on Windows)

### Step 4: Clone the Repository

```bash
git clone https://github.com/wajidcodes/istore-ecommerce-system.git
cd istore-ecommerce-system/EcommerceStore1/EcommerceStore
```

Or download the ZIP file and extract it to your preferred location.

## Database Setup

### Step 1: Access MySQL

Open MySQL command line or MySQL Workbench and login with root credentials:

```bash
mysql -u root -p
```

### Step 2: Create Database

Execute the following SQL commands:

```sql
-- Create the database
CREATE DATABASE loginaccounts;

-- Use the database
USE loginaccounts;
```

### Step 3: Create Tables

#### Accounts Table
```sql
CREATE TABLE accounts (
    Email VARCHAR(100) PRIMARY KEY,
    Password VARCHAR(255) NOT NULL,
    AccountType ENUM('Customer', 'Supplier', 'Admin') NOT NULL,
    SupplierID VARCHAR(50) NULL,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

#### Customer Accounts Table
```sql
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
```

#### Supplier Accounts Table
```sql
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
```

#### Supplier Products Table
```sql
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
```

#### Supplier Product Images Table
```sql
CREATE TABLE supplierproductimages (
    ImageID INT AUTO_INCREMENT PRIMARY KEY,
    ProductID INT NOT NULL,
    Images LONGBLOB,
    ImageOrder INT DEFAULT 1,
    FOREIGN KEY (ProductID) REFERENCES supplierproducts(ProductID) ON DELETE CASCADE
);
```

#### Customer Cart Table
```sql
CREATE TABLE customercart (
    CartID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerEmail VARCHAR(100) NOT NULL,
    ProductID INT NOT NULL,
    Quantity INT DEFAULT 1,
    AddedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (CustomerEmail) REFERENCES accounts(Email) ON DELETE CASCADE,
    FOREIGN KEY (ProductID) REFERENCES supplierproducts(ProductID) ON DELETE CASCADE
);
```

#### Customer Orders Table
```sql
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
```

#### Events Table (Discount/Promotional Events)
```sql
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
```

#### Supplier Applications Table
```sql
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
```

### Step 4: Insert Sample Data (Optional)

Create an admin account for testing:

```sql
-- Insert admin account
INSERT INTO accounts (Email, Password, AccountType) 
VALUES ('admin@istore.com', 'Admin@123', 'Admin');

-- Insert sample customer
INSERT INTO accounts (Email, Password, AccountType) 
VALUES ('customer@example.com', 'Customer@123', 'Customer');

INSERT INTO customeraccounts (Email, FirstName, LastName, PhoneNumber, City, Country)
VALUES ('customer@example.com', 'John', 'Doe', '+1234567890', 'New York', 'USA');

-- Insert sample supplier
INSERT INTO accounts (Email, Password, AccountType, SupplierID) 
VALUES ('supplier@example.com', 'Supplier@123', 'Supplier', 'SUP001');

INSERT INTO supplieraccounts (SupplierID, Email, BusinessName, FirstName, LastName, PhoneNumber, City, Country, BusinessType)
VALUES ('SUP001', 'supplier@example.com', 'Tech Store Inc.', 'Jane', 'Smith', '+1234567891', 'San Francisco', 'USA', 'Electronics');
```

## Project Configuration

### Database Configuration

> [!IMPORTANT]
> You need to update the database credentials in the source code files.

The following files contain hardcoded database credentials that need to be updated with your MySQL settings:

1. **LoginForm.java** - Line 27-29
2. **SignupForm.java** - Check for database connection strings
3. **ExploreProduct.java** - Line 21-23
4. All files in `cDashboard/` and `sDashboard/` packages that use database connections

**Default credentials in code:**
```java
private static final String url = "jdbc:mysql://127.0.0.1:3306/loginaccounts";
private static final String user = "root";
private static final String password = "YOUR_MYSQL_PASSWORD_HERE";  // CHANGE THIS
```

**Update to your MySQL credentials:**
```java
private static final String url = "jdbc:mysql://127.0.0.1:3306/loginaccounts";
private static final String user = "root";  // or your MySQL username
private static final String password = "your_actual_password";  // your MySQL password
```

Use Find & Replace (Ctrl+H in most IDEs) to replace `"YOUR_MYSQL_PASSWORD_HERE"` with your actual MySQL root password across all files.

### IDE Setup (IntelliJ IDEA)

1. **Open the project:**
   - File → Open → Select `EcommerceStore` folder

2. **Configure JDK:**
   - File → Project Structure → Project SDK → Select JDK 23

3. **Add MySQL Connector Library:**
   - File → Project Structure → Libraries
   - Click `+` → Java
   - Browse to `mysql-connector-j-9.3.0.jar`
   - Click OK

4. **Set up Run Configuration:**
   - Run → Edit Configurations
   - Click `+` → Application
   - Name: `IStore Login`
   - Main class: `Project1.src.com.accounts.LoginForm`
   - Click OK

### Command Line Setup

If not using an IDE:

1. **Add MySQL Connector to CLASSPATH:**

   Windows:
   ```cmd
   set CLASSPATH=%CLASSPATH%;C:\path\to\mysql-connector-j-9.3.0.jar
   ```

   macOS/Linux:
   ```bash
   export CLASSPATH=$CLASSPATH:/path/to/mysql-connector-j-9.3.0.jar
   ```

## Running the Application

### Using IntelliJ IDEA

1. Open `LoginForm.java`
2. Click the green play button next to the `main` method
3. Or use the Run Configuration created earlier: Run → Run 'IStore Login'

### Using Command Line

1. **Navigate to project directory:**
   ```bash
   cd EcommerceStore1/EcommerceStore
   ```

2. **Compile the project:**
   ```bash
   javac -d out -sourcepath src -cp "path/to/mysql-connector-j-9.3.0.jar" src/Project1/src/com/accounts/LoginForm.java
   ```

3. **Run the application:**

   Windows:
   ```cmd
   java -cp "out;C:\path\to\mysql-connector-j-9.3.0.jar" Project1.src.com.accounts.LoginForm
   ```

   macOS/Linux:
   ```bash
   java -cp "out:/path/to/mysql-connector-j-9.3.0.jar" Project1.src.com.accounts.LoginForm
   ```

### Default Login Credentials

After inserting sample data:

- **Admin:**
  - Email: `admin@istore.com`
  - Password: `Admin@123`

- **Customer:**
  - Email: `customer@example.com`
  - Password: `Customer@123`

- **Supplier:**
  - Email: `supplier@example.com`
  - Password: `Supplier@123`

## Troubleshooting

### Common Issues

#### 1. ClassNotFoundException: com.mysql.cj.jdbc.Driver

**Problem**: MySQL Connector not found in classpath.

**Solution**:
- Verify MySQL Connector JAR is added to project libraries
- Check CLASSPATH environment variable includes the JAR file
- In IntelliJ: File → Project Structure → Libraries → Ensure connector is listed

#### 2. SQLException: Access denied for user 'root'@'localhost'

**Problem**: Incorrect database credentials.

**Solution**:
- Verify your MySQL username and password
- Update credentials in all source files (see [Database Configuration](#database-configuration))
- Test MySQL connection: `mysql -u root -p`

#### 3. SQLException: Unknown database 'loginaccounts'

**Problem**: Database not created.

**Solution**:
- Follow [Database Setup](#database-setup) section
- Verify database exists: `SHOW DATABASES;` in MySQL

#### 4. Images not loading

**Problem**: Image resources not found.

**Solution**:
- Ensure `src/images/` directory exists with all required images
- Check that images are included in the compiled output directory
- Verify ClassLoader.getSystemResource() paths are correct

#### 5. UnsupportedClassVersionError

**Problem**: Java version mismatch.

**Solution**:
- Ensure JDK 23 is installed and configured
- Verify IDE is using JDK 23: File → Project Structure → Project SDK
- Check command line: `java -version` should show version 23

#### 6. Window size/resolution issues

**Problem**: Application window doesn't fit screen.

**Solution**:
- Minimum resolution: 1200x650
- Application windows are set to fixed size (1200x650 or 1000x650)
- Consider adjusting window dimensions in the respective Dashboard classes

### MySQL Connection Pooling (Optional Performance Improvement)

For better performance with multiple users, consider implementing connection pooling. However, for a university project with single-user testing, the current implementation is sufficient.

### Getting Help

If you encounter issues not covered here:

1. Check the MySQL error logs
2. Enable Java debugging output
3. Review the project's GitHub Issues page
4. Contact the development team

## Next Steps

After successful installation:

1. Test all three user roles (Customer, Supplier, Admin)
2. Add sample products as a Supplier
3. Browse and purchase as a Customer
4. Review applications and users as an Admin
5. Explore all features listed in the README

---

**Installation complete! 🎉** 

You're now ready to use the IStore E-Commerce Management System.
