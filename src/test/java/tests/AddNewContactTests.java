package tests;

import config.AppiumConfig;
import models.Contact;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import screens.AddNewContactScreen;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

import java.util.Random;

public class AddNewContactTests extends AppiumConfig {

    int i;

    @BeforeSuite
    public void precondition(){
        i = new Random().nextInt(1000) + 1000;
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .fillEmail("katy@mail.ru")
                .fillPassword("Kk12345!")
                .submitLogin();
    }

    @Test(invocationCount = 3)
    public void addNewContactPositive(){
        Contact contact = Contact.builder()
                .name("AddNewContact_" + i)
                .lastName("Positive")
                .email("katy_" + i + "@mail.ru")
                .phone("1234567" + i)
                .address("Netanya")
                .description("New contact" + i)
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAdded(contact);
    }

    @Test
    public void addNewContactNegativeName(){
        Contact contact = Contact.builder()
                .name("")  // ili mozno prosto zakomentit ety stroky
                .lastName("Negative")
                .email("katy_" + i + "@mail.ru")
                .phone("1234567" + i)
                .address("Netanya")
                .description("New contact" + i)
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitLoginNegative()
                .isErrorMessageContainsText("blank")
                .closeErrorTextMessage();
    }

    @Test
    public void addNewContactNegativeEmptyName(){ // metod Andrey
        Contact contact = Contact.builder()
         //       .name("")  // ili mozno prosto zakomentit ety stroky
                .lastName("Negative")
                .email("katy_" + i + "@mail.ru")
                .phone("1234567" + i)
                .address("Netanya")
                .description("New contact" + i)
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitLoginNegative()
                .isErrorMessageContainsTextInAlert("blank");
    }

    @Test
    public void addNewContactNegativeLastName(){
        Contact contact = Contact.builder()
                .name("Contact_negative_" + i)
                .lastName("  ")
                .email("katy_" + i + "@mail.ru")
                .phone("1234567" + i)
                .address("Netanya")
                .description("New contact" + i)
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitLoginNegative()
                .isErrorMessageContainsTextInAlert("blank");
    }

    @Test
    public void addNewContactNegativeEmail(){
        Contact contact = Contact.builder()
                .name("Contact_negative_" + i)
                .lastName("Negative")
                .email("katy_" + i + "mail.ru")
                .phone("1234567" + i)
                .address("Netanya")
                .description("New contact" + i)
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitLoginNegative()
                .isErrorMessageContainsTextInAlert("must be a well-formed");
    }

    @Test
    public void addNewContactNegativePhone(){
        Contact contact = Contact.builder()
                .name("Contact_negative_" + i)
                .lastName("Negative")
                .email("katy_" + i + "@mail.ru")
                .phone("12")
                .address("Netanya")
                .description("New contact" + i)
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitLoginNegative()
                .isErrorMessageContainsTextInAlert("Phone number must contain");
    }

    @Test
    public void addNewContactNegativeAddress(){
        Contact contact = Contact.builder()
                .name("Contact_negative_" + i)
                .lastName("Negative")
                .email("katy_" + i + "@mail.ru")
                .phone("1234567" + i)
                .address(" ")
                .description("New contact" + i)
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitLoginNegative()
                .isErrorMessageContainsTextInAlert("blank");
    }

    @AfterMethod
    public void postCondition(){
            new ContactListScreen(driver).logoutAdd();
            new SplashScreen(driver);

    }

}
