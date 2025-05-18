CREATE TABLE `Product` (
    `product_id` INT PRIMARY KEY AUTO_INCREMENT,

    `name` VARCHAR(255) NOT NULL,
    `description` TEXT,
    `stock` INT NOT NULL,
    `limit` INT NOT NULL,
    `base_price` DECIMAL(10, 2) NOT NULL, 

    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `ProductModifierType` (
    `modifier_type_id` INT PRIMARY KEY AUTO_INCREMENT,

    `product_id` INT,

    `name` VARCHAR(255) UNIQUE NOT NULL,

    FOREIGN KEY (`product_id`) REFERENCES `Product`(`product_id`) ON DELETE CASCADE
);

CREATE TABLE `ProductModifier` (
    `modifier_id` INT PRIMARY KEY AUTO_INCREMENT,

    `modifier_type_id` INT,

    `name` VARCHAR(255) NOT NULL,
    `price_modifier` DECIMAL(10, 2) DEFAULT 0.00,

    FOREIGN KEY (`modifier_type_id`) REFERENCES `ProductModifierType`(`modifier_type_id`) ON DELETE CASCADE
);