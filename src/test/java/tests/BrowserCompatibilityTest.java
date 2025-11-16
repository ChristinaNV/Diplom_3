package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;

import static org.junit.Assert.assertTrue;

public class BrowserCompatibilityTest extends BaseTest {

    @Test
    @DisplayName("Проверка работы в выбранном браузере")
    public void testBrowserCompatibility() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        // Создаем пользователя через API
        createUserAndGetAccessToken();

        // Тестируем логин
        mainPage.clickLoginAccountButton();
        loginPage.waitForLoginPageLoad();
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue("Главная страница должна отображаться в браузере: " + getBrowser(),
                mainPage.isMainPageLoaded());

        // Проверяем что пользователь залогинен
        assertTrue("Пользователь должен быть залогинен в браузере: " + getBrowser(),
                mainPage.isUserLoggedIn());

        System.out.println("Тест успешно выполнен в браузере: " + getBrowser());
    }

    @Test
    @DisplayName("Проверка навигации в выбранном браузере")
    public void testNavigationInBrowser() {
        MainPage mainPage = new MainPage(driver);

        // Простая проверка, что главная страница загружается
        assertTrue("Главная страница должна загружаться в браузере: " + getBrowser(),
                mainPage.isMainPageLoaded());

        // Проверяем основные элементы
        assertTrue("Кнопка 'Войти в аккаунт' должна отображаться",
                mainPage.isLoginAccountButtonDisplayed());

        assertTrue("Кнопка 'Личный кабинет' должна отображаться",
                mainPage.isPersonalAccountButtonDisplayed());

        System.out.println("Навигация работает корректно в браузере: " + getBrowser());
    }

}
