package org.example.tests.ui;

import com.github.javafaker.Faker;
import org.example.models.ContactUsForm;
import org.example.pages.ContactUsPage;
import org.example.pages.LucaNetHomePage;
import org.example.pages.SearchModal;
import org.example.pages.SearchPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LucaNetTest extends UiTestFixtures {

    LucaNetHomePage homePage;

    @BeforeEach
    void acceptCookies() {
        homePage = new LucaNetHomePage(page);
        homePage.navigate();
        homePage.acceptAllCookies();
    }

    @Test
    void shouldSeeNavBar() {
        assertTrue(homePage.isTopNavVisible());
    }

    @Test
    void shouldNavigateToSearchPage() {
        SearchPage searchPage = homePage.search("abc");
        searchPage.init();
        assertTrue(searchPage.isSearchResultsVisible());
    }

    @Test
    void shouldCloseSearchModal() {
        SearchModal searchModal = homePage.openSearch();
        searchModal.close();
        assertTrue(searchModal.isModalHidden());
    }

    @Test
    void shouldCloseSearchModalWithPlaywrightAssertions() {
        SearchModal searchModal = homePage.openSearch();
        searchModal.close();
        assertThat(searchModal.close).isHidden();
        assertThat(searchModal.text).isHidden();
        assertThat(searchModal.search).isHidden();
    }

    @Test
    void shouldFindResults() {
        SearchPage searchPage = homePage.search("IFRS");
        searchPage.init();
        assertEquals("38 results", searchPage.getNumberOfResults());
        assertEquals(10, searchPage.getDisplayedResults());
    }

    @Test
    void shouldFindResultsWithPlaywrightAssertions() {
        SearchPage searchPage = homePage.search("IFRS");
        assertThat(searchPage.self).isVisible();
        assertThat(searchPage.results).hasText("38 results");
        assertThat(searchPage.resultsPerPage).hasCount(10);
    }

    @Test
    void shouldNavigateViaNavToContactUsPage() {
        homePage.navigate("About Us", "Contact Us");
        ContactUsPage contactUsPage = new ContactUsPage(page);
        contactUsPage.init();
        assertEquals("Contact us", contactUsPage.getH1());
    }

    @Test
    void shouldNavigateViaNavToContactUsPageWithPlaywrightAssertions() {
        homePage.navigate("About Us", "Contact Us");
        ContactUsPage contactUsPage = new ContactUsPage(page);
        assertThat(contactUsPage.firstname).isVisible();
        assertThat(contactUsPage.title).hasText("Contact us");
    }

    @Test
    void shouldFillFormWithMissingNumber() {
        homePage.navigate("About Us", "Contact Us");
        ContactUsPage contactUsPage = new ContactUsPage(page);
        contactUsPage.init();
        contactUsPage.fill(createForm());
        contactUsPage.submit();
        assertEquals( "Please complete all required fields.", contactUsPage.getFormError());
        assertTrue(contactUsPage.getPhoneClasses().contains("invalid error"));
        assertEquals(1, contactUsPage.getErrorCount());
    }

    @Test
    void shouldFillFormWithMissingNumberAndPlaywrightAssertions() {
        homePage.navigate("About Us", "Contact Us");
        ContactUsPage contactUsPage = new ContactUsPage(page);
        assertThat(contactUsPage.firstname).isVisible();
        contactUsPage.fill(createForm());
        contactUsPage.submit();
        assertThat(contactUsPage.formError).hasText("Please complete all required fields.");
        assertThat(contactUsPage.phone).hasClass(Pattern.compile("invalid error"));
        assertThat(contactUsPage.fieldLabels).hasCount(1);
    }

    private ContactUsForm createForm() {
        Faker faker = new Faker(Locale.of("de"));
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        String email = STR."\{firstname.toLowerCase()}.\{lastname.toLowerCase()}@abc.com";
        return new ContactUsForm(firstname, lastname, faker.company().name(),
                email, faker.company().profession(), null, faker.country().name(),
                "Appointment request", "I would like a meeting ASAP!");
    }
}
