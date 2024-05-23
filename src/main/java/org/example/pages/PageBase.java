package org.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class PageBase {
    public static final String FIND_BY_DIV_ID = "//*[@id='%s']";
    protected final Page page;

    public PageBase(Page page) {
        this.page = page;
    }

    public void waitAndAcceptModal(String idModal, String idButton) {
        page.locator(String.format(FIND_BY_DIV_ID, idModal)).waitFor(elementToBeVisible());
        page.locator(String.format(FIND_BY_DIV_ID, idButton)).click();
        page.locator(String.format(FIND_BY_DIV_ID, idModal)).waitFor(elementToBeHidden());
    }

    public Locator.WaitForOptions elementToBeHidden() {
        Locator.WaitForOptions hidden = new Locator.WaitForOptions();
        hidden.setState(WaitForSelectorState.HIDDEN);
        return hidden;
    }

    public Locator.WaitForOptions elementToBeVisible() {
        Locator.WaitForOptions hidden = new Locator.WaitForOptions();
        hidden.setState(WaitForSelectorState.VISIBLE);
        return hidden;
    }
}
