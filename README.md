# IStore - E-Commerce Management System

<div align="center">
  <img src="EcommerceStore1/EcommerceStore/src/images/store2.png" alt="IStore Logo" width="200" height="auto" />
  <br />
  
  <h1>🛒 IStore E-Commerce System</h1>
  
  <p>
    <b>A robust, comprehensive Java-based marketplace solution.</b>
  </p>
  
  <p>
    Customers • Suppliers • Administrators
  </p>

  <p>
    <a href="#-features">Features</a> • 
    <a href="#-screenshots">Screenshots</a> • 
    <a href="#-technology-stack">Tech Stack</a> • 
    <a href="#-getting-started">Getting Started</a> • 
    <a href="#-contributors">Contributors</a>
  </p>

  [![Java](https://img.shields.io/badge/Java-JDK_23-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.oracle.com/java/)
  [![Database](https://img.shields.io/badge/Database-MySQL_8.0-00758F?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
  [![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)](LICENSE)
</div>

---

## 📖 Overview

**IStore** is a feature-rich e-commerce desktop application built with the **Java Swing** framework. It bridges the gap between traditional retail and digital convenience by providing three distinct, specialized interfaces:
1.  **Customer Panel**: For browsing, shopping, and managing orders.
2.  **Supplier Panel**: For inventory management, product listing, and sales tracking.
3.  **Admin Dashboard**: For system-wide oversight and user management.

---

## ✨ Features

### 🛍️ For Customers
*   **User Authentication**: Secure login and signup with password validation
*   **Product Browsing**: Explore products with images, descriptions, and pricing
*   **Shopping Cart**: Add products to cart, update quantities, and view totals
*   **Order Management**: Place orders and track order history in real-time
*   **Discount Events**: View active promotional events and apply discounts
*   **Supplier Communication**: Send notifications and inquiries directly to suppliers
*   **Account Management**: Update profile information, address, and password
*   **Help Center**: Access support resources and frequently asked questions

### 📦 For Suppliers
*   **Dashboard Overview**: View sales analytics, order counts, and performance metrics
*   **Product Management**: Add, edit, and delete products with support for multiple images
*   **Inventory Control**: Track stock levels and manage product availability
*   **Order Processing**: View incoming orders and update shipping status
*   **Event Creation**: Create promotional discount events to boost sales
*   **Account Settings**: Configure business profile and contact information
*   **Help Resources**: Access supplier-specific documentation and guides

### 🛡️ For Administrators
*   **User Management**: View and manage all registered customers and suppliers
*   **Application Review**: Review and approve/reject new supplier registration applications
*   **System Monitoring**: Overview of platform activity and user statistics
*   **Data Management**: Access to system-wide data reports

---

## 📸 Screenshots

### 🔐 Authentication
<table>
  <tr>
    <td align="center"><img src="Project Images/Login.png" width="400"/><br/><b>Login Portal</b></td>
    <td align="center"><img src="Project Images/Signup.png" width="400"/><br/><b>Registration</b></td>
  </tr>
</table>

### 🛍️ Customer Interface
<table>
  <tr>
    <td align="center"><img src="Project Images/Customer Dashboard.png" width="400"/><br/><b>Dashboard</b></td>
    <td align="center"><img src="Project Images/Customer Explore Products.png" width="400"/><br/><b>Explore Products</b></td>
  </tr>
  <tr>
    <td align="center"><img src="Project Images/Customer Cart.png" width="400"/><br/><b>Shopping Cart</b></td>
    <td align="center"><img src="Project Images/Customer Orders.png" width="400"/><br/><b>Order History</b></td>
  </tr>
  <tr>
    <td align="center"><img src="Project Images/Customer Discount Events.png" width="400"/><br/><b>Discount Events</b></td>
    <td align="center"><img src="Project Images/Customer Help Center.png" width="400"/><br/><b>Help Center</b></td>
  </tr>
</table>

### 📦 Supplier Interface
<table>
  <tr>
    <td align="center"><img src="Project Images/Seller Dashboard.png" width="400"/><br/><b>Supplier Dashboard</b></td>
    <td align="center"><img src="Project Images/Seller Products.png" width="400"/><br/><b>Inventory Management</b></td>
  </tr>
  <tr>
    <td align="center"><img src="Project Images/Seller Order.png" width="400"/><br/><b>Order Processing</b></td>
    <td align="center"><img src="Project Images/Seller Adding Discount Event.png" width="400"/><br/><b>Create Event</b></td>
  </tr>
</table>

### 🛡️ Administrator Interface
<table>
  <tr>
    <td align="center"><img src="Project Images/Admin Dashboard.png" width="400"/><br/><b>Admin Dashboard</b></td>
    <td align="center"><img src="Project Images/Admin Total Customers.png" width="400"/><br/><b>Customer Management</b></td>
  </tr>
</table>

---

## 🛠️ Technology Stack

| Component | Technology | Details |
| :--- | :--- | :--- |
| **Language** | ![Java](https://img.shields.io/badge/Java-23-ED8B00?style=flat-square&logo=java&logoColor=white) | Core Logic & Swing GUI |
| **Database** | ![MySQL](https://img.shields.io/badge/MySQL-8.0+-00758F?style=flat-square&logo=mysql&logoColor=white) | Relational Data Storage |
| **Connectivity** | ![JDBC](https://img.shields.io/badge/JDBC-Connector/J-CA3132?style=flat-square) | MySQL Connector 9.3.0 |
| **IDE** | ![IntelliJ](https://img.shields.io/badge/IntelliJ_IDEA-Recommended-000000?style=flat-square&logo=intellij-idea&logoColor=white) | Development Environment |

---

## 🚀 Getting Started

### Prerequisites

Before you begin, ensure you have the following installed:
*   **Java Development Kit (JDK)**: Version 23 or higher
*   **MySQL Server**: Version 8.0 or higher
*   **MySQL Connector/J**: Version 9.3.0
*   **Git**: For version control

### 📦 Installation Guide

1.  **Clone the Repository**
    ```bash
    git clone https://github.com/wajidcodes/istore-ecommerce-system.git
    cd istore-ecommerce-system/EcommerceStore1/EcommerceStore
    ```

2.  **Database Setup**
    *   The application requires a MySQL database named `loginaccounts`.
    *   Please follow the detailed [INSTALLATION.md](INSTALLATION.md) guide to run the SQL scripts and create the necessary tables.

3.  **Configure Credentials**
    *   Open the project in your IDE.
    *   Locate the database connection strings in the source files.
    *   Update the `user` and `password` fields with your local MySQL credentials.

    > [!WARNING]
    > **Security Notice**: ensure you replace `"YOUR_MYSQL_PASSWORD_HERE"` with your actual local MySQL password before running.

4.  **Compile and Run**
    You can run the application directly from IntelliJ IDEA (recommended) or use the command line:

    ```bash
    javac -d out -sourcepath src src/Project1/src/com/accounts/LoginForm.java
    java -cp out:lib/mysql-connector-j-9.3.0.jar Project1.src.com.accounts.LoginForm
    ```

For troubleshooting and detailed setup, refer to **[INSTALLATION.md](INSTALLATION.md)**.

---

## 📁 Project Structure

```text
EcommerceStore/
├── src/
│   ├── Accounts/              # Account management logic (Customer/Supplier)
│   ├── Admin/                 # Administrative dashboard and controls
│   ├── cDashboard/            # Customer Interface (Cart, Products, Orders)
│   ├── sDashboard/            # Supplier Interface (Inventory, Sales, Events)
│   ├── Project1/              # Core Authentication (Login, Signup)
│   ├── HelpCenter/            # User support modules
│   ├── Main/                  # Application entry point
│   └── images/                # UI Assets and Icons
├── out/                       # Compiled bytecode
├── .idea/                     # IDE Configuration
├── README.md                  # Project Documentation
├── INSTALLATION.md            # Setup Guide
├── REQUIREMENTS.md            # System Specs
├── SECURITY.md                # Security Policy
└── LICENSE                    # MIT License
```

---

## �️ Database Schema

The application relies on a robust relational schema. Key tables include:

*   **accounts**: Base table for all user authentication.
*   **customeraccounts / supplieraccounts**: Extended profile data.
*   **supplierproducts**: Inventory catalog with pricing and referencing.
*   **supplierproductimages**: BLOB storage for product visuals.
*   **customerorders**: Transactional records linking users and products.
*   **events**: Promotional time-sensitive data.

*(See [INSTALLATION.md](INSTALLATION.md#database-schema) for the full SQL script)*

---

## 👥 Contributors

This project was developed as part of the **Object-Oriented Programming** course at **NUML Faisalabad**.

<table>
  <tr>
    <td align="center">
      <a href="https://www.linkedin.com/in/wajid-ali-developer">
        <img src="https://ui-avatars.com/api/?name=Wajid+Ali&background=0D8ABC&color=fff&size=100&rounded=true" width="80px"/><br />
        <b>Wajid Ali</b>
      </a><br />
      Lead Developer<br />
      <a href="https://github.com/wajidcodes">@wajidcodes</a>
    </td>
    <td align="center">
      <a href="https://www.linkedin.com/in/umais-shahid-96675a396/">
        <img src="https://ui-avatars.com/api/?name=Umais+Shahid&background=random&color=fff&size=100&rounded=true" width="80px"/><br />
        <b>Umais Shahid</b>
      </a><br />
      Co-Developer<br />
      <a href="https://linkedin.com">@Limitless</a>
    </td>
  </tr>
</table>

### Course Details
*   **Course**: Object-Oriented Programming (2nd Semester, 2024-2025)
*   **Instructors**: 
    *   Engr. Dr. Naeem Raza (Theory)
    *   Hafiz Muhammad Adnan Asghar (Lab)

---

## 📞 Support

<div align="center">
  <p>Need help? Found a bug? Feel free to reach out!</p>
  
  <a href="https://github.com/wajidcodes/istore-ecommerce-system/issues">
    <img src="https://img.shields.io/badge/GitHub-Open_Issue-181717?style=for-the-badge&logo=github&logoColor=white" />
  </a>
  <a href="mailto:m.wajid.t.v@gmail.com">
    <img src="https://img.shields.io/badge/Email-Contact_Team-EA4335?style=for-the-badge&logo=gmail&logoColor=white" />
  </a>
</div>

---

<div align="center">
  <i>Made with ❤️ by the IStore Team • 2025</i>
</div>
