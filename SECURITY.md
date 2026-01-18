<div align="center">
  <img src="EcommerceStore1/EcommerceStore/src/images/store2.png" alt="IStore Logo" width="120" />
  <h1>🛡️ Security Policy</h1>
  <p><b>Vulnerability reporting and security best practices.</b></p>
</div>

---


## Supported Versions

Currently, only the latest version receives security updates:

| Version | Supported          |
| ------- | ------------------ |
| 1.0.x   | :white_check_mark: |

## Security Considerations

This project was developed as an educational application for an Object-Oriented Programming course. While it demonstrates core OOP concepts and database integration, **it contains known security vulnerabilities** that make it unsuitable for production use without significant modifications.

## Known Security Issues

### Critical Vulnerabilities

#### 1. Plain Text Password Storage

**Issue**: Passwords are stored in plain text in the MySQL database.

**Risk**: 
- If the database is compromised, all user passwords are immediately exposed
- Users who reuse passwords across services are at risk
- Violates security best practices and compliance requirements

**Files Affected**:
- `LoginForm.java`
- `SignupForm.java`
- All authentication-related files

**Recommendation**:
```java
// BAD - Current Implementation
String password = passwordField.getText();
pst.setString(2, password); // Storing plain text

// GOOD - Recommended Implementation
import org.mindrot.jbcrypt.BCrypt;

String password = passwordField.getText();
String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
pst.setString(2, hashedPassword);

// For login verification:
if (BCrypt.checkpw(inputPassword, storedHashedPassword)) {
    // Password matches
}
```

**Remediation Steps**:
1. Add BCrypt library dependency
2. Update signup process to hash passwords before storage
3. Modify login process to verify against hashed passwords
4. Migrate existing plain text passwords (if any production data exists)

---

#### 2. Hardcoded Database Credentials

**Issue**: Database credentials are hardcoded directly in source files.

**Risk**:
- Credentials exposed in version control history
- Difficult to change credentials without modifying code
- Anyone with access to source code has database access
- Different environments (dev, staging, prod) require code changes

**Files Affected**:
- `LoginForm.java` (lines 27-29)
- `SignupForm.java`
- `ExploreProduct.java` (lines 21-23)
- All files in `cDashboard/` package
- All files in `sDashboard/` package
- `Admin/` package files
- `Accounts/` package files

**Current Code**:
```java
private static final String url = "jdbc:mysql://127.0.0.1:3306/loginaccounts";
private static final String user = "root";
private static final String password = "Bazar@37250"; // EXPOSED!
```

**Recommendation**:

**Option 1: Environment Variables**
```java
private static final String url = System.getenv("DB_URL");
private static final String user = System.getenv("DB_USER");
private static final String password = System.getenv("DB_PASSWORD");
```

**Option 2: Configuration File**
```java
// Create a db.properties file (add to .gitignore!)
Properties props = new Properties();
try (InputStream input = new FileInputStream("config/db.properties")) {
    props.load(input);
    String url = props.getProperty("db.url");
    String user = props.getProperty("db.user");
    String password = props.getProperty("db.password");
}
```

**Option 3: Configuration Class**
```java
public class DatabaseConfig {
    private static Properties properties = new Properties();
    
    static {
        try (InputStream input = DatabaseConfig.class
                .getClassLoader()
                .getResourceAsStream("db.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load database configuration", e);
        }
    }
    
    public static String getUrl() {
        return properties.getProperty("db.url");
    }
    
    public static String getUser() {
        return properties.getProperty("db.user");
    }
    
    public static String getPassword() {
        return properties.getProperty("db.password");
    }
}
```

**Immediate Action Required**:
- Before committing to public repository, remove or replace hardcoded credentials
- Add `db.properties` to `.gitignore`
- Use environment variables or external configuration

---

### High Severity Issues

#### 3. No Session Management

**Issue**: No session timeout or session invalidation.

**Risk**:
- Unattended computers remain logged in indefinitely
- No automatic logout after inactivity
- Increased risk of unauthorized access

**Recommendation**:
Implement session timeout mechanism:
```java
private long lastActivityTime;
private static final long SESSION_TIMEOUT = 30 * 60 * 1000; // 30 minutes

private void checkSessionTimeout() {
    if (System.currentTimeMillis() - lastActivityTime > SESSION_TIMEOUT) {
        logout();
        showTimeoutMessage();
    }
}

// Update lastActivityTime on any user interaction
private void updateActivity() {
    lastActivityTime = System.currentTimeMillis();
}
```

