package models.product;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a type of modifier that can be applied to a product.
 */
public class ProductModifierType {
    private Integer modifierTypeId;

    private Integer productId;

    private String name;

    private List<ProductModifier> modifiers;

    /**
     * Default constructor.
     */
    public ProductModifierType() {
    }

    /**
     * Constructs a new ProductModifierType with the specified details.
     *
     * @param modifierTypeId The unique identifier for the modifier type.
     * @param name           The name of the modifier type.
     */
    public ProductModifierType(Integer modifierTypeId, Integer productId, String name) {
        this.setModifierTypeId(modifierTypeId);
        this.setProductId(productId);
        this.setName(name);
        this.modifiers = new ArrayList<ProductModifier>();
    }

    /**
     * Gets the unique identifier for the modifier type.
     *
     * @return The modifier type ID.
     */
    public Integer getModifierTypeId() {
        return this.modifierTypeId;
    }

    /**
     * Sets the unique identifier for the modifier type.
     *
     * @param modifierTypeId The modifier type ID to set.
     * @throws IllegalArgumentException if the ID is not positive.
     */
    public void setModifierTypeId(Integer modifierTypeId) {
        if (modifierTypeId != null && modifierTypeId <= 0) {
            throw new IllegalArgumentException("Modifier type ID must be positive.");
        }
        
        this.modifierTypeId = modifierTypeId;
    }

    /**
     * Gets the product ID associated with the modifier type.
     *
     * @return The product ID.
     */
    public Integer getProductId() {
        return this.productId;
    }

    /**
     * Sets the product ID associated with the modifier type.
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
     * Gets the name of the modifier type.
     *
     * @return The modifier type name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the modifier type.
     *
     * @param name The modifier type name to set.
     * @throws IllegalArgumentException if the name is null or empty.
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Modifier type name cannot be null or empty.");
        }

        this.name = name.trim();
    }

    /**
     * Gets the list of modifiers associated with this type.
     *
     * @return The list of modifiers.
     */
    public List<ProductModifier> getModifiers() {
        return this.modifiers;
    }
}
