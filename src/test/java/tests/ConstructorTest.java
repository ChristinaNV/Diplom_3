package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.ConstructorPage;
import pages.MainPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    public void testNavigateToBunsSection() {
        MainPage mainPage = new MainPage(driver);
        ConstructorPage constructorPage = new ConstructorPage(driver);

        // Явно ждем загрузки главной страницы
        assertTrue("Главная страница должна загрузиться",
                mainPage.isMainPageLoaded());
        // Переходим на другую секцию и обратно, чтобы проверить переключение
        constructorPage.clickSaucesSection();
        constructorPage.clickBunsSection();

        assertTrue("Раздел 'Булки' должен быть активен",
                constructorPage.isBunsSectionActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    public void testNavigateToSaucesSection() {
        MainPage mainPage = new MainPage(driver);
        ConstructorPage constructorPage = new ConstructorPage(driver);

        assertTrue("Главная страница должна загрузиться",
                mainPage.isMainPageLoaded());
        constructorPage.clickSaucesSection();

        assertTrue("Раздел 'Соусы' должен быть активен",
                constructorPage.isSaucesSectionActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    public void testNavigateToFillingsSection() {
        MainPage mainPage = new MainPage(driver);
        ConstructorPage constructorPage = new ConstructorPage(driver);

        assertTrue("Главная страница должна загрузиться",
                mainPage.isMainPageLoaded());
        constructorPage.clickFillingsSection();

        assertTrue("Раздел 'Начинки' должен быть активен",
                constructorPage.isFillingsSectionActive());
    }

}
