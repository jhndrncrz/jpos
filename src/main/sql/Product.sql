CREATE TABLE `Product` (
    `product_id` INT PRIMARY KEY AUTO_INCREMENT,

    `name` VARCHAR(255) NOT NULL,
    `description` TEXT,
    `stock` INT NOT NULL,
    `threshold` INT NOT NULL,
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

(1, 'Size'),
(1, 'Sugar Level'),
(1, 'Addons');

INSERT INTO `ProductModifier` (`modifier_type_id`, `name`, `price_modifier`) VALUES
((SELECT `modifier_type_id` FROM `ProductModifierType` WHERE `name` = 'Size' AND `product_id` = 1), 'Small', 0.00),
((SELECT `modifier_type_id` FROM `ProductModifierType` WHERE `name` = 'Size' AND `product_id` = 1), 'Medium', 0.50),
((SELECT `modifier_type_id` FROM `ProductModifierType` WHERE `name` = 'Size' AND `product_id` = 1), 'Large', 1.00),

((SELECT `modifier_type_id` FROM `ProductModifierType` WHERE `name` = 'Sugar Level' AND `product_id` = 1), '0%', 0.00),
((SELECT `modifier_type_id` FROM `ProductModifierType` WHERE `name` = 'Sugar Level' AND `product_id` = 1), '25%', 0.00),
((SELECT `modifier_type_id` FROM `ProductModifierType` WHERE `name` = 'Sugar Level' AND `product_id` = 1), '50%', 0.00),
((SELECT `modifier_type_id` FROM `ProductModifierType` WHERE `name` = 'Sugar Level' AND `product_id` = 1), '75%', 0.00),
((SELECT `modifier_type_id` FROM `ProductModifierType` WHERE `name` = 'Sugar Level' AND `product_id` = 1), '100%', 0.00),

((SELECT `modifier_type_id` FROM `ProductModifierType` WHERE `name` = 'Addons' AND `product_id` = 1), 'Boba', 0.75),
((SELECT `modifier_type_id` FROM `ProductModifierType` WHERE `name` = 'Addons' AND `product_id` = 1), 'Pudding', 0.75),
((SELECT `modifier_type_id` FROM `ProductModifierType` WHERE `name` = 'Addons' AND `product_id` = 1), 'Aloe', 0.75),
((SELECT `modifier_type_id` FROM `ProductModifierType` WHERE `name` = 'Addons' AND `product_id` = 1), 'Grass Jelly', 0.75);

