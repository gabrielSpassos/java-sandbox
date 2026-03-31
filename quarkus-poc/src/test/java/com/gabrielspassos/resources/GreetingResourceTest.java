package com.gabrielspassos.resources;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class GreetingResourceTest {

    @Test
    void shouldGreet() {
        given()
            .when()
                .get("/v1/greet/Gabriel")
            .then()
                .statusCode(200)
                .body("message", is("Hello Gabriel from Quarkus"));
    }

}