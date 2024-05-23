package org.example.pages;

import com.microsoft.playwright.Page;

public class CookiesModal extends PageBase {

    public CookiesModal(Page page) {
        super(page);
    }

    public void acceptAllCookies() {
        this.waitAndAcceptModal("CybotCookiebotDialog", "CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll");
    }
}
