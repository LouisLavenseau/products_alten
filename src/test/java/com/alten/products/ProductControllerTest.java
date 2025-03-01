package com.alten.products;

import com.alten.products.domain.InventoryStatus;
import com.alten.products.domain.Product;
import com.alten.products.domain.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProductControllerTest extends IntegrationTest {

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void cleanProducts() {
        productRepository.deleteAll();
    }

    @Test
    public void getShouldReturnProduct() {
        Product product = new Product(
                "P12345",
                "Product B",
                "Description of Product B",
                "http://example.com/product-b.jpg",
                "Category 2",
                100.50f,
                10,
                "REF12345",
                4321,
                InventoryStatus.LOWSTOCK,
                4.5f
        );

        productRepository.save(product);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/products/" + product.getId())
                .then()
                .statusCode(200)
                .body("code", equalTo("P12345"))
                .body("name", equalTo("Product B"))
                .body("price", equalTo(100.50f))
                .body("quantity", equalTo(10))
                .body("inventoryStatus", equalTo("LOWSTOCK"))
                .body("description", equalTo("Description of Product B"))
                .body("image", equalTo("http://example.com/product-b.jpg"))
                .body("category", equalTo("Category 2"))
                .body("internalReference", equalTo("REF12345"))
                .body("shellId", equalTo(4321))
                .body("rating", equalTo(4.5f));
    }

    @Test
    public void listShouldReturnSortedListByCreatedAtOfProducts() {
        Product product1 = new Product(
                "P1",
                 "The product 1",
                "Description of Product 1",
                "http://example.com/product-1.jpg",
                "Category 25",
                60.50f,
                2,
                "REFP1",
                23422,
                InventoryStatus.INSTOCK,
                10f
        );

        Product product2 = new Product(
                "P2",
                "My product 2",
                "Description of Product 2",
                "http://example.com/product-2.jpg",
                "Category 34",
                41f,
                3,
                "REFP2",
                98578,
                InventoryStatus.OUTOFSTOCK,
                9.5f
        );

        productRepository.save(product1);
        productRepository.save(product2);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body("size()", equalTo(2))
                .body("[0].id", equalTo(product2.getId()))
                .body("[1].id", equalTo(product1.getId()));
    }

    @Test
    public void listShouldReturnProductOutputsList() {
        Product product = new Product(
                "P1",
                "Product 1",
                "Description of Product 1",
                "http://example.com/product-1.jpg",
                "Category 25",
                60.50f,
                2,
                "REFP1",
                23422,
                InventoryStatus.INSTOCK,
                10f
        );

        productRepository.save(product);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body("[0].id", equalTo(product.getId()))
                .body("[0].code", equalTo("P1"))
                .body("[0].name", equalTo("Product 1"))
                .body("[0].description", equalTo("Description of Product 1"))
                .body("[0].image", equalTo("http://example.com/product-1.jpg"))
                .body("[0].category", equalTo("Category 25"))
                .body("[0].price", equalTo(60.50f))
                .body("[0].quantity", equalTo(2))
                .body("[0].internalReference", equalTo("REFP1"))
                .body("[0].shellId", equalTo(23422))
                .body("[0].inventoryStatus", equalTo("INSTOCK"))
                .body("[0].rating", equalTo(10f));
    }

    @Test
    public void createShouldReturnCreatedProduct() {
        Map<String, Object> productMap = new HashMap<>();
        productMap.put("code", "P12345");
        productMap.put("name", "Product A");
        productMap.put("description", "Description of Product A");
        productMap.put("image", "http://example.com/product-a.jpg");
        productMap.put("category", "Category 1");
        productMap.put("price", 100.50);
        productMap.put("quantity", 10);
        productMap.put("internalReference", "REF12345");
        productMap.put("shellId", 4321);
        productMap.put("inventoryStatus", "INSTOCK");
        productMap.put("rating", 4.5);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload;
        try {
            jsonPayload = objectMapper.writeValueAsString(productMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("parsing into json with jackson does not work", e);
        }

        given()
                .contentType(ContentType.JSON)
                .body(jsonPayload)
                .when()
                .post("/products")
                .then()
                .statusCode(200)
                .body("code", equalTo("P12345"))
                .body("name", equalTo("Product A"))
                .body("description", equalTo("Description of Product A"))
                .body("image", equalTo("http://example.com/product-a.jpg"))
                .body("category", equalTo("Category 1"))
                .body("price", equalTo(100.50f))
                .body("quantity", equalTo(10))
                .body("internalReference", equalTo("REF12345"))
                .body("shellId", equalTo(4321))
                .body("inventoryStatus", equalTo("INSTOCK"))
                .body("rating", equalTo(4.5f));
    }

    @Test
    public void patchShouldModifyProductWithNonNullFields() {
        Product product = new Product(
                "P12345",
                "Product B",
                "Description of Product B",
                "http://example.com/product-b.jpg",
                "Category 2",
                100.50f,
                10,
                "REF12345",
                4321,
                InventoryStatus.LOWSTOCK,
                4.5f
        );

        productRepository.save(product);

        Map<String, Object> productMap = new HashMap<>();
        productMap.put("code", "T9087");
        productMap.put("description", "New description");
        productMap.put("price", 198);
        productMap.put("quantity", 3);
        productMap.put("inventoryStatus", "INSTOCK");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload;
        try {
            jsonPayload = objectMapper.writeValueAsString(productMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("parsing into json with jackson does not work", e);
        }

        given()
                .contentType(ContentType.JSON)
                .body(jsonPayload)
                .when()
                .patch("/products/" + product.getId())
                .then()
                .statusCode(200)
                .body("code", equalTo("T9087"))
                .body("description", equalTo("New description"))
                .body("price", equalTo(198.0f))
                .body("quantity", equalTo(3))
                .body("inventoryStatus", equalTo("INSTOCK"));
    }

    @Test
    public void deleteShouldRemoveProduct() {
        Product product = new Product(
                "P12345",
                "Product B",
                "Description of Product B",
                "http://example.com/product-b.jpg",
                "Category 2",
                100.50f,
                10,
                "REF12345",
                4321,
                InventoryStatus.LOWSTOCK,
                4.5f
        );

        productRepository.save(product);

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/products/" + product.getId())
                .then()
                .statusCode(200);

        assertFalse(productRepository.existsById(product.getId()));
    }
}
