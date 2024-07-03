package org.example.tests.api;

import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JavaHttpClientFixture extends LanguageTestBase {

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

}
