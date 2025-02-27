package com.alten.products.domain;

import com.alten.products.utils.SecureRandomIDGenerator;

import java.time.Instant;

public class Product {
        private long id;
        private String code;
        private String name;
        private String description;
        private String image;
        private String category;
        private float price;
        private int quantity;
        private String internalReference;
        private long shellId;
        private InventoryStatus inventoryStatus;
        private float rating;
        private float createdAt;
        private float updatedAt;

        public Product(
                String code,
                String name,
                String description,
                String image,
                String category,
                float price,
                int quantity,
                String internalReference,
                long shellId,
                InventoryStatus inventoryStatus,
                float rating
        ) {
                this.id = SecureRandomIDGenerator.generateID();
                this.code = code;
                this.name = name;
                this.description = description;
                this.image = image;
                this.category = category;
                this.price = price;
                this.quantity = quantity;
                this.internalReference = internalReference;
                this.shellId = shellId;
                this.inventoryStatus = inventoryStatus;
                this.rating = rating;
                this.createdAt = Instant.now().getEpochSecond();
                this.updatedAt = this.createdAt;
        }

        public long getId() { return id; }
        public String getCode() { return code; }
        public String getName() { return name; }
        public String getDescription() { return description; }
        public String getImage() { return image; }
        public String getCategory() { return category; }
        public float getPrice() { return price; }
        public int getQuantity() { return quantity; }
        public String getInternalReference() { return internalReference; }
        public long getShellId() { return shellId; }
        public InventoryStatus getInventoryStatus() { return inventoryStatus; }
        public float getRating() { return rating; }
        public float getCreatedAt() { return createdAt; }
        public float getUpdatedAt() { return updatedAt; }

}

