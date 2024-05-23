package org.example.tests.api;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import org.example.models.language.LanguageResponseDto;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestAssuredApiTest {
    private static final String LANGUAGE = "Georgian";
    private static final String LANGUAGE_UPDATE = "Romanian";
    private static final String NEW_LANGUAGE_JSON = STR."""
            {
            "name": "\{LANGUAGE}"
            }
            """;
    private static long id;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    @Order(1)
    void createLanguage() {
        System.out.println("First test!");
        LanguageResponseDto dto = given()
                .header(new Header("Content-Type", "application/json"))
                .body(NEW_LANGUAGE_JSON)
                .when()
                .post("/languages")
                .then()
                .statusCode(201)
                .body("name", is(LANGUAGE))
                .and()
                .extract().as(LanguageResponseDto.class);

        id = dto.getId();
    }

    @Test
    @Order(2)
    void readLanguage() {
        System.out.println("Second test!");
        LanguageResponseDto dto = given()
                .header(new Header("Content-Type", "application/json"))
                .when()
                .get("/languages/{id}", id)
                .then()
                .statusCode(200)
                .body("id", equalTo(Long.valueOf(id).intValue()),
                        "name", equalTo(LANGUAGE))
                .and()
                .extract().as(LanguageResponseDto.class);

        id = dto.getId();
    }

    @Test
    @Order(3)
    void updateLanguage() {
        System.out.println("Third test!");
        LanguageResponseDto dto = given()
                .header(new Header("Content-Type", "application/json"))
                .body(NEW_LANGUAGE_JSON.replaceAll(LANGUAGE, LANGUAGE_UPDATE))
                .when()
                .put("/languages/{id}", id)
                .then()
                .statusCode(200)
                .body("name", is(LANGUAGE_UPDATE))
                .and()
                .extract().as(LanguageResponseDto.class);

        id = dto.getId();
    }

    @Test
    @Order(4)
    void deleteLanguage() {
        System.out.println("Fourth test!");
        given()
                .header(new Header("Content-Type", "application/json"))
                .when()
                .delete("/languages/{id}", id)
                .then()
                .statusCode(204);
    }
}
