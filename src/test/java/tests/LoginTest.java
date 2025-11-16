package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.*;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Логин через кнопку 'Войти в аккаунт' на главной")
    public void testLoginViaAccountButton() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        // Создаем пользователя через API
        createUserAndGetAccessToken();

        mainPage.clickLoginAccountButton();
        loginPage.waitForLoginPageLoad();
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue("Главная страница должна отображаться после успешного логина",
                mainPage.isMainPageLoaded());
    }

    @Test
    @DisplayName("Логин через кнопку 'Личный Кабинет'")
    public void testLoginViaPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        createUserAndGetAccessToken();

        mainPage.clickPersonalAccountButton();
        loginPage.waitForLoginPageLoad();
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue("Главная страница должна отображаться после успешного логина",
                mainPage.isMainPageLoaded());
    }

    @Test
    @DisplayName("Логин через форму регистрации")
    public void testLoginViaRegistrationForm() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);

        createUserAndGetAccessToken();

        mainPage.clickLoginAccountButton();
        loginPage.waitForLoginPageLoad();
        loginPage.clickRegisterLink();
        registrationPage.clickLoginLink();
        loginPage.waitForLoginPageLoad();
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue("Главная страница должна отображаться после успешного логина",
                mainPage.isMainPageLoaded());
    }

    @Test
    @DisplayName("Логин через форму восстановления пароля")
    public void testLoginViaPasswordRecoveryForm() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);

        createUserAndGetAccessToken();

        mainPage.clickPersonalAccountButton();
        loginPage.waitForLoginPageLoad();
        loginPage.clickRecoverPasswordLink();
        passwordRecoveryPage.clickLoginLink();
        loginPage.waitForLoginPageLoad();
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue("Главная страница должна отображаться после успешного логина",
                mainPage.isMainPageLoaded());
    }
}
