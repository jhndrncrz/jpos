CREATE TABLE `Inventory` (
    item_id INT AUTO_INCREMENT PRIMARY KEY,

    item_name VARCHAR(255) NOT NULL,
    item_type ENUM('flavor', 'add-on', 'accessory', 'others') NOT NULL,
    base_price DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL,
    floor_amount INT NOT NULL,
    roof_amount INT NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO `Inventory` (item_name, item_type, base_price, stock, floor_amount, roof_amount) VALUES
("Ube Powder", "flavor", 50, 14, 10, 30),
("Pearls", "add-on", 40, 23, 20, 50),
("Straw Bag", "accessory", 30, 5, 3, 7),
("Cup Bag", "accessory", 30, 5, 3, 7),
("Strawberry Powder", "flavor", 50, 14, 10, 30),
("Oreo", "add-on", 40, 23, 20, 50);
