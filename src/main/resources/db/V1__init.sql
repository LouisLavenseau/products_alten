CREATE TABLE IF NOT EXISTS products (
    `id` VARCHAR(64) NOT NULL,
    `name` VARCHAR(64) NOT NULL,
    `code` VARCHAR(64) NOT NULL,
    `name` VARCHAR(64) NOT NULL,
    `description` TEXT NOT NULL,
    `image` VARCHAR(2048) NOT NULL,
    `category` VARCHAR(64) NOT NULL,
    `price` FLOAT NOT NULL,
    `quantity` INT NOT NULL,
    `internalReference` VARCHAR(64) NOT NULL,
    `shellId` BIGINT NOT NULL,
    `inventoryStatus` ENUM('INSTOCK', 'LOWSTOCK', 'OUTOFSTOCK') NOT NULL,
    `rating` FLOAT NOT NULL,
    `createdAt` FLOAT NOT NULL,
    `updatedAt` FLOAT NOT NULL
);
