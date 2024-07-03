package org.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class TopNav extends PageBase {
    private final Locator language;
    private final Locator search;

    public TopNav(Page page) {
        super(page);
        this.language = page.locator("//div[@class='lnet-top-menu']//button[contains(@class, 'language-switcher-button')]");
        this.search = page.locator("//div[@class='lnet-top-menu']//button[contains(@class, 'btn btn--search')]");
    }

    public boolean isTopNavVisible() {
        return search.isVisible() && language.isVisible();
    }

    public SearchPage search(String toSearch) {
        this.search.click();
        SearchModal searchModal = new SearchModal(page);
        return searchModal.search(toSearch);
    }

    public SearchModal openSearch() {
        this.search.click();
        return new SearchModal(page);
    }
}
