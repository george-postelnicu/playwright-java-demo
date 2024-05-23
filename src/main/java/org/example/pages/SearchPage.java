package org.example.pages;

import com.microsoft.playwright.Page;

public class SearchPage extends PageBase {

    public SearchPage(Page page) {
        super(page);
    }

    public void init() {
        page.locator("//div[contains(@class, 'tx-kesearch-pi1')]//*[@id='form_kesearch_pi1']").waitFor(elementToBeVisible());
    }

    public boolean isSearchResultsVisible() {
        return page.locator("//div[@id='kesearch_results']").isVisible();
    }

    public String getNumberOfResults() {
        return page.locator("//*[@id='kesearch_num_results']").textContent().trim();
    }

    public int getDisplayedResults() {
        return page.locator("//*[@id='kesearch_results']//a").count();
    }
}
