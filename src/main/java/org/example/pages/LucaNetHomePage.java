package org.example.pages;

import com.microsoft.playwright.Page;

public class LucaNetHomePage extends PageBase {

    private final CookiesModal cookiesModal;
    private final TopNav topNav;
    private final MainNav mainNav;

    public LucaNetHomePage(Page page) {
        super(page);
        cookiesModal = new CookiesModal(page);
        topNav = new TopNav(page);
        mainNav = new MainNav(page);
    }

    public void navigate() {
        page.navigate("https://www.lucanet.com/en/");
    }

    public void acceptAllCookies() {
        this.cookiesModal.acceptAllCookies();
    }

    public boolean isTopNavVisible() {
        return this.topNav.isTopNavVisible();
    }

    public SearchPage search(String toSearch) {
        return this.topNav.search(toSearch);
    }

    public SearchModal openSearch() {
        return this.topNav.openSearch();
    }

    public void navigate(String main, String secondary) {
        this.mainNav.navigate(main, secondary);
    }
}
