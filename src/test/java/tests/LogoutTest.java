package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;

import static org.junit.Assert.assertTrue;

public class LogoutTest extends BaseTest {

    @Test
    @DisplayName("Выход из личного кабинета")
    public void testLogout() {
        // Создаем пользователя и логинимся
        createUserAndGetAccessToken();

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        navigateToProfilePage(mainPage, loginPage, profilePage, user.getEmail(), user.getPassword());
        profilePage.clickLogoutButton();

        assertTrue("После выхода должна отображаться страница логина",
                loginPage.isLoginPageDisplayed());
    }

}
