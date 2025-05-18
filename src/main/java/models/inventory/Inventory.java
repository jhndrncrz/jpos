package models.inventory;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Represents an inventory management in the system.
 */
public class Inventory {
    private Integer itemId;

    private String itemName;
    private String itemType;
    private BigDecimal basePrice;
    private Integer stock;
    private Integer floorAmount;
    private Integer roofAmount;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    /**
     * Default constructor.
     */
    public Inventory() {
    }

    /**
     * 
     * @param itemId
     * @param itemName
     * @param itemType
     * @param basePrice
     * @param stock
     * @param floorAmount
     * @param roofAmount
     * @param createdAt   Timestamp when the record was created
     * @param updatedAt   Timestamp when the record was last updated
     */

    public Inventory(Integer itemId, String itemName, String itemType, BigDecimal basePrice, Integer stock,
            Integer floorAmount, Integer roofAmount,
            Timestamp createdAt, Timestamp updatedAt) {
        this.setItemId(itemId);
        this.setItemName(itemName);
        this.setItemType(itemType);
        this.setBasePrice(basePrice);
        this.setStock(stock);
        this.setFloorAmount(floorAmount);
        this.setRoofAmount(roofAmount);
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);
    }

    public void setItemId(Integer itemId) {
        if (itemId == null || itemId <= 0) {
            throw new IllegalArgumentException("Item ID cannot be null or <= 0.");
        }
        this.itemId = itemId;
    }

    public Integer getItemId() {
        return this.itemId;
    }

    public void setItemName(String itemName) {
        if (itemName == null || itemName.trim().isEmpty()) {
            throw new IllegalArgumentException("itemName cannot be null or empty.");
        }
        this.itemName = itemName.trim();
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemType(String itemType) {
        if (itemType == null || itemType.trim().isEmpty()) {
            throw new IllegalArgumentException("itemType cannot be null or empty.");
        }
        this.itemType = itemType.trim();
    }

    public String getItemType() {
        return this.itemType;
    }

    public void setBasePrice(BigDecimal basePrice) {
        if (basePrice == null || basePrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("base_price cannot be null or empty.");
        }
        this.basePrice = basePrice;
    }

    public BigDecimal getBasePrice() {
        return this.basePrice;
    }

    public void setStock(Integer stock) {
        if (stock == null || stock < 0) {
            throw new IllegalArgumentException("stock cannot be null or empty.");
        }
        this.stock = stock;
    }

    public Integer getStock() {
        return this.stock;
    }

    public BigDecimal getTotalPrice() {
        return (BigDecimal.valueOf(this.stock).multiply(this.basePrice));
    }

    public void setFloorAmount(Integer floorAmount) {
        if (floorAmount == null || floorAmount < 0) {
            throw new IllegalArgumentException("floorAmount must be non-negative.");
        }
        this.floorAmount = floorAmount;
    }

    public Integer getFloorAmount() {
        return this.floorAmount;
    }

    public void setRoofAmount(Integer roofAmount) {
        if (roofAmount == null || roofAmount < 0) {
            throw new IllegalArgumentException("roofAmount must be non-negative.");
        }
        this.roofAmount = roofAmount;
    }

    public Integer getRoofAmount() {
        return this.roofAmount;
    }

    public void setCreatedAt(Timestamp createdAt) {
        if (createdAt == null) {
            throw new IllegalArgumentException("Created at cannot be null.");
        }
        this.createdAt = createdAt;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        if (updatedAt == null) {
            throw new IllegalArgumentException("Updated at cannot be null.");
        }
        this.updatedAt = updatedAt;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }
}
