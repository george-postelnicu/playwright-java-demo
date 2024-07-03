package org.example.tests.api;

import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;
import org.example.models.language.LanguageResponseDto;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("java-api")
public class JavaHttpClientFixtures {
    private final Dotenv dotenv = Dotenv.load();
    private final String ACCEPT = "Accept";
    private final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=UTF-8";
    private final String APPLICATION_JSON = "application/json";
    private final String CONTENT_TYPE = "Content-Type";
    private final String BASE_URL = dotenv.get("API_BASE_URL", "http://localhost:9000/api");

    HttpResponse<String> send(HttpRequest request) throws IOException, InterruptedException {
        return HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());
    }

    HttpRequest create(String json) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI(STR."\{BASE_URL}/languages"))
                .headers(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8, ACCEPT, APPLICATION_JSON)
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
    }

    HttpRequest read(long id) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI(STR."\{BASE_URL}/languages/\{id}"))
                .headers(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8, ACCEPT, APPLICATION_JSON)
                .GET()
                .build();
    }

    HttpRequest update(String json, long id) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI(STR."\{BASE_URL}/languages/\{id}"))
                .headers(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8, ACCEPT, APPLICATION_JSON)
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();
    }

    HttpRequest delete(long id) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI(STR."\{BASE_URL}/languages/\{id}"))
                .headers(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8, ACCEPT, APPLICATION_JSON)
                .DELETE()
                .build();
    }

    LanguageResponseDto transform(HttpResponse<String> response) {
        return new Gson().fromJson(response.body(), LanguageResponseDto.class);
    }
}
