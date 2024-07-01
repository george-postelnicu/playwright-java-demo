package org.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SearchPage extends PageBase {

    public final Locator self;
    public final Locator results;
    public final Locator resultsPerPage;

    public SearchPage(Page page) {
        super(page);
        self = page.locator("//div[contains(@class, 'tx-kesearch-pi1')]//*[@id='form_kesearch_pi1']");
        results = page.locator("//*[@id='kesearch_num_results']");
        resultsPerPage = page.locator("//*[@id='kesearch_results']//a");
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
