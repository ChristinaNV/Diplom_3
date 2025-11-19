package tests;

import io.qameta.allure.junit4.DisplayName;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;

import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {

    private User registrationUser;
    private String registeredUserAccessToken;

    @Before
    public void setUpRegistration() {
        // Создаем отдельного пользователя для тестов регистрации
        registrationUser = new User(
                "reguser_" + System.currentTimeMillis() + "@example.com",
                "password123",
                "RegUser"
        );
    }

    @After
    public void tearDownAdditional() {
        // Удаляем пользователя, зарегистрированного через UI
        if (registeredUserAccessToken != null) {
            userClient.deleteUser(registeredUserAccessToken);
        }
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void testSuccessfulRegistration() {
        MainPage mainPage = new MainPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        // Переходим на страницу регистрации
        navigateToRegistrationPage(mainPage, loginPage, registrationPage);

        // Регистрируем нового пользователя
        registrationPage.register(registrationUser.getName(), registrationUser.getEmail(), registrationUser.getPassword());

        // После регистрации должны быть перенаправлены на страницу логина
        waitForLoginPageLoaded(loginPage);
        assertTrue("После успешной регистрации должна отображаться страница входа",
                loginPage.isLoginPageDisplayed());

        // Логинимся с только что созданными данными
        loginPage.login(registrationUser.getEmail(), registrationUser.getPassword());

        // После успешного логина должны оказаться на главной странице
        waitForMainPageLoaded(mainPage);
        assertTrue("Пользователь должен быть залогинен после регистрации",
                mainPage.isUserLoggedIn());

        // Получаем accessToken для последующего удаления пользователя
        registeredUserAccessToken = getAccessTokenForUser(registrationUser.getEmail(), registrationUser.getPassword());
    }

    @Test
    @DisplayName("Ошибка при регистрации с некорректным паролем")
    public void testRegistrationWithInvalidPassword() {
        MainPage mainPage = new MainPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        // Переходим на страницу регистрации
        navigateToRegistrationPage(mainPage, loginPage, registrationPage);

        // Пытаемся зарегистрироваться с коротким паролем
        registrationPage.register(registrationUser.getName(), registrationUser.getEmail(), "123");

        // Должны остаться на странице регистрации с ошибкой
        assertTrue("Должна отображаться ошибка для короткого пароля",
                registrationPage.isPasswordErrorDisplayed());
    }
    }