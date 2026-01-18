<div align="center">
  <img src="EcommerceStore1/EcommerceStore/src/images/store2.png" alt="IStore Logo" width="120" />
  <h1>📋 System Requirements</h1>
  <p><b>Hardware and Software specifications for optimal performance.</b></p>
</div>

---


This document outlines all system and software requirements for the IStore E-Commerce Management System.

## 📋 Table of Contents

1. [System Requirements](#system-requirements)
2. [Software Requirements](#software-requirements)
3. [Dependencies](#dependencies)
4. [Development Environment](#development-environment)
5. [Network Requirements](#network-requirements)
6. [Database Requirements](#database-requirements)

## System Requirements

### Minimum Hardware Requirements

| Component | Specification |
|-----------|---------------|
| **Processor** | Intel Core i3 (3rd gen) / AMD Ryzen 3 or equivalent |
| **RAM** | 4 GB |
| **Storage** | 500 MB free disk space |
| **Display** | 1200x650 resolution |
| **Graphics** | Integrated graphics with Java 2D support |
| **Input Devices** | Keyboard and Mouse |

### Recommended Hardware Requirements

| Component | Specification |
|-----------|---------------|
| **Processor** | Intel Core i5 (8th gen) / AMD Ryzen 5 or better |
| **RAM** | 8 GB or more |
| **Storage** | 1 GB free disk space (SSD recommended) |
| **Display** | 1920x1080 resolution or higher |
| **Graphics** | Dedicated graphics card (optional, for better UI rendering) |
| **Network** | Stable internet connection for future cloud features |

## Software Requirements

### Operating System

The application is cross-platform and supports:

| Operating System | Version | Status |
|-----------------|---------|--------|
| **Windows** | Windows 10, Windows 11 | ✅ Fully Supported |
| **macOS** | macOS 10.14 (Mojave) or later | ✅ Fully Supported |
| **Linux** | Ubuntu 20.04+, Fedora 33+, Debian 10+ | ✅ Fully Supported |

### Java Runtime Environment

| Component | Version | Required/Optional |
|-----------|---------|-------------------|
| **JDK (Java Development Kit)** | 23 or higher | ✅ Required |
| **JRE (Java Runtime Environment)** | 23 or higher | ✅ Required (included with JDK) |

**Why JDK 23?**
- Modern language features and performance improvements
- Better memory management
- Enhanced security features
- Project was developed and tested with JDK 23

### Database System

| Component | Version | Required/Optional |
|-----------|---------|-------------------|
| **MySQL Server** | 8.0 or higher | ✅ Required |
| **MySQL Workbench** | Latest version | ⭕ Optional (for database management) |

**Database Size Estimates:**
- Fresh installation: ~10 MB
- With sample data: ~50 MB
- With 1000 products and images: ~500 MB - 2 GB

## Dependencies

### Required Libraries

#### 1. MySQL Connector/J (JDBC Driver)

| Attribute | Details |
|-----------|---------|
| **Library Name** | MySQL Connector/J |
| **Version** | 9.3.0 |
| **File** | `mysql-connector-j-9.3.0.jar` |
| **Purpose** | Database connectivity and operations |
| **License** | GPL v2.0 with Universal FOSS Exception |
| **Download** | [MySQL Official Website](https://dev.mysql.com/downloads/connector/j/) |
| **Size** | ~2.5 MB |

**Maven Coordinates:**
```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>9.3.0</version>
</dependency>
```

**Gradle:**
```gradle
implementation 'com.mysql:mysql-connector-j:9.3.0'
```

### Java Standard Libraries

The following Java standard libraries are used (included with JDK):

| Library | Package | Purpose |
|---------|---------|---------|
| **Swing** | `javax.swing.*` | GUI framework |
| **AWT** | `java.awt.*` | Graphics and event handling |
| **JDBC** | `java.sql.*` | Database connectivity |
| **I/O** | `java.io.*` | File and stream operations |
| **Utilities** | `java.util.*` | Collections, Calendar, Pattern matching |

### Optional Development Tools

| Tool | Version | Purpose |
|------|---------|---------|
| **IntelliJ IDEA** | 2023.3 or later | IDE (recommended) |
| **Eclipse** | 2023-12 or later | IDE (alternative) |
| **NetBeans** | 19 or later | IDE (alternative) |
| **Git** | 2.40+ | Version control |
| **MySQL Workbench** | 8.0+ | Database management GUI |

## Development Environment

### IDE Configuration Requirements

#### IntelliJ IDEA (Recommended)

| Setting | Value |
|---------|-------|
| **Project SDK** | JDK 23 |
| **Language Level** | 23 - Pattern matching for switch, record patterns |
| **Compiler Output** | `out/` directory |
| **Source Folders** | `src/` |
| **Libraries** | MySQL Connector/J 9.3.0 |
| **Encoding** | UTF-8 |

#### Eclipse

| Setting | Value |
|---------|-------|
| **Java Compiler** | JDK 23 |
| **JRE System Library** | JDK 23 |
| **Build Path** | Include MySQL Connector JAR |
| **Output Folder** | `bin/` or `out/` |

### Build Configuration

#### Classpath Requirements

The classpath must include:
1. Project source directory: `src/`
2. Compiled classes directory: `out/` or `bin/`
3. MySQL Connector JAR: `mysql-connector-j-9.3.0.jar`

**Example Classpath (Windows):**
```
.;out;C:\path\to\mysql-connector-j-9.3.0.jar
```

**Example Classpath (macOS/Linux):**
```
.:out:/path/to/mysql-connector-j-9.3.0.jar
```

## Network Requirements

### Database Connection

| Attribute | Value |
|-----------|-------|
| **Protocol** | TCP/IP |
| **Default Port** | 3306 (MySQL) |
| **Host** | localhost / 127.0.0.1 |
| **Connection Type** | Local (same machine) |

### Firewall Configuration

If MySQL is on a remote server:
- Allow inbound connections on port 3306
- Configure MySQL to accept remote connections
- Update `bind-address` in `my.cnf` or `my.ini`

### Internet Connectivity

| Feature | Requirement |
|---------|-------------|
| **Core Application** | ❌ Not required (works offline) |
| **Social Login (Commented)** | ✅ Required (if enabled) |
| **Future Updates** | ⭕ Optional |

## Database Requirements

### MySQL Server Configuration

#### Minimum Configuration

```ini
[mysqld]
# Basic Settings
port = 3306
max_connections = 50
max_allowed_packet = 16M

# Character Set
character-set-server = utf8mb4
collation-server = utf8mb4_unicode_ci

# Storage
innodb_buffer_pool_size = 256M
innodb_log_file_size = 64M
```

#### Recommended Configuration

```ini
[mysqld]
# Basic Settings
port = 3306
max_connections = 150
max_allowed_packet = 64M

# Character Set
character-set-server = utf8mb4
collation-server = utf8mb4_unicode_ci

# Performance
innodb_buffer_pool_size = 512M
innodb_log_file_size = 128M
innodb_flush_log_at_trx_commit = 2

# Binary Logging (for backups)
log_bin = mysql-bin
expire_logs_days = 7
```

### Database Storage Requirements

| Data Type | Estimated Size |
|-----------|----------------|
| **User Accounts** | ~1 KB per user |
| **Product Catalog** | ~5 KB per product (without images) |
| **Product Images** | 100 KB - 5 MB per image (BLOB storage) |
| **Orders** | ~2 KB per order |
| **Events** | ~1 KB per event |

**Total Database Size Examples:**
- 100 users + 200 products + 500 images: ~750 MB
- 1,000 users + 2,000 products + 5,000 images: ~7 GB

### Required MySQL Privileges

The MySQL user account requires the following privileges on the `loginaccounts` database:

```sql
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, ALTER, INDEX 
ON loginaccounts.* 
TO 'your_user'@'localhost';
```

For full administrative access during development:
```sql
GRANT ALL PRIVILEGES ON loginaccounts.* TO 'root'@'localhost';
FLUSH PRIVILEGES;
```

## Resource Files

### Required Image Assets

The application requires the following image files in `src/images/`:

| File | Purpose | Format | Approximate Size |
|------|---------|--------|------------------|
| `store2.png` | Application icon | PNG | 50-200 KB |
| `realsidelogin.png` | Login page background | PNG | 200-500 KB |
| `adminlogo.png` | Admin panel logo | PNG | 20-100 KB |
| `newLogo.png` | Brand logo | PNG | 20-100 KB |
| Various icons | UI elements | PNG | 5-20 KB each |

**Total Assets Size**: ~1-2 MB

## Security Requirements

### Password Policy (Current Implementation)

| Requirement | Status |
|-------------|--------|
| **Minimum Length** | 8 characters |
| **Uppercase Letter** | ✅ Required |
| **Lowercase Letter** | ✅ Required |
| **Number** | ✅ Required |
| **Special Character** | ✅ Required |
| **Password Hashing** | ❌ Not implemented (plain text storage) |

> [!WARNING]
> **Security Notice**: The current implementation stores passwords in plain text. For production use, implement password hashing (bcrypt, Argon2, etc.).

### Future Security Enhancements

For production deployment, consider:
- [ ] Implement bcrypt or Argon2 password hashing
- [ ] Use environment variables for database credentials
- [ ] Implement SQL injection prevention (already using Prepared Statements)
- [ ] Add session management and timeout
- [ ] Implement HTTPS for future web version
- [ ] Add input sanitization and validation
- [ ] Implement rate limiting for login attempts

## Performance Benchmarks

### Expected Performance (on Recommended Hardware)

| Operation | Expected Time |
|-----------|---------------|
| **Application Startup** | 2-5 seconds |
| **Login** | < 1 second |
| **Load Product Catalog (100 items)** | 1-3 seconds |
| **Add to Cart** | < 500 ms |
| **Place Order** | < 1 second |
| **Image Loading** | 500 ms - 2 seconds (per image from DB) |

### Database Query Performance

| Query Type | Expected Time |
|------------|---------------|
| **User Authentication** | < 100 ms |
| **Product Listing** | 100-500 ms (100 products) |
| **Order Insertion** | < 200 ms |
| **Image BLOB Retrieval** | 200-1000 ms (depending on image size) |

## Accessibility Requirements

| Feature | Support |
|---------|---------|
| **Screen Resolution** | Minimum 1200x650 |
| **Color Depth** | 24-bit true color |
| **Font Rendering** | Anti-aliasing supported |
| **Keyboard Navigation** | Tab navigation supported |
| **Mouse Interaction** | Required |

## Compliance and Standards

| Standard | Compliance |
|----------|------------|
| **Java Coding Conventions** | ✅ Followed |
| **SQL Standard** | SQL:2016 compliant |
| **Character Encoding** | UTF-8 |
| **Date Format** | ISO 8601 (YYYY-MM-DD) |

## Version Compatibility

### Java Version Compatibility

| Java Version | Status |
|--------------|--------|
| **JDK 23** | ✅ Fully Tested |
| **JDK 21** | ⚠️ Should work (not tested) |
| **JDK 17** | ⚠️ May require code modifications |
| **JDK 11 or lower** | ❌ Not compatible |

### MySQL Version Compatibility

| MySQL Version | Status |
|---------------|--------|
| **8.0+** | ✅ Fully Supported |
| **5.7** | ⚠️ Should work (not tested) |
| **5.6 or lower** | ❌ Not recommended |

### MySQL Connector/J Compatibility

| Connector Version | Status |
|-------------------|--------|
| **9.3.0** | ✅ Recommended |
| **9.x.x** | ✅ Should work |
| **8.x.x** | ⚠️ May require driver class change |
| **5.x.x** | ❌ Not compatible |

## Testing Requirements

For quality assurance and testing:

### Unit Testing (Optional)
- **JUnit 5**: For unit testing (not currently implemented)
- **Mockito**: For mocking database connections

### Integration Testing
- Live MySQL database connection
- Sample test data
- All user roles configured

## Support and Compatibility Matrix

| OS | JDK 23 | MySQL 8.0 | Status |
|----|--------|-----------|--------|
| **Windows 11** | ✅ | ✅ | ✅ Tested |
| **Windows 10** | ✅ | ✅ | ✅ Tested |
| **macOS Sonoma** | ✅ | ✅ | ⚠️ Should work |
| **Ubuntu 22.04** | ✅ | ✅ | ⚠️ Should work |
| **Other Linux** | ✅ | ✅ | ⚠️ Should work |

---

**Last Updated**: January 2026  
**Version**: 1.0  
**Maintained by**: Development Team

For installation instructions based on these requirements, see [INSTALLATION.md](INSTALLATION.md).
