package org.example.tests.api;

import org.example.models.language.LanguageResponseDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JavaHttpClientApiTest extends JavaHttpClientFixtures {

    private static final String LANGUAGE = "Indonesian";
    private static final String LANGUAGE_UPDATE = "Suomi";
    private static final String NEW_LANGUAGE_JSON = STR."""
            {
            "name": "\{LANGUAGE}"
            }
            """;
    private static long id;


    @Test
    @Order(1)
    void shouldCreateLanguage() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest httpRequest = create(NEW_LANGUAGE_JSON);
        HttpResponse<String> send = send(httpRequest);
        LanguageResponseDto dto = transform(send);
        assertEquals(201, send.statusCode());
        assertEquals(LANGUAGE, dto.getName());
        id = dto.getId();
    }

    @Test
    @Order(2)
    void shouldReadLanguage() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest httpRequest = read(id);
        HttpResponse<String> send = send(httpRequest);
        LanguageResponseDto dto = transform(send);
        assertEquals(200, send.statusCode());
        assertEquals(LANGUAGE, dto.getName());
        id = dto.getId();
    }

    @Test
    @Order(3)
    void shouldUpdateLanguage() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest httpRequest = update(NEW_LANGUAGE_JSON.replaceAll(LANGUAGE, LANGUAGE_UPDATE), id);
        HttpResponse<String> send = send(httpRequest);
        LanguageResponseDto dto = transform(send);
        assertEquals(200, send.statusCode());
        assertEquals(LANGUAGE_UPDATE, dto.getName());
        id = dto.getId();
    }

    @Test
    @Order(4)
    void shouldDeleteLanguage() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest httpRequest = delete(id);
        HttpResponse<String> send = send(httpRequest);
        assertEquals(204, send.statusCode());
    }

    @Test
    @Order(5)
    void whenIdIsNotFound_shouldGet404() throws URISyntaxException, IOException, InterruptedException {
        HttpResponse<String> send = send(read(id));
        System.out.println(send.body());
        assertEquals(404, send.statusCode());
    }
}
