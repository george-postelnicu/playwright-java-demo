package org.example.tests.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RestAssuredFixture extends LanguageTestBase {
    @BeforeAll
    void setup() {
        RestAssured.baseURI = HOST_URI;
        RestAssured.port = Integer.parseInt(HOST_PORT);
        RestAssured.basePath = HOST_RESOURCE;
    }
}
