package com.alten.products;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class IntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setPort() {
        RestAssured.baseURI = "http://localhost:" + port;
    }
}
