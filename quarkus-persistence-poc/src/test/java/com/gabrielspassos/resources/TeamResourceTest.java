package com.gabrielspassos.resources;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class TeamResourceTest {

    @Test
    void shouldCreateAndListTeam() {
        given()
                .contentType("application/json")
                .body("""
                            {
                              "name": "Inter",
                              "city": "Porto Alegre",
                              "titles": 88
                            }
                        """
                ).when()
                    .post("/v1/teams")
                .then()
                    .statusCode(200)
                    .body("name", is("Inter"));

        given()
                .when()
                    .get("/v1/teams")
                .then()
                    .statusCode(200)
                    .body("[0].name", is("Inter"));
    }

    @Test
    void shouldCreateTeamAndUseItsId() {
        Response response = given()
                    .contentType("application/json")
                    .body("""
                                {
                                  "name": "Grêmio",
                                  "city": "Porto Alegre",
                                  "titles": 1
                                }
                            """
                ).when()
                    .post("/v1/teams")
                .then()
                    .statusCode(200)
                    .extract()
                    .response();

        Long id = response.jsonPath().getLong("id");

        given()
            .pathParam("id", id)
        .when()
            .get("/v1/teams/{id}")
        .then()
            .statusCode(200)
            .body("name", is("Grêmio"))
            .body("id", is(id.intValue()));
    }

    @Test
    void shouldDeleteTeam() {
        Response response = given()
                .contentType("application/json")
                .body("""
                            {
                              "name": "Flamengo",
                              "city": "Rio de Janeiro",
                              "titles": 40
                            }
                        """
                ).when()
                .post("/v1/teams")
                .then()
                .statusCode(200)
                .extract()
                .response();

        Long id = response.jsonPath().getLong("id");

        given()
            .pathParam("id", id)
        .when()
            .delete("/v1/teams/{id}")
        .then()
            .statusCode(200)
            .body(is("true"));

        given()
            .pathParam("id", id)
        .when()
            .get("/teams/{id}")
        .then()
            .statusCode(404);
    }

}