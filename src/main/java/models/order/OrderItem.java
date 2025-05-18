package models.order;

import java.util.List;

import models.product.ProductModifier;

import java.util.ArrayList;
import java.math.BigDecimal;

/**
 * Represents an item in an order, linking a product with quantity and pricing.
 */
public class OrderItem {
    private Integer orderItemId;

    private Integer orderId;
    private Integer productId;

    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;

    private List<ProductModifier> modifiers;

    /**
     * Default constructor.
     */
    public OrderItem() {
    }

    /**
     * Constructs a new OrderItem with the specified details.
     *
     * @param orderItemId The unique identifier for the order item.
     * @param orderId     The ID of the associated order.
     * @param productId   The ID of the associated product.
     * @param quantity    The quantity of the product ordered.
     * @param unitPrice   The unit price of the product.
     * @param subtotal    The subtotal for this item (unitPrice × quantity).
     */
    public OrderItem(Integer orderItemId, Integer orderId, Integer productId, Integer quantity, BigDecimal unitPrice, BigDecimal subtotal) {
        this.setOrderItemId(orderItemId);
        this.setOrderId(orderId);
        this.setProductId(productId);
        this.setQuantity(quantity);
        this.setUnitPrice(unitPrice);
        this.modifiers = new ArrayList<ProductModifier>();
    }

    /**
     * Gets the unique identifier for the order item.
     *
     * @return The order item ID.
     */
    public Integer getOrderItemId() {
        return this.orderItemId;
    }

    /**
     * Sets the unique identifier for the order item.
     *
     * @param orderItemId The order item ID to set.
     * @throws IllegalArgumentException if the ID is not positive.
     */
    public void setOrderItemId(Integer orderItemId) {
        if (orderItemId != null && orderItemId <= 0) {
            throw new IllegalArgumentException("Order item ID must be positive.");
        }
        this.orderItemId = orderItemId;
    }

    /**
     * Gets the ID of the associated order.
     *
     * @return The order ID.
     */
    public Integer getOrderId() {
        return this.orderId;
    }

    /**
     * Sets the ID of the associated order.
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
     * Gets the ID of the associated product.
     *
     * @return The product ID.
     */
    public Integer getProductId() {
        return this.productId;
    }

    /**
     * Sets the ID of the associated product.
     *
     * @param productId The product ID to set.
     * @throws IllegalArgumentException if the ID is not positive.
     */
    public void setProductId(Integer productId) {
        if (productId != null && productId <= 0) {
            throw new IllegalArgumentException("Product ID must be positive.");
        }
        this.productId = productId;
    }

    /**
     * Gets the quantity of the product ordered.
     *
     * @return The quantity.
     */
    public Integer getQuantity() {
        return this.quantity;
    }

    /**
     * Sets the quantity of the product ordered.
     *
     * @param quantity The quantity to set.
     * @throws IllegalArgumentException if quantity is null or not positive.
     */
    public void setQuantity(Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be a positive integer.");
        }
        this.quantity = quantity;
        this.calculateSubtotal();
    }

    /**
     * Gets the unit price of the product.
     *
     * @return The unit price.
     */
    public BigDecimal getUnitPrice() {
        return this.unitPrice;
    }

    /**
     * Sets the unit price of the product.
     *
     * @param unitPrice The unit price to set.
     * @throws IllegalArgumentException if unit price is null or negative.
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Unit price must be non-negative.");
        }
        this.unitPrice = unitPrice;
        this.calculateSubtotal();
    }

    /**
     * Gets the subtotal for this item (unitPrice × quantity).
     *
     * @return The subtotal.
     */
    public BigDecimal getSubtotal() {
        return this.subtotal;
    }

    /**
     * Calculates and sets the subtotal based on the current quantity and unit price.
     * The subtotal is calculated as (unitPrice + totalModifiers) * quantity.
     */
    private void calculateSubtotal() {
        BigDecimal totalModifiers = BigDecimal.ZERO;
        for (ProductModifier modifier : this.modifiers) {
            totalModifiers = totalModifiers.add(modifier.getPriceModifier());
        }

        if (this.quantity != null && this.unitPrice != null) {
            this.subtotal = this.unitPrice.add(totalModifiers).multiply(BigDecimal.valueOf(this.quantity));
        }
    }

    public List<ProductModifier> getModifiers() {
        return this.modifiers;
    }
}
