package org.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.example.models.ContactUsForm;

import java.util.Objects;

public class ContactUsPage extends PageBase {
    public final Locator formError;
    public final Locator firstname;
    public final Locator phone;
    public final Locator fieldLabels;
    public final Locator title;
    public ContactUsPage(Page page) {
        super(page);
        this.formError = page.locator("//div[contains(@class, 'hs_error_rollup')]//label");
        this.firstname = page.locator("//input[@name='firstname']");
        this.phone = page.locator("//input[@name='phone']");
        this.fieldLabels = page.locator("//label[contains(@class, 'hs-error-msg')]");
        this.title = page.getByRole(AriaRole.HEADING);
    }

    public void init() {
        page.locator("//input[@name='firstname']").waitFor(elementToBeVisible());
    }

    public String getH1() {
        return this.title.textContent().trim();
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
            this.phone.fill(form.phone());
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

    public String getFormError() {
        formError.waitFor(elementToBeVisible());
        return formError.textContent().trim();
    }

    public String getPhoneClasses() {
        return phone.getAttribute("class");
    }

    public int getErrorCount() {
        return fieldLabels.count();
    }

}
