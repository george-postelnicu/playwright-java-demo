package org.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.example.models.ContactUsForm;

import java.util.Objects;

public class ContactUsPage extends PageBase {
    public ContactUsPage(Page page) {
        super(page);
    }

    public void init() {
        page.locator("//input[@name='firstname']").waitFor(elementToBeVisible());
    }

    public String getH1() {
        return page.getByRole(AriaRole.HEADING).textContent().trim();
    }

    public void fill(ContactUsForm form) {
        if (Objects.nonNull(form.firstname())) {
            page.locator("//input[@name='firstname']").fill(form.firstname());
        }
        if (Objects.nonNull(form.lastname())) {
            page.locator("//input[@name='lastname']").fill(form.lastname());
        }
        if (Objects.nonNull(form.email())) {
            page.locator("//input[@name='email']").fill(form.email());
        }
        if (Objects.nonNull(form.company())) {
            page.locator("//input[@name='company']").fill(form.company());
        }
        if (Objects.nonNull(form.jobTitle())) {
            page.locator("//input[@name='jobtitle']").fill(form.jobTitle());
        }
        if (Objects.nonNull(form.phone())) {
            page.locator("//input[@name='phone']").fill(form.phone());
        }
        if (Objects.nonNull(form.country())) {
            page.locator("//select[@name='country']").selectOption(form.country());
        }
        if (Objects.nonNull(form.contactTopic())) {
            page.locator("//select[@name='contact_topic']").selectOption(form.contactTopic());
        }
        if (Objects.nonNull(form.message())) {
            page.locator("//textarea[@name='situation__c']").fill(form.message());
        }
    }

    public void submit() {
        page.locator("//input[@value='Send Request']").click();
    }

    public Locator getFormErrorLabel() {
        return page.locator("//div[contains(@class, 'hs_error_rollup')]//label");
    }

//"//div[contains(@class, 'hs_error_rollup')]//label"
// input value   Send Request
}
