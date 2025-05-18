package models.product;

import java.math.BigDecimal;

/**
 * Represents a modifier that can be applied to a product.
 * This includes information about the modifier itself, its type, name, and how
 * it affects the price.
 */
public class ProductModifier {
    private Integer modifierId;

    private Integer modifierTypeId;
    
    private String name;
    private BigDecimal priceModifier;

    /**
     * Default constructor for ProductModifier.
     */
    public ProductModifier() {
    }

    /**
     * Constructs a new ProductModifier with the specified details.
     *
     * @param modifierId     The unique identifier for the product modifier.
     * @param modifierTypeId The identifier for the type of this modifier.
     * @param name           The name of the product modifier.
     * @param priceModifier  The amount to modify the base price of a product by.
     */
    public ProductModifier(Integer modifierId, Integer modifierTypeId, String name, BigDecimal priceModifier) {
        setModifierId(modifierId);
        setModifierTypeId(modifierTypeId);
        setName(name);
        setPriceModifier(priceModifier);
    }

    /**
     * Gets the unique identifier for the product modifier.
     *
     * @return The modifier ID.
     */
    public Integer getModifierId() {
        return this.modifierId;
    }

    /**
     * Sets the unique identifier for the product modifier.
     *
     * @param modifierId The modifier ID to set. Must be positive if not null.
     * @throws IllegalArgumentException if the modifier type is null or not positive.
     */
    public void setModifierId(Integer modifierId) {
        if (modifierId != null && modifierId <= 0) {
            throw new IllegalArgumentException("Modifier ID must be positive.");
        }
        this.modifierId = modifierId;
    }

    /**
     * Gets the identifier for the type of this modifier.
     *
     * @return The modifier type ID.
     */
    public Integer getModifierTypeId() {
        return this.modifierTypeId;
    }

    /**
     * Sets the identifier for the type of this modifier.
     *
     * @param modifierTypeId The modifier type ID to set. Must be positive if not null.
     * @throws IllegalArgumentException if the modifier type is null or not positive.
     */
    public void setModifierTypeId(Integer modifierTypeId) {
        if (modifierTypeId != null && modifierTypeId <= 0) {
            throw new IllegalArgumentException("Modifier type ID must be positive.");
        }
        this.modifierTypeId = modifierTypeId;
    }

    /**
     * Gets the name of the product modifier.
     *
     * @return The modifier name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the product modifier.
     *
     * @param name The modifier name to set. Cannot be null or empty.
     * @throws IllegalArgumentException if the name is null or empty.
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Modifier name cannot be null or empty.");
        }

        this.name = name.trim();
    }

    /**
     * Gets the amount to modify the base price of a product by.
     *
     * @return The price modifier.
     */
    public BigDecimal getPriceModifier() {
        return this.priceModifier;
    }

    /**
     * Sets the amount to modify the base price of a product by.
     *
     * @param priceModifier The price modifier to set. Must not be null.
     * @throws IllegalArgumentException if the price modifier is null.
     */
    public void setPriceModifier(BigDecimal priceModifier) {
        if (priceModifier == null) {
            throw new IllegalArgumentException("Price modifier cannot be null.");
        }
        this.priceModifier = priceModifier;
    }
}
