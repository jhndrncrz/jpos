package models.customer;

import java.sql.Timestamp;

/**
 * Represents an customer in the system.
 */
public class Customer {
    private Integer customerId;
    
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    /**
     * Default constructor.
     */
    public Customer() {}

    /**
     * Constructs an Customer with all fields initialized.
     * @param customerId Customer's ID
     * @param firstName First name
     * @param lastName Last name
     * @param address Address
     * @param email Email address
     * @param phoneNumber Contact number
     * @param createdAt Timestamp when the record was created
     * @param updatedAt Timestamp when the record was last updated
     */
    public Customer(Integer customerId, String firstName, String lastName,
                    String address, String email, String phoneNumber,
                    Timestamp createdAt, Timestamp updatedAt) {
        this.setCustomerId(customerId);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAddress(address);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);
    }

    /** Sets the customer ID. */
    public void setCustomerId(Integer customerId) {
        if (customerId == null || customerId <= 0) {
            throw new IllegalArgumentException("Customer ID cannot be null or <= 0.");
        }
        this.customerId = customerId;
    }

    /** Gets the customer ID. */
    public Integer getCustomerId() {
        return this.customerId;
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

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    /** Sets the address. */
    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty.");
        }
        this.address = address.trim();
    }

    /** Gets the address. */
    public String getAddress() {
        return this.address;
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
