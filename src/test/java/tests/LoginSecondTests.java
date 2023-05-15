package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;

public class LoginSecondTests extends AppiumConfig {

    @Test
    public void loginSuccess() {
        new AuthenticationScreen(driver)
                .fillEmail("pups+1@gmail.com")
                .fillPassword("Ff12345$")
                .submitLogin()
                .isAccountOpened()
                .logout();
    }

    @Test
    public void loginSuccessModels() {
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("pups+1@gmail.com").password("Ff12345$").build())
                .submitLogin()
                .isAccountOpened()
                .logout();
    }

    @Test
    public void loginWrongEmail() {
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("pups+1gmail.com").password("Ff12345$").build())
                .submitLoginNegative()
                .isErrorMessageContainsText("Login or Password incorrect");
    }

    @Test
    public void loginWrongPassword() {
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("pups+1@gmail.com").password("f12345$").build())
                .submitLoginNegative()
                .isErrorMessageContainsText("Login or Password incorrect");
    }


}
