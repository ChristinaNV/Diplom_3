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

        loginUser(mainPage, loginPage, user.getEmail(), user.getPassword());
    }

    @Test
    @DisplayName("Логин через кнопку 'Личный Кабинет'")
    public void testLoginViaPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        createUserAndGetAccessToken();
        navigateToLoginPageViaPersonalAccount(mainPage, loginPage);
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue("Пользователь должен быть залогинен", mainPage.isUserLoggedIn());
    }

    @Test
    @DisplayName("Логин через форму регистрации")
    public void testLoginViaRegistrationForm() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);

        createUserAndGetAccessToken();

        navigateToRegistrationPage(mainPage, loginPage, registrationPage);
        registrationPage.clickLoginLink();

        assertTrue("Страница логина должна отображаться", loginPage.isLoginPageDisplayed());
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue("Пользователь должен быть залогинен", mainPage.isUserLoggedIn());
    }

    @Test
    @DisplayName("Логин через форму восстановления пароля")
    public void testLoginViaPasswordRecoveryForm() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);

        createUserAndGetAccessToken();

        navigateToLoginPageViaPersonalAccount(mainPage, loginPage);
        loginPage.clickRecoverPasswordLink();

        assertTrue("Страница восстановления пароля должна отображаться",
                passwordRecoveryPage.isPasswordRecoveryPageDisplayed());

        passwordRecoveryPage.clickLoginLink();

        assertTrue("Страница логина должна отображаться", loginPage.isLoginPageDisplayed());
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue("Пользователь должен быть залогинен", mainPage.isUserLoggedIn());
    }
}
