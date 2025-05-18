CREATE TABLE `Order` (
    `order_id` INT PRIMARY KEY AUTO_INCREMENT,

    `employee_id` INT,
    
    `total_amount` DECIMAL(10, 2) NOT NULL,
    `payment_method` VARCHAR(50),
    `status` ENUM('pending', 'completed', 'cancelled') DEFAULT 'pending',
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (`employee_id`) REFERENCES `Employee`(`employee_id`) ON DELETE CASCADE
);

CREATE TABLE `OrderItem` (
    `order_item_id` INT PRIMARY KEY AUTO_INCREMENT,

    `order_id` INT,
    `product_id` INT,

    `quantity` INT NOT NULL,
    `unit_price` DECIMAL(10, 2) NOT NULL,
    `subtotal` DECIMAL(10, 2) NOT NULL,

    FOREIGN KEY (`order_id`) REFERENCES `Order`(`order_id`) ON DELETE CASCADE,
    FOREIGN KEY (`product_id`) REFERENCES `Product`(`product_id`) ON DELETE CASCADE
);

CREATE TABLE `ModifierInOrderItem` (
    `modifier_in_order_item` INT PRIMARY KEY AUTO_INCREMENT,

    `modifier_id` INT,
    `order_item_id` INT,

    FOREIGN KEY (`modifier_id`) REFERENCES `ProductModifier`(`modifier_id`) ON DELETE CASCADE,
    FOREIGN KEY (`order_item_id`) REFERENCES `OrderItem`(`order_item_id`) ON DELETE CASCADE
)