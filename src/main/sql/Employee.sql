CREATE TABLE `Employee` (
    `employee_id` INT PRIMARY KEY AUTO_INCREMENT,

    `username` VARCHAR(255) UNIQUE NOT NULL,
    `password_hash` VARCHAR(255) NOT NULL,

    `first_name` VARCHAR(255),
    `last_name` VARCHAR(255),
    `role` ENUM('admin', 'employee') NOT NULL DEFAULT 'employee',
    `salary` DECIMAL(10, 2) DEFAULT 20000.00,

    `email` VARCHAR(255) UNIQUE,
    `phone_number` VARCHAR(20),

    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


-- CREATE TABLE `EmployeeSession` (
--     `session_id` VARCHAR(255) PRIMARY KEY,

--     `employee_id` INT NOT NULL,
--     `is_expired` BOOLEAN NOT NULL DEFAULT FALSE,
--     `expires_at` TIMESTAMP NOT NULL,

--     `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     FOREIGN KEY (`employee_id`) REFERENCES `Employee` (`employee_id`) ON DELETE CASCADE
-- );

INSERT INTO `Employee` (`employee_id`, `username`, `password_hash`, `first_name`, `last_name`, `role`, `salary`, `email`, `phone_number`) VALUES
(1, 'jamescarillo', '70F844CC96ECEB9908FDA901D9D93829CCB61A9307BF6355A8ABF309214E115F', 'James', 'Carillo', 'admin', 20000, 'carillo.jamesemanuel@ue.edu.ph', '123-456-7890'),
(2, 'apollocastillo', 'BCCEF73FBF7E7949CB8F7FA674EB4B59443FC3781DF3CFE9EAEA101ED2DF3DEE', 'Apollo', 'Castillo', 'admin', 20000, 'castillo.apollomarco@ue.edu.ph', '456-123-7890'),
(3, 'elaizacastillo', '1B2795B38FEFB5CA59F2C693D580B5F00169135838ADE58F2082E4838CFF3EC5', 'Elaiza', 'Castillo', 'admin', 20000, 'castillo.elaiza@ue.edu.ph', '789-123-4560'),
(4, 'adriancruz', '07DC96AA6D2EC367A890F6FC6FB410C7D73DF76C21851F6B7E28EC1924776CFB', 'Adrian', 'Cruz', 'admin', 20000, 'cruz.johnadrian@ue.edu.ph', '456-789-1230'),
(5, 'allencruz', 'A8969D32F43B2C10AE054FCA4A5C41032145062E5269AFACD790F46F0F4B17FD', 'Allen', 'Cruz', 'admin', 20000, 'cruz.allendane@ue.edu.ph', '789-123-4560');
