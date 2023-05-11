package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class LoginTests extends AppiumConfig {

    @Test
    public void loginSuccess(){
//        boolean result = new SplashScreen(driver)
//            .checkCurrentVersion("Version 1.0.0")
        boolean result = new AuthenticationScreen(driver)
            .fillEmail("pups+1@gmail.com")
            .fillPassword("Ff12345$")
            .submitLogin()
            .isActivityTitleDisplayed("Contact list");

        Assert.assertTrue(result);
    }

    @Test
    public void loginSuccessModel(){
//        boolean result = new SplashScreen(driver)
//                .checkCurrentVersion("Version 1.0.0")
        boolean result = new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("pups+1@gmail.com").password("Ff12345$").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");

        Assert.assertTrue(result);
    }

    @Test
    public void loginSuccessModel2(){
        Assert.assertTrue(new AuthenticationScreen(driver)
            .fillLoginRegistrationForm(Auth.builder().email("pups+1@gmail.com").password("Ff12345$").build())
            .submitLogin()
            .isActivityTitleDisplayed("Contact list"));
    }

    @Test
    public void loginWrongEmail(){
        AuthenticationScreen result = new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("pups+1gmail.com").password("Ff12345$").build())
                .submitLoginNegative()
                .isErrorMessageContainsText("Login or Password incorrect");
    }


    @AfterMethod
    public void posCondition(){
        new ContactListScreen(driver).logout();
    }

}
