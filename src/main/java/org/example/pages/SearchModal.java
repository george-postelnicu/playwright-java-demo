package org.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SearchModal extends PageBase {

    public final Locator text;
    public final Locator search;
    public final Locator close;
    public SearchModal(Page page) {
        super(page);
        text = page.locator("//*[@id='search-modal']//input");
        close = page.locator("//*[@id='search-modal']//button[contains(@class, 'close')]");
        search = page.locator("//*[@id='search-modal']//button[@type='submit']");
    }

    public SearchPage search(String toSearch) {
        this.text.fill(toSearch);
        this.search.click();
        return new SearchPage(page);
    }

    public void close() {
        this.close.click();
        this.close.waitFor(elementToBeHidden());
    }

    public boolean isModalHidden() {
        return this.text.isHidden() && close.isHidden() && this.search.isHidden();
    }
}
