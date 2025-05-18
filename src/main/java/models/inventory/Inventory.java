package models.inventory;


import java.sql.Timestamp;


/**
 * Represents an inventory management in the system.
 */
public class Inventory {
    private Integer itemId;
	private String itemName;
	private String itemType;
	private Float base_price;
	private Integer stock;
	private Float totalPrice;
	private Integer floorAmount;
	private Integer roofAmount;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    /**
     * Default constructor.
     */
    public Inventory() {}

        /**
     
     * @param itemId 
     * @param itemName 
     * @param itemType 
     * @param base_price 
     * @param stock 
     * @param totalPrice 
     * @param floorAmount 
     * @param roofAmount 
     * @param createdAt Timestamp when the record was created
     * @param updatedAt Timestamp when the record was last updated
     */

    public Inventory(Integer id, String in, String it, Float bp, Integer s
                    , Integer fa, Integer ra,
                    Timestamp createdAt, Timestamp updatedAt) {
        this.setItemId(id);
        this.setItemName(in);
        this.setItemType(it);
        this.setBasePrice(bp);
        this.setStock(s);
        this.setTotalPrice(bp, s);
        this.setFloorAmount(fa);
        this.setRoofAmount(ra);
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


    public void setBasePrice(Float base_price) {
        if (base_price == null || base_price <= 0) {
            throw new IllegalArgumentException("base_price cannot be null or empty.");
        }
        this.base_price = base_price;
    }


    public Float getBasePrice() {
        return this.base_price;
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

 
    public void setTotalPrice(Float base_price, Integer stock) {
        if (base_price == null || stock == null) {
            throw new IllegalArgumentException("base_price and stock cannot be null.");
        }
        this.totalPrice = base_price * stock.floatValue();
    }


    public Float getTotalPrice() {
        return this.totalPrice;
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
