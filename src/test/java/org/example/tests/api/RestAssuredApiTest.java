package org.example.tests.api;

import io.restassured.http.Header;
import org.example.models.language.LanguageResponse;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@Tag("regression")
@Tag("rest-assured-api")
@Tag("api-test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestAssuredApiTest extends RestAssuredFixture {
    private static final String LANGUAGE = "Georgian";
    private static final String LANGUAGE_UPDATE = "Romanian";
    private static final String NEW_LANGUAGE_JSON = STR."""
            {
            "name": "\{LANGUAGE}"
            }
            """;

    @Test
    @Order(1)
    void shouldCreateLanguage() {
        LanguageResponse dto = given()
                .header(new Header(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8))
                .header(new Header(ACCEPT, APPLICATION_JSON))
                .body(NEW_LANGUAGE_JSON)
                .when()
                .post("/languages")
                .then()
                .statusCode(201)
                .body("name", is(LANGUAGE))
                .and()
                .extract().as(LanguageResponse.class);

        saveId(dto);
    }

    @Test
    @Order(2)
    void shouldReadLanguage() {
        LanguageResponse dto = given()
                .header(new Header(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8))
                .header(new Header(ACCEPT, APPLICATION_JSON))
                .when()
                .get("/languages/{id}", id)
                .then()
                .statusCode(200)
                .body("id", equalTo(Long.valueOf(id).intValue()),
                        "name", equalTo(LANGUAGE))
                .and()
                .extract().as(LanguageResponse.class);

        saveId(dto);
    }

    @Test
    @Order(3)
    void shouldUpdateLanguage() {
        LanguageResponse dto = given()
                .header(new Header(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8))
                .header(new Header(ACCEPT, APPLICATION_JSON))
                .body(NEW_LANGUAGE_JSON.replaceAll(LANGUAGE, LANGUAGE_UPDATE))
                .when()
                .put("/languages/{id}", id)
                .then()
                .statusCode(200)
                .body("name", is(LANGUAGE_UPDATE))
                .and()
                .extract().as(LanguageResponse.class);

        saveId(dto);
    }

    @Test
    @Order(4)
    void shouldDeleteLanguage() {
        given()
                .header(new Header(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8))
                .header(new Header(ACCEPT, APPLICATION_JSON))
                .when()
                .delete("/languages/{id}", id)
                .then()
                .statusCode(204);
    }

    @Test
    @Order(5)
    void whenIdIsNotFound_shouldReturn404() {
        given()
                .header(new Header(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8))
                .header(new Header(ACCEPT, APPLICATION_JSON))
                .when()
                .get("/languages/{id}", id)
                .then()
                .statusCode(404)
                .body("title", equalTo("Bad Request"),
                        "detail", equalTo(STR."Cannot find [language] with [\{id}]"),
                        "status", equalTo("NOT_FOUND"));
    }
}
