package org.example.tests.api;

import org.example.models.ErrorResponse;
import org.example.models.language.LanguageResponse;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("regression")
@Tag("api-test")
@Tag("java-api")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JavaHttpClientApiTest extends JavaHttpClientFixture {

    private static final String LANGUAGE = "Indonesian";
    private static final String LANGUAGE_UPDATE = "Suomi";
    private static final String NEW_LANGUAGE_JSON = STR."""
            {
            "name": "\{LANGUAGE}"
            }
            """;


    @Test
    @Order(1)
    void shouldCreateLanguage() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest httpRequest = create(NEW_LANGUAGE_JSON);
        HttpResponse<String> send = send(httpRequest);
        LanguageResponse dto = transform(send.body());
        assertEquals(201, send.statusCode());
        assertEquals(LANGUAGE, dto.getName());
        saveId(dto);
    }

    @Test
    @Order(2)
    void shouldReadLanguage() throws URISyntaxException, IOException, InterruptedException {
        HttpResponse<String> send = send(read(id));
        LanguageResponse dto = transform(send.body());
        assertEquals(200, send.statusCode());
        assertEquals(LANGUAGE, dto.getName());
        saveId(dto);
    }

    @Test
    @Order(3)
    void shouldUpdateLanguage() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest httpRequest = update(NEW_LANGUAGE_JSON.replaceAll(LANGUAGE, LANGUAGE_UPDATE), id);
        HttpResponse<String> send = send(httpRequest);
        LanguageResponse dto = transform(send.body());
        assertEquals(200, send.statusCode());
        assertEquals(LANGUAGE_UPDATE, dto.getName());
        saveId(dto);
    }

    @Test
    @Order(4)
    void shouldDeleteLanguage() throws URISyntaxException, IOException, InterruptedException {
        HttpResponse<String> send = send(delete(id));
        assertEquals(204, send.statusCode());
    }

    @Test
    @Order(5)
    void whenIdIsNotFound_shouldGet404() throws URISyntaxException, IOException, InterruptedException {
        HttpResponse<String> send = send(read(id));
        assertEquals(404, send.statusCode());
        ErrorResponse response = convert(send.body());
        assertEquals("Bad Request", response.getTitle());
        assertEquals(STR."Cannot find [language] with [\{id}]", response.getDetail());
        assertEquals("NOT_FOUND", response.getStatus());
    }
}
