package org.example.tests.api;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.example.models.ErrorResponse;
import org.example.models.language.LanguageResponse;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Tag("regression")
@Tag("api-test")
@Tag("playwright-api")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PlaywrightApiTest extends PlaywrightApiFixture {
    private static final String LANGUAGE = "Russian";
    private static final String LANGUAGE_UPDATE = "Austrian";
    private static final String NEW_LANGUAGE_JSON = STR."""
            {
            "name": "\{LANGUAGE}"
            }
            """;

    @Test
    @Order(1)
    void createLanguage() {
        APIResponse apiResponse = request.post("./languages",
                RequestOptions.create().setData(NEW_LANGUAGE_JSON));
        assertEquals(201, apiResponse.status());
        assertThat(apiResponse).isOK();
        LanguageResponse dto = transform(apiResponse.text());
        assertEquals(LANGUAGE, dto.getName());
        saveId(dto);
    }

    @Test
    @Order(2)
    void shouldReadLanguage() {
        APIResponse apiResponse = request.get(STR."./languages/\{id}");
        assertEquals(200, apiResponse.status());
        LanguageResponse dto = transform(apiResponse.text());
        assertEquals(LANGUAGE, dto.getName());
        assertEquals(id, dto.getId());
        saveId(dto);
    }

    @Test
    @Order(3)
    void updateLanguage() {
        APIResponse apiResponse = request.put(STR."./languages/\{id}",
                RequestOptions.create().setData(NEW_LANGUAGE_JSON.replaceAll(LANGUAGE, LANGUAGE_UPDATE)));
        assertEquals(200, apiResponse.status());
        LanguageResponse dto = transform(apiResponse.text());
        assertEquals(LANGUAGE_UPDATE, dto.getName());
        saveId(dto);
    }

    @Test
    @Order(4)
    void shouldDeleteLanguage() {
        APIResponse apiResponse = request.delete(STR."./languages/\{id}");
        assertEquals(204, apiResponse.status());
        assertThat(apiResponse).isOK();
    }

    @Test
    @Order(5)
    void whenIdIsNotFound_shouldGet404() {
        APIResponse apiResponse = request.get(STR."./languages/\{id}");
        assertEquals(404, apiResponse.status());
        assertThat(apiResponse).not().isOK();
        ErrorResponse response = convert(apiResponse.text());
        assertEquals("Bad Request", response.getTitle());
        assertEquals(STR."Cannot find [language] with [\{id}]", response.getDetail());
        assertEquals("NOT_FOUND", response.getStatus());
    }

}
