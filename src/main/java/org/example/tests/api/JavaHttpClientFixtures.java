package org.example.tests.api;

import com.google.gson.Gson;
import com.microsoft.playwright.APIResponse;
import org.example.models.language.LanguageResponseDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JavaHttpClientFixtures {

    HttpResponse<String> send(HttpRequest request) throws IOException, InterruptedException {
        return HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());
    }

    HttpRequest create(String json) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri( new URI("http://localhost:8080/languages"))
                .headers("Content-Type", "application/json;charset=UTF-8", "Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
    }

    HttpRequest read(long id) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri( new URI(STR."http://localhost:8080/languages/\{id}"))
                .headers("Content-Type", "application/json;charset=UTF-8", "Accept", "application/json")
                .GET()
                .build();
    }

    HttpRequest update(String json, long id) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri( new URI(STR."http://localhost:8080/languages/\{id}"))
                .headers("Content-Type", "application/json;charset=UTF-8", "Accept", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();
    }

    HttpRequest delete(long id) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri( new URI(STR."http://localhost:8080/languages/\{id}"))
                .headers("Content-Type", "application/json;charset=UTF-8", "Accept", "application/json")
                .DELETE()
                .build();
    }

    LanguageResponseDto transform(HttpResponse<String> response) {
        return new Gson().fromJson(response.body(), LanguageResponseDto.class);
    }
}
