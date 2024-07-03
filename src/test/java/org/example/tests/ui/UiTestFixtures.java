package org.example.tests.ui;

import com.microsoft.playwright.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.example.App.STATE;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UiTestFixtures {
    Playwright playwright;
    Browser browser;
    Dotenv dotenv = Dotenv.load();

    @BeforeAll
    void launchBrowser() {
        boolean isHeadlessMode = Boolean.parseBoolean(dotenv.get("HEADLESS_MODE", "true"));
        playwright = Playwright.create();
        browser = playwright.chromium().launch(isHeadlessMode ? headless() : slowMo());
    }

    private BrowserType.LaunchOptions slowMo() {
        return new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setSlowMo(100);
    }

    private BrowserType.LaunchOptions headless() {
        return new BrowserType.LaunchOptions()
                .setHeadless(true);
    }

    @AfterAll
    void closeBrowser() {
        playwright.close();
    }

    BrowserContext context;
    Page page;

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1920, 1080)
                .setStorageStatePath(Paths.get(STATE)));
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        try {
            Files.write(Paths.get("screenshot.png"), page.screenshot());
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("trace.zip")));
            context.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
