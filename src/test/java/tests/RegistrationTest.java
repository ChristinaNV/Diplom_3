package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;

import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void testSuccessfulRegistration() {
        MainPage mainPage = new MainPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickLoginAccountButton();
        loginPage.clickRegisterLink();

        registrationPage.register(user.getName(), user.getEmail(), user.getPassword());

        assertTrue("После успешной регистрации должна отображаться страница входа",
                loginPage.isLoginPageDisplayed());
    }

    @Test
    @DisplayName("Ошибка при регистрации с некорректным паролем")
    public void testRegistrationWithInvalidPassword() {
        MainPage mainPage = new MainPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickLoginAccountButton();
        loginPage.clickRegisterLink();

        // Пытаемся зарегистрироваться с коротким паролем
        registrationPage.register(user.getName(), user.getEmail(), "123");

        assertTrue("Должна отображаться ошибка для короткого пароля",
                registrationPage.isPasswordErrorDisplayed());
    }
}
