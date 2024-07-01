package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.example.pages.LucaNetHomePage;

import java.nio.file.Paths;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class App {
    public static final String STATE = "playwright/.auth/state.json";
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            Page page = browser.newPage();
            LucaNetHomePage homePage = new LucaNetHomePage(page);
            homePage.navigate();
            homePage.acceptAllCookies();
            page.context().storageState(new BrowserContext.StorageStateOptions()
                    .setPath(Paths.get(STATE)));
        }
    }
}
