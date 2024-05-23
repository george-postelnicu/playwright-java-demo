package org.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class MainNav extends PageBase {
    public MainNav(Page page) {
        super(page);
    }
    public void navigate(String mainCategory, String secondCategory) {

        Locator mainNav = page
                .locator("//a[contains(@class, 'main-navigation__item')]")
                .filter(new Locator.FilterOptions().setHasText(mainCategory));
        mainNav.hover();

        Locator secondNav = page
                .locator("//ul[contains(@class, 'main-navigation__sub-listitem')]//a")
                .filter(new Locator.FilterOptions().setHasText(secondCategory));

        secondNav.click();
    }

}
