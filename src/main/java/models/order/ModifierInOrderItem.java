package models.order;

/**
 * Represents a modifier applied to a specific order item in the POS system.
 */
public class ModifierInOrderItem {
    private Integer modifierInOrderItemId;

    private Integer modifierId;
    private Integer orderItemId;

    /**
     * Default constructor.
     */
    public ModifierInOrderItem() {
    }

    /**
     * Constructs a new ModifierInOrderItem with the specified details.
     *
     * @param modifierInOrderItemId The unique identifier for this record.
     * @param modifierId            The ID of the product modifier.
     * @param orderItemId           The ID of the order item to which the modifier is applied.
     */
    public ModifierInOrderItem(Integer modifierInOrderItemId, Integer modifierId, Integer orderItemId) {
        this.modifierInOrderItemId = modifierInOrderItemId;
        this.modifierId = modifierId;
        this.orderItemId = orderItemId;
    }

    /**
     * Gets the unique identifier for this modifier-in-order-item record.
     *
     * @return The ID.
     */
    public Integer getModifierInOrderItemId() {
        return this.modifierInOrderItemId;
    }

    /**
     * Sets the unique identifier for this modifier-in-order-item record.
     *
     * @param modifierInOrderItemId The ID to set.
     */
    public void setModifierInOrderItemId(Integer modifierInOrderItemId) {
        this.modifierInOrderItemId = modifierInOrderItemId;
    }

    /**
     * Gets the product modifier ID.
     *
     * @return The modifier ID.
     */
    public Integer getModifierId() {
        return this.modifierId;
    }

    /**
     * Sets the product modifier ID.
     *
     * @param modifierId The modifier ID to set.
     * @throws IllegalArgumentException if modifierId is null or negative.
     */
    public void setModifierId(Integer modifierId) {
        if (modifierId == null || modifierId <= 0) {
            throw new IllegalArgumentException("Modifier ID must be a positive integer.");
        }
        this.modifierId = modifierId;
    }

    /**
     * Gets the order item ID this modifier is applied to.
     *
     * @return The order item ID.
     */
    public Integer getOrderItemId() {
        return this.orderItemId;
    }

    /**
     * Sets the order item ID this modifier is applied to.
     *
     * @param orderItemId The order item ID to set.
     * @throws IllegalArgumentException if orderItemId is null or negative.
     */
    public void setOrderItemId(Integer orderItemId) {
        if (orderItemId == null || orderItemId <= 0) {
            throw new IllegalArgumentException("Order item ID must be a positive integer.");
        }
        this.orderItemId = orderItemId;
    }
}
