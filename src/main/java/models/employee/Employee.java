package models.employee;

import java.math.BigDecimal;
import java.sql.Timestamp;
import utilities.cryptography.Cryptography;

/**
 * Represents an employee in the system.
 */
public class Employee {
    private Integer employeeId;
    private String username;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private String role;
    private BigDecimal salary;
    private String email;
    private String phoneNumber;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    /**
     * Default constructor.
     */
    public Employee() {}

    /**
     * Constructs an Employee with all fields initialized.
     * @param employeeId Employee's ID
     * @param username Username
     * @param password Plaintext password (will be hashed)
     * @param firstName First name
     * @param lastName Last name
     * @param role Role ("admin" or "employee")
     * @param salary Monthly salary
     * @param email Email address
     * @param phoneNumber Contact number
     * @param createdAt Timestamp when the record was created
     * @param updatedAt Timestamp when the record was last updated
     */
    public Employee(Integer employeeId, String username, String password, String firstName, String lastName,
                    String role, BigDecimal salary, String email, String phoneNumber,
                    Timestamp createdAt, Timestamp updatedAt) {
        this.setEmployeeId(employeeId);
        this.setUsername(username);
        this.setPasswordHash(password);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setRole(role);
        this.setSalary(salary);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);
    }

    /** Sets the employee ID. */
    public void setEmployeeId(Integer employeeId) {
        if (employeeId == null || employeeId <= 0) {
            throw new IllegalArgumentException("Employee ID cannot be null or <= 0.");
        }
        this.employeeId = employeeId;
    }

    /** Gets the employee ID. */
    public Integer getEmployeeId() {
        return this.employeeId;
    }

    /** Sets the username. */
    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        this.username = username.trim();
    }

    /** Gets the username. */
    public String getUsername() {
        return this.username;
    }

    /** Sets the password hash from the plaintext password. */
    public void setPasswordHash(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password hash cannot be null or empty.");
        }
        this.passwordHash = Cryptography.hashPassword(password);
    }

    /** Gets the password hash. */
    public String getPasswordHash() {
        return this.passwordHash;
    }

    /** Sets the first name. */
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty.");
        }
        this.firstName = firstName.trim();
    }

    /** Gets the first name. */
    public String getFirstName() {
        return this.firstName;
    }

    /** Sets the last name. */
    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty.");
        }
        this.lastName = lastName.trim();
    }

    /** Gets the last name. */
    public String getLastName() {
        return this.lastName;
    }

    /** Sets the employee's role. Only "admin" or "employee" are allowed. */
    public void setRole(String role) {
        if (role == null || (!role.equals("admin") && !role.equals("employee"))) {
            throw new IllegalArgumentException("Role must be 'admin' or 'employee'.");
        }
        this.role = role;
    }

    /** Gets the role. */
    public String getRole() {
        return this.role;
    }

    /** Sets the salary. Must be non-negative. */
    public void setSalary(BigDecimal salary) {
        if (salary == null || salary.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Salary must be non-negative.");
        }
        this.salary = salary;
    }

    /** Gets the salary. */
    public BigDecimal getSalary() {
        return this.salary;
    }

    /** Sets the email address. */
    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty() || !email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this.email = email.trim();
    }

    /** Gets the email address. */
    public String getEmail() {
        return this.email;
    }

    /** Sets the phone number. */
    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty.");
        }
        this.phoneNumber = phoneNumber.trim();
    }

    /** Gets the phone number. */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /** Sets the creation timestamp. */
    public void setCreatedAt(Timestamp createdAt) {
        if (createdAt == null) {
            throw new IllegalArgumentException("Created at cannot be null.");
        }
        this.createdAt = createdAt;
    }

    /** Gets the creation timestamp. */
    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    /** Sets the update timestamp. */
    public void setUpdatedAt(Timestamp updatedAt) {
        if (updatedAt == null) {
            throw new IllegalArgumentException("Updated at cannot be null.");
        }
        this.updatedAt = updatedAt;
    }

    /** Gets the update timestamp. */
    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }
}
