package tests;

import config.AppiumConfig;
import org.testng.annotations.Test;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig {

    @Test
    public void loginPositive(){
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .fillEmail("katy@mail.ru")
                .fillPassword("Kk12345!")
                .submitLogin();
    }

}
