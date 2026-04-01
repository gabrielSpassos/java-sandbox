package com.gabrielspassos.resources;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class AdminResourceTest {

    @Test
    void shouldBeHealthy() {
        given()
                .when()
                .get("/q/health")
                .then()
                .statusCode(200)
                .body("status", is("UP"));
    }

    @Test
    void shouldBeLive() {
        given()
                .when()
                .get("/q/health/live")
                .then()
                .statusCode(200)
                .body("status", is("UP"));
    }

    @Test
    void shouldBeReady() {
        given()
                .when()
                .get("/q/health/ready")
                .then()
                .statusCode(200)
                .body("status", is("UP"));
    }

    @Test
    void shouldContainDatabaseCheck() {
        given()
                .when()
                .get("/q/health/ready")
                .then()
                .statusCode(200)
                .body("checks[0].name", is("database"))
                .body("checks[0].status", is("UP"));
    }

}
