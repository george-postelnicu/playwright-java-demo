package org.example.tests.api;

import com.google.gson.Gson;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.example.models.language.LanguageResponseDto;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaywrightApiTest extends PlaywrightApiFixtures {
    private static final String LANGUAGE = "Russian";
    private static final String LANGUAGE_UPDATE = "Austrian";
    private static final String NEW_LANGUAGE_JSON = STR."""
            {
            "name": "\{LANGUAGE}"
            }
            """;
    private static long id;

    @Test
    @Order(1)
    void createLanguage() {
        System.out.println("1 test!");
        APIResponse apiResponse = request.post("/languages",
                RequestOptions.create().setData(NEW_LANGUAGE_JSON));
        assertEquals(201, apiResponse.status());
        saveId(apiResponse);
    }

    @Test
    @Order(2)
    void readLanguage() {
        System.out.println("2 test!");
        APIResponse apiResponse = request.get(STR."/languages/\{id}");
        assertEquals(200, apiResponse.status());
        saveId(apiResponse);
    }

    @Test
    @Order(3)
    void updateLanguage() {
        System.out.println("3 test!");
        APIResponse apiResponse = request.put(STR."/languages/\{id}",
                RequestOptions.create().setData(NEW_LANGUAGE_JSON.replaceAll(LANGUAGE, LANGUAGE_UPDATE)));
        assertEquals(200, apiResponse.status());
        saveId(apiResponse);
    }

    @Test
    @Order(4)
    void deleteLanguage() {
        System.out.println("4 test!");
        APIResponse apiResponse = request.delete(STR."/languages/\{id}");
        assertEquals(204, apiResponse.status());
    }

    private static void saveId(APIResponse apiResponse) {
        LanguageResponseDto dto = new Gson().fromJson(apiResponse.text(), LanguageResponseDto.class);
        id = dto.getId();
    }
}
