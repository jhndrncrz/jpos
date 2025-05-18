CREATE TABLE `Customer` (
    `customer_id` INT PRIMARY KEY AUTO_INCREMENT,

    `first_name` VARCHAR(255),
    `last_name` VARCHAR(255),

    `address` VARCHAR(255), 
    `email` VARCHAR(255),
    `phone_number` VARCHAR(20),

    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

