package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.Auth;
import models.Contact;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AddNewContactScreen extends BaseScreen{

    public AddNewContactScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(id="com.sheygam.contactapp:id/inputName")
    MobileElement nameEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputLastName")
    MobileElement lastNameEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputEmail")
    MobileElement emailEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputPhone")
    MobileElement phoneEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputAddress")
    MobileElement addressEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputDesc")
    MobileElement descriptionEditText;
    @FindBy(id="com.sheygam.contactapp:id/createBtn")
    MobileElement createButton;

    @FindBy(xpath = "//*[@resource-id='android:id/message']")
    MobileElement errorTextView;

    @FindBy(xpath = "//*[@resource-id='android:id/button1']")
    MobileElement okButton;

    public AddNewContactScreen fillContactForm(Contact contact){
        waitElement(nameEditText, 5);
        type(nameEditText, contact.getName());
        type(lastNameEditText, contact.getLastName());
        type(emailEditText, contact.getEmail());
        type(phoneEditText, contact.getPhone());
        type(addressEditText, contact.getAddress());
        type(descriptionEditText, contact.getDescription());
        return this;
    }

    public ContactListScreen submitContactForm() {
        createButton.click();
        return new ContactListScreen(driver);
    }

    public AddNewContactScreen submitLoginNegative(){
        createButton.click();
        return this;
    }

    public AddNewContactScreen closeErrorTextMessage(){
        okButton.click();
        pause(3000);
        return this;
    }

    public AddNewContactScreen isErrorMessageContainsText(String text){
        Assert.assertTrue(errorTextView.getText().contains(text));
        return this; //chtobi nikyda ne yxodit s ekrana
    }
    public AddNewContactScreen isErrorMessageContainsTextInAlert(String text){
        Alert alert = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains(text));
        alert.accept();
        return this;
    }

}



