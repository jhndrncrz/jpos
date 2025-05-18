package models.order;

import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Represents an order in the point of sale system.
 */
public class Order {
    private Integer orderId;

    private Integer employeeId;
    private Integer customerId;

    private String paymentMethod;
    private String status; // 'pending', 'completed', 'cancelled'

    private Timestamp createdAt;

    private List<OrderItem> items;

    /**
     * Default constructor.
     */
    public Order() {
    }

    /**
     * Constructs a new Order with the specified details.
     *
     * @param orderId       The unique identifier for the order.
     * @param employeeId    The ID of the employee who created the order.
     * @param totalAmount   The total amount of the order.
     * @param paymentMethod The payment method used.
     * @param status        The current status of the order.
     * @param createdAt     The timestamp when the order was created.
     */
    public Order(Integer orderId, Integer employeeId, Integer customerId, String paymentMethod, String status,
            Timestamp createdAt) {
        this.setOrderId(orderId);
        this.setEmployeeId(employeeId);
        this.setCustomerId(customerId);
        this.setPaymentMethod(paymentMethod);
        this.setStatus(status);
        this.setCreatedAt(createdAt);
        this.items = new ArrayList<OrderItem>();
    }

    /**
     * Gets the unique identifier for the order.
     *
     * @return The order ID.
     */
    public Integer getOrderId() {
        return this.orderId;
    }

    /**
     * Sets the unique identifier for the order.
     *
     * @param orderId The order ID to set.
     * @throws IllegalArgumentException if the ID is not positive.
     */
    public void setOrderId(Integer orderId) {
        if (orderId != null && orderId <= 0) {
            throw new IllegalArgumentException("Order ID must be positive.");
        }

        this.orderId = orderId;
    }

    /**
     * Gets the employee ID associated with the order.
     *
     * @return The employee ID.
     */
    public Integer getEmployeeId() {
        return this.employeeId;
    }

    /**
     * Sets the employee ID associated with the order.
     *
     * @param employeeId The employee ID to set.
     * @throws IllegalArgumentException if the ID is not positive.
     */
    public void setEmployeeId(Integer employeeId) {
        if (employeeId != null && employeeId <= 0) {
            throw new IllegalArgumentException("Employee ID must be positive.");
        }

        this.employeeId = employeeId;
    }

    /**
     * Gets the customer ID associated with the order.
     *
     * @return The customer ID.
     */
    public Integer getCustomerId() {
        return this.customerId;
    }

    /**
     * Sets the customer ID associated with the order.
     *
     * @param customerId The customer ID to set.
     * @throws IllegalArgumentException if the ID is not positive.
     */
    public void setCustomerId(Integer customerId) {
        if (customerId != null && customerId <= 0) {
            throw new IllegalArgumentException("Customer ID must be positive.");
        }

        this.customerId = customerId;
    }

    /**
     * Gets the total amount of the order.
     *
     * @return The total amount.
     */
    public BigDecimal getTotalAmount() {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (OrderItem item : this.items) {
            totalAmount = totalAmount.add(item.getSubtotal());
        }

        return totalAmount;
    }

    /**
     * Gets the payment method used for the order.
     *
     * @return The payment method.
     */
    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    /**
     * Sets the payment method used for the order.
     *
     * @param paymentMethod The payment method to set.
     * @throws IllegalArgumentException if the method is null or empty.
     */
    public void setPaymentMethod(String paymentMethod) {
        if (paymentMethod == null || paymentMethod.trim().isEmpty()) {
            throw new IllegalArgumentException("Payment method cannot be null or empty.");
        }

        this.paymentMethod = paymentMethod.trim();
    }

    /**
     * Gets the status of the order.
     *
     * @return The order status.
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Sets the status of the order.
     *
     * @param status The order status to set.
     * @throws IllegalArgumentException if the status is invalid.
     */
    public void setStatus(String status) {
        if (status == null ||
                !(status.equalsIgnoreCase("pending") ||
                        status.equalsIgnoreCase("completed") ||
                        status.equalsIgnoreCase("cancelled"))) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }
        this.status = status.toLowerCase();
    }

    /**
     * Gets the timestamp when the order was created.
     *
     * @return The creation timestamp.
     */
    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Sets the timestamp when the order was created.
     *
     * @param createdAt The creation timestamp to set.
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the list of order items associated with the order.
     *
     * @return The list of order items.
     */
    public List<OrderItem> getItems() {
        return this.items;
    }
}
