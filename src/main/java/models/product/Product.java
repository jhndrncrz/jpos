package models.product;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a product in the point of sale system.
 */
public class Product {
    private Integer productId;

    private String name;
    private String description;
    private Integer stock;
    private Integer threshold;
    private BigDecimal basePrice;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    private List<ProductModifierType> modifierTypes;

    /**
     * Default constructor.
     */
    public Product() {
        this.modifierTypes = new ArrayList<ProductModifierType>();
    }

    /**
     * Constructs a new Product with the specified details.
     *
     * @param productId   The unique identifier for the product.
     * @param name        The name of the product.
     * @param description The description of the product.
     * @param stock       The current stock level of the product.
     * @param limit       The maximum stock level for the product.
     * @param basePrice   The base price of the product.
     * @param createdAt   The timestamp when the product was created.
     */
    public Product(Integer productId, String name, String description, Integer stock, Integer limit, BigDecimal basePrice, Timestamp createdAt) {
        this.setProductId(productId);
        this.setName(name);
        this.setDescription(description);
        this.setStock(stock);
        this.setThreshold(limit);
        this.setBasePrice(basePrice);
        this.setCreatedAt(createdAt);
        this.modifierTypes = new ArrayList<ProductModifierType>();
    }

    /**
     * Gets the unique identifier for the product.
     *
     * @return The product ID.
     */
    public Integer getProductId() {
        return this.productId;
    }

    /**
     * Sets the unique identifier for the product.
     *
     * @param productId The product ID to set. Must be positive if not null.
     */
    public void setProductId(Integer productId) {
        if (productId != null && productId <= 0) {
            throw new IllegalArgumentException("Product ID must be positive.");
        }

        this.productId = productId;
    }

    /**
     * Gets the name of the product.
     *
     * @return The product name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name The product name to set. Cannot be null or empty.
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty.");
        }
        
        this.name = name.trim();
    }

    /**
     * Gets the description of the product.
     *
     * @return The product description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the product.
     *
     * @param description The product description to set. Null is allowed but empty string will be trimmed.
     */
    public void setDescription(String description) {
        this.description = (description != null) ? description.trim() : null;
    }

    /**
     * Gets the current stock level of the product.
     *
     * @return The current stock level.
     */
    public Integer getStock() {
        return this.stock;
    }

    /**
     * Sets the current stock level of the product.
     *
     * @param stock The stock level to set. Must be non-negative.
     * @throws IllegalArgumentException 
     */
    public void setStock(Integer stock) {
        if (stock == null) {
            throw new IllegalArgumentException("Stock cannot be null.");
        }

        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative.");
        }

        this.stock = stock;
    }

    /**
     * Gets the maximum stock level for the product.
     *
     * @return The maximum stock level.
     */
    public Integer getThreshold() {
        return this.threshold;
    }

    /**
     * Sets the maximum stock level for the product.
     *
     * @param threshold The maximum stock level to set. Must be non-negative.
     * @throws IllegalArgumentException 
     */
    public void setThreshold(Integer threshold) {
        if (threshold == null) {
            throw new IllegalArgumentException("Limit cannot be null.");
        }

        if (threshold < 0) {
            throw new IllegalArgumentException("Limit cannot be negative.");
        }

        this.threshold = threshold;
    }

    /**
     * Gets the base price of the product.
     *
     * @return The base price.
     */
    public BigDecimal getBasePrice() {
        return this.basePrice;
    }

    /**
     * Sets the base price of the product.
     *
     * @param basePrice The base price to set. Must be non-null and non-negative.
     * @throws IllegalArgumentException 
     */
    public void setBasePrice(BigDecimal basePrice) {
        if (basePrice == null) {
            throw new IllegalArgumentException("Base price cannot be null.");
        }

        if (basePrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Base price cannot be negative.");
        }

        this.basePrice = basePrice;
    }

    /**
     * Gets the timestamp when the product was created.
     *
     * @return The creation timestamp.
     */
    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Sets the timestamp when the product was created.
     *
     * @param createdAt The creation timestamp to set. Can be null.
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the timestamp when the product was last updated.
     *
     * @return The last update timestamp.
     */
    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    /**
     * Sets the timestamp when the product was last updated.
     *
     * @param updatedAt The last update timestamp to set. Can be null.
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Gets the list of modifier types associated with the product.
     *
     * @return The list of modifier types.
     */
    public List<ProductModifierType> getModifierTypes() {
        return this.modifierTypes;
    }
}
