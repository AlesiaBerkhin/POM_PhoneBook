package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.AddNewContactScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig {

    @Test
    public void loginPositive(){
        Assert.assertTrue(new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .fillEmail("katy@mail.ru")
                .fillPassword("Kk12345!")
                .submitLogin()
                .isContactListActivityPresent()
        );
    }

    @Test
    public void loginPositiveModel(){
        Assert.assertTrue(new SplashScreen(driver)
                .gotoAuthenticationScreen()
                        .login(
                                Auth.builder()
                                .email("katy@mail.ru")
                                .password("Kk12345!")
                                .build()
                        )
                .isContactListActivityPresent()
        );
    }

    @Test
    public void loginWrongEmail(){
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .fillEmail("katymail.ru")
                .fillPassword("Kk12345!")
                .submitLoginNegative()
                .isErrorMessageContainsText("incorrect").closeErrorTextMessage();

    }

    @Test
    public void loginWrongPassword(){
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .loginNegative(Auth.builder()
                        .email("katy@mail.ru")
                        .password("Kk12345")
                        .build())
                .isErrorMessageContainsTextInAlert("incorrect");
    }


    @AfterMethod
    public void postCondition(){
    new ContactListScreen(driver).logout();
    new SplashScreen(driver);
    }

}