---

#### 4. Limited Input Validation

**Issue**: Insufficient validation of user inputs.

**Risk**:
- Potential for SQL injection (mitigated by prepared statements)
- Invalid data in database
- Application crashes from unexpected input
- Cross-site scripting if migrated to web interface

**Current Mitigation**:
- Using prepared statements (good!)
- Basic email format validation
- Password strength requirements

**Additional Recommendations**:
```java
// Input sanitization
public static String sanitizeInput(String input) {
    if (input == null) return "";
    return input.trim()
                .replaceAll("[<>\"']", "") // Remove potentially dangerous characters
                .substring(0, Math.min(input.length(), MAX_INPUT_LENGTH));
}

// Email validation enhancement
private static final Pattern EMAIL_PATTERN = 
    Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

public static boolean isValidEmail(String email) {
    return email != null && EMAIL_PATTERN.matcher(email).matches();
}

// Phone number validation
public static boolean isValidPhone(String phone) {
    return phone != null && phone.matches("^\\+?[1-9]\\d{1,14}$");
}
```

---

#### 5. No Rate Limiting

**Issue**: No protection against brute force login attempts.

**Risk**:
- Attackers can attempt unlimited login combinations
- Easy to guess weak passwords through brute force
- No account lockout mechanism

**Recommendation**:
```java
private static Map<String, Integer> loginAttempts = new HashMap<>();
private static Map<String, Long> lockoutTimestamps = new HashMap<>();
private static final int MAX_ATTEMPTS = 5;
private static final long LOCKOUT_DURATION = 15 * 60 * 1000; // 15 minutes

public boolean checkLoginAttempts(String email) {
    // Check if account is locked out
    if (lockoutTimestamps.containsKey(email)) {
        long lockoutTime = lockoutTimestamps.get(email);
        if (System.currentTimeMillis() - lockoutTime < LOCKOUT_DURATION) {
            return false; // Still locked out
        } else {
            // Unlock account
            lockoutTimestamps.remove(email);
            loginAttempts.remove(email);
        }
    }
    
    // Check attempts
    int attempts = loginAttempts.getOrDefault(email, 0);
    if (attempts >= MAX_ATTEMPTS) {
        lockoutTimestamps.put(email, System.currentTimeMillis());
        return false;
    }
    
    return true;
}

public void recordFailedLogin(String email) {
    int attempts = loginAttempts.getOrDefault(email, 0) + 1;
    loginAttempts.put(email, attempts);
}

public void recordSuccessfulLogin(String email) {
    loginAttempts.remove(email);
    lockoutTimestamps.remove(email);
}
```

---

### Medium Severity Issues

#### 6. Database Connection Management

**Issue**: Each operation creates a new database connection.

**Risk**:
- Performance degradation under load
- Potential connection exhaustion
- Resource leaks if connections aren't properly closed

**Current Implementation**:
Using try-with-resources (good for cleanup, but inefficient)

**Recommendation**:
Implement connection pooling:
```java
// Using HikariCP (add dependency)
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseConnectionPool {
    private static HikariDataSource dataSource;
    
    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/loginaccounts");
        config.setUsername("root");
        config.setPassword("password");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setConnectionTimeout(30000);
        
        dataSource = new HikariDataSource(config);
    }
    
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
```

---

#### 7. Error Messages Expose Information

**Issue**: Detailed error messages may reveal system information.

**Risk**:
- Stack traces shown to users reveal file paths and structure
- Database error messages expose schema information
- Helps attackers understand the system

**Recommendation**:
```java
try {
    // Database operation
} catch (SQLException e) {
    // Log detailed error for developers
    logger.error("Database error: " + e.getMessage(), e);
    
    // Show generic message to users
    JOptionPane.showMessageDialog(null, 
        "An error occurred. Please try again later.",
        "Error", 
        JOptionPane.ERROR_MESSAGE);
}
```

---

#### 8. No Data Encryption

**Issue**: Sensitive data transmitted and stored without encryption.

**Risk**:
- Data readable if intercepted
- Database breach exposes all data
- Sensitive customer information at risk

**Recommendation**:
- Use SSL/TLS for database connections
- Encrypt sensitive fields before storage
- Consider full database encryption

```java
// Enable SSL for MySQL connection
String url = "jdbc:mysql://localhost:3306/loginaccounts?useSSL=true&requireSSL=true";
```

---

