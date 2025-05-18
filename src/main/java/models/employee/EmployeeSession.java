package models.employee;

import java.sql.Timestamp;

/**
 * Represents an employee session in the system.
 */
public class EmployeeSession {
    private String sessionId;

    private int employeeId;

    private boolean isExpired;
    private Timestamp expiresAt;

    private Timestamp createdAt;

    /**
     * Default constructor for the EmployeeSession class.
     */
    public EmployeeSession() {
    }

    /**
     * Constructs a new EmployeeSession object with the specified details.
     *
     * @param sessionId  The unique identifier for the session.
     * @param employeeId The ID of the employee associated with the session.
     * @param isExpired  A boolean indicating if the session is expired.
     * @param expiresAt  The timestamp when the session expires.
     * @param createdAt  The timestamp when the session was created.
     */
    public EmployeeSession(String sessionId, int employeeId, boolean isExpired, Timestamp expiresAt,
            Timestamp createdAt) {
        this.sessionId = sessionId;
        this.employeeId = employeeId;
        this.isExpired = isExpired;
        this.expiresAt = expiresAt;
        this.createdAt = createdAt;
    }

    /**
     * Gets the session ID.
     *
     * @return The unique identifier for the session.
     */
    public String getSessionId() {
        return this.sessionId;
    }

    /**
     * Gets the employee ID associated with the session.
     *
     * @return The employee ID.
     */
    public int getEmployeeId() {
        return this.employeeId;
    }

    /**
     * Checks if the session is expired.
     *
     * @return True if the session is expired, false otherwise.
     */
    public boolean isExpired() {
        return this.isExpired;
    }

    /**
     * Gets the timestamp when the session expires.
     *
     * @return The expiration timestamp.
     */
    public Timestamp getExpiresAt() {
        return this.expiresAt;
    }

    /**
     * Gets the timestamp when the session was created.
     *
     * @return The creation timestamp.
     */
    public Timestamp getCreatedAt() {
        return this.createdAt;
    }
}
