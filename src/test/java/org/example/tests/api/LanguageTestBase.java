package org.example.tests.api;

import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;
import org.example.models.ErrorResponse;
import org.example.models.language.LanguageResponse;

public abstract class LanguageTestBase {
    private final Dotenv dotenv = Dotenv.load();
    protected final String ACCEPT = "Accept";
    protected final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=UTF-8";
    protected final String APPLICATION_JSON = "application/json";
    protected final String CONTENT_TYPE = "Content-Type";
    protected final String BASE_URL = dotenv.get("API_BASE_URL", "http://localhost:9000/api");
    protected final String HOST_URI = dotenv.get("REST_URI", "http://localhost");
    protected final String HOST_PORT = dotenv.get("REST_PORT", "9000");
    protected final String HOST_RESOURCE = dotenv.get("REST_RESOURCE", "/api");
    protected static long id;
    protected LanguageResponse transform(String body) {
        return new Gson().fromJson(body, LanguageResponse.class);
    }

    protected ErrorResponse convert(String body) {
        return new Gson().fromJson(body, ErrorResponse.class);
    }

    protected void saveId(LanguageResponse response) {
        id = response.getId();
    }
}