## Security Best Practices for Production

If deploying this application in a production environment, implement these security measures:

### Essential (Must Have)

- [ ] **Password Hashing**: Implement bcrypt or Argon2
- [ ] **Secure Configuration**: Remove hardcoded credentials
- [ ] **Input Validation**: Validate and sanitize all inputs
- [ ] **Error Handling**: Generic user messages, detailed logs
- [ ] **HTTPS/SSL**: Encrypt data in transit
- [ ] **Rate Limiting**: Prevent brute force attacks
- [ ] **Session Management**: Implement timeout and invalidation
- [ ] **SQL Injection Prevention**: Continue using prepared statements
- [ ] **Access Control**: Verify role-based permissions
- [ ] **Audit Logging**: Log security-relevant events

### Recommended (Should Have)

- [ ] **Two-Factor Authentication**: Add 2FA for sensitive accounts
- [ ] **Email Verification**: Verify email addresses during signup
- [ ] **Password Reset Security**: Secure password reset mechanism
- [ ] **Account Lockout**: Lock accounts after failed attempts
- [ ] **Data Encryption**: Encrypt sensitive data at rest
- [ ] **Regular Updates**: Keep dependencies updated
- [ ] **Security Headers**: Implement security headers (if web version)
- [ ] **Penetration Testing**: Regular security testing
- [ ] **Backup and Recovery**: Secure backup procedures
- [ ] **Monitoring**: Security event monitoring

### Advanced (Nice to Have)

- [ ] **Intrusion Detection**: Monitor for suspicious activity
- [ ] **Web Application Firewall**: If migrating to web
- [ ] **CAPTCHA**: Prevent automated attacks
- [ ] **Security Training**: Train users on security
- [ ] **Compliance**: Meet industry standards (PCI-DSS if handling payments)

---

## Reporting a Vulnerability

### Educational Context

Since this is an educational project, the known vulnerabilities listed above are for learning purposes. However, if you discover **additional** security issues not mentioned here, please report them.

### How to Report

1. **Do NOT** open a public GitHub issue for security vulnerabilities
2. Email the maintainers directly or contact via GitHub: [@wajidcodes](https://github.com/wajidcodes)
3. Include:
   - Description of the vulnerability
   - Steps to reproduce
   - Potential impact
   - Suggested fix (if any)

### Response Timeline

- **Initial Response**: Within 3 business days
- **Status Update**: Within 7 days
- **Fix Timeline**: Depends on severity
  - Critical: 7-14 days
  - High: 14-30 days
  - Medium: 30-60 days
  - Low: Future release

### Disclosure Policy

- We follow responsible disclosure practices
- We will credit researchers who report vulnerabilities (with permission)
- Please allow us reasonable time to address issues before public disclosure

---

## Security Checklist for Deployment

Before deploying this application:

```
[ ] Removed all hardcoded credentials
[ ] Implemented password hashing
[ ] Added environment-based configuration
[ ] Enabled database SSL/TLS
[ ] Implemented comprehensive input validation
[ ] Added rate limiting for login
[ ] Implemented session timeout
[ ] Added audit logging
[ ] Updated all dependencies to latest secure versions
[ ] Performed security code review
[ ] Conducted penetration testing
[ ] Set up monitoring and alerting
[ ] Created incident response plan
[ ] Backed up database with encryption
[ ] Implemented least privilege principle for database user
[ ] Documented security procedures
```

---

## Additional Resources

### Security Libraries for Java

- **BCrypt**: [jBCrypt](https://github.com/jeremyh/jBCrypt)
- **OWASP Java Encoder**: Input/output encoding
- **Apache Shiro**: Security framework
- **Spring Security**: Comprehensive security (if using Spring)

### Security Guidelines

- [OWASP Top 10](https://owasp.org/www-project-top-ten/)
- [Java Security Best Practices](https://www.oracle.com/java/technologies/javase/seccodeguide.html)
- [MySQL Security Guide](https://dev.mysql.com/doc/refman/8.0/en/security.html)

### Compliance Frameworks

- PCI-DSS (if handling payment cards)
- GDPR (if handling EU user data)
- HIPAA (if handling health information)

---

## Disclaimer

This software is provided "as is" for educational purposes. The developers make no warranties regarding security and are not liable for any damages from security breaches. Users deploy at their own risk.

**For educational/demonstration purposes only. Not production-ready.**

---

**Last Updated**: January 18, 2026  
**Version**: 1.0
