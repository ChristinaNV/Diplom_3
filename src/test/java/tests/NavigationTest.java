package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;

import static org.junit.Assert.assertTrue;

public class NavigationTest extends BaseTest {

    @Test
    @DisplayName("Переход в личный кабинет")
    public void testNavigateToPersonalAccount() {
        // Создаем пользователя и логинимся
        createUserAndGetAccessToken();

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        navigateToProfilePage(mainPage, loginPage, profilePage, user.getEmail(), user.getPassword());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор через ссылку 'Конструктор'")
    public void testNavigateFromProfileToConstructorViaLink() {
        // Создаем пользователя и логинимся
        createUserAndGetAccessToken();

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        navigateToProfilePage(mainPage, loginPage, profilePage, user.getEmail(), user.getPassword());
        profilePage.clickConstructorLink();

        assertTrue("После клика на ссылку 'Конструктор' должна отображаться главная страница",
                mainPage.isMainPageLoaded());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор через логотип")
    public void testNavigateFromProfileToConstructorViaLogo() {
        // Создаем пользователя и логинимся
        createUserAndGetAccessToken();

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        navigateToProfilePage(mainPage, loginPage, profilePage, user.getEmail(), user.getPassword());
        profilePage.clickLogo();

        assertTrue("После клика на логотип должна отображаться главная страница",
                mainPage.isMainPageLoaded());
    }
}
