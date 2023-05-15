package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class AddNewContactsTests extends AppiumConfig {

    @BeforeClass
    public void preCondition() {
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("pups+1@gmail.com").password("Ff12345$").build())
                .submitLogin();
    }

    @Test
    public void createNewContactSuccess() {
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("aSimon")
                .lastName("aWow" + i)
                .email("s.wow" + i + "@gmail.com")
                .phone("254675" + i)
                .address("address")
                .description("The best friend")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(), contact.getLastName());
    }

    @Test
    public void createContactWithEmptyName(){
        Contact contact = Contact.builder()
                .lastName("Wow")
                .email("s.wow@gmail.com")
                .phone("2545687675")
                .address("NY")
                .description("Empty name")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorContainsText("{name=must not be blank}");
    }

    @Test
    public void createNewContactSuccessReq() {

    }

    @AfterClass
    public void postCondition() {
        new ContactListScreen(driver).logout();
    }

}
