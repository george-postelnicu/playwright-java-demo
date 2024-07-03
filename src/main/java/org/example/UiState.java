package org.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.github.cdimascio.dotenv.Dotenv;
import org.example.pages.LucanetHomePage;

import java.nio.file.Path;
import java.nio.file.Paths;

public class UiState {
    private static final Dotenv dotenv = Dotenv.load();
    public static final Path STATE = Paths.get("playwright/.auth/state.json");

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create();
             Browser browser = playwright.chromium().launch()) {
            Page page = browser.newPage();
            LucanetHomePage homePage = new LucanetHomePage(page);
            homePage.navigate(dotenv.get("UI_BASE_URL"));
            homePage.acceptAllCookies();
            page.context().storageState(new BrowserContext.StorageStateOptions()
                    .setPath(STATE));
            System.exit(0);
        } catch (Throwable throwable) {
            System.out.println(throwable.getMessage());
            System.exit(1);
        }
    }
}
