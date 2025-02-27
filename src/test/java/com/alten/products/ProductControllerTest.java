package com.alten.products;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductControllerTest extends IntegrationTest {

    @Test
    public void testCreateProduct() {
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
                .post("/products/here")
                .then()
                .statusCode(200)
                .body("code", equalTo("P12345"))
                .body("name", equalTo("Product A"))
                .body("price", equalTo(100.50f))
                .body("quantity", equalTo(10))
                .body("inventoryStatus", equalTo("INSTOCK"))
                .body("description", equalTo("Description of Product A"))
                .body("image", equalTo("http://example.com/product-a.jpg"))
                .body("category", equalTo("Category 1"))
                .body("internalReference", equalTo("REF12345"))
                .body("shellId", equalTo(4321))
                .body("rating", equalTo(4.5f));
    }
}
