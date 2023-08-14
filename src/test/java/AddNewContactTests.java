import config.AppiumConfig;
import models.Contact;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class AddNewContactTests extends AppiumConfig {

    int i = (int) (System.currentTimeMillis() / 1000) % 3600;

    @BeforeMethod
    public void precondition(){
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .fillEmail("abc_1895@def.com")
                .fillPassword("$Abcdef12345")
                .submitLogin();
    }

    @Test
    public void addNewContactPositive(){
        Contact contact = Contact.builder()
                .name("John_" + i)
                .lastName("Snow")
                .phone("01234578" + i)
                .email("john_" + i + "@mail.com")
                .address("Rehovot")
                .description("Best friend")
                .build();

        Assert.assertTrue(
                new ContactListScreen(driver)
                        .openContactForm()
                        .fillContactForm(contact)
                        .submitContact()
                        .isContactAdded(contact)
        );
    }
}