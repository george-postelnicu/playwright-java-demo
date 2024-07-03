package org.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitUntilState;

public class LucanetHomePage extends PageBase {

    private final CookiesModal cookiesModal;
    private final TopNav topNav;
    private final MainNav mainNav;

    private final Locator headline;

    public LucanetHomePage(Page page) {
        super(page);
        this.cookiesModal = new CookiesModal(page);
        this.topNav = new TopNav(page);
        this.mainNav = new MainNav(page);
        this.headline = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setLevel(1));
    }

    public void navigate() {
        page.navigate("/", new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
    }

    public void navigate(String url) {
        page.navigate(url, new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
    }

    public void waitToLoad() {
        this.headline.waitFor(elementToBeVisible());
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
