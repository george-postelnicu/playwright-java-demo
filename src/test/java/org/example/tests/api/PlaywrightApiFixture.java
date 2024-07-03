package org.example.tests.api;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlaywrightApiFixture extends LanguageTestBase {

    private Playwright playwright;
    protected APIRequestContext request;

    private void createPlaywright() {
        playwright = Playwright.create();
    }

    private void createAPIRequestContext() {
        Map<String, String> headers = new HashMap<>();
        headers.put(ACCEPT, APPLICATION_JSON);
        headers.put(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8);

        request = playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL(STR."\{BASE_URL}/")
                .setExtraHTTPHeaders(headers));
    }

    @BeforeAll
    void beforeAll() {
        createPlaywright();
        createAPIRequestContext();
    }

    private void disposeAPIRequestContext() {
        if (request != null) {
            request.dispose();
            request = null;
        }
    }

    private void closePlaywright() {
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }

    @AfterAll
    void afterAll() {
        disposeAPIRequestContext();
        closePlaywright();
    }
}
