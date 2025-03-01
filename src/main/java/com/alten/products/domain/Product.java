package com.alten.products.domain;

import com.alten.products.api.products.ProductPatch;
import com.alten.products.utils.SecureRandomIDGenerator;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Map;

@Entity
@Table(name = "products")
public class Product {

        @Id
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

        public Product() {
        }

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

        public void patch(ProductPatch productPatch) {
                if (productPatch.code() != null) {
                        this.code = productPatch.code();
                }
                if (productPatch.name() != null) {
                        this.name = productPatch.name();
                }
                if (productPatch.description() != null) {
                        this.description = productPatch.description();
                }
                if (productPatch.image() != null) {
                        this.image = productPatch.category();
                }
                if (productPatch.price() != null) {
                        this.price = productPatch.price();
                }
                if (productPatch.quantity() != null) {
                        this.quantity = productPatch.quantity();
                }
                if (productPatch.internalReference() != null) {
                        this.internalReference = productPatch.internalReference();
                }
                if (productPatch.shellId() != null) {
                        this.shellId = productPatch.shellId();
                }
                if (productPatch.inventoryStatus() != null) {
                        this.inventoryStatus = productPatch.inventoryStatus();
                }
                if (productPatch.rating() != null) {
                        this.rating = productPatch.rating();
                }
                this.updatedAt = Instant.now().getEpochSecond();
        }

}

