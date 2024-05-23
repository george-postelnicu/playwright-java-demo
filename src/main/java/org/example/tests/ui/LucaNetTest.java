package org.example.tests.ui;

import com.github.javafaker.Faker;
import org.example.models.ContactUsForm;
import org.example.pages.ContactUsPage;
import org.example.pages.LucaNetHomePage;
import org.example.pages.SearchModal;
import org.example.pages.SearchPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class LucaNetTest extends UiTestFixtures {

    LucaNetHomePage homePage;

    @BeforeEach
    public void acceptCookies() {
        homePage = new LucaNetHomePage(page);
        homePage.navigate();
        homePage.acceptAllCookies();
    }

    @Test
    public void shouldSeeNavBar() {
        assertTrue(homePage.isTopNavVisible());
    }

    @Test
    public void shouldNavigateToSearchPage() {
        SearchPage searchPage = homePage.search("abc");
        searchPage.init();
        assertTrue(searchPage.isSearchResultsVisible());
    }

    @Test
    public void shouldCloseSearchModal() {
        SearchModal searchModal = homePage.openSearch();
        searchModal.close();
        assertTrue(searchModal.isModalHidden());
    }

    @Test
    public void shouldFindResults() {
        SearchPage searchPage = homePage.search("IFRS");
        searchPage.init();
        assertEquals("30 results", searchPage.getNumberOfResults());
        assertEquals(10, searchPage.getDisplayedResults());
    }

    @Test
    public void shouldNavigateViaNavToContactUsPage() {
        homePage.navigate("About Us", "Contact Us");
        ContactUsPage contactUsPage = new ContactUsPage(page);
        contactUsPage.init();
        assertEquals("Contact us", contactUsPage.getH1());
    }

    @Test
    public void shouldFillFormWithMissingNumber() {
        homePage.navigate("About Us", "Contact Us");
        ContactUsPage contactUsPage = new ContactUsPage(page);
        contactUsPage.init();
        contactUsPage.fill(createForm());
        contactUsPage.submit();
        assertThat(contactUsPage.getFormErrorLabel()).hasText("Please complete all required fields.");
    }

    private ContactUsForm createForm() {
        Faker faker = new Faker();
        return new ContactUsForm(faker.name().firstName(), faker.name().lastName(), faker.company().name(),
                faker.internet().emailAddress(), faker.company().profession(), null, faker.country().name(),
                "Appointment request", "I would like a meeting ASAP!");
    }
}
