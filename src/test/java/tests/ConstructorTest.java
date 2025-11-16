package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.ConstructorPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    public void testNavigateToBunsSection() {
        ConstructorPage constructorPage = new ConstructorPage(driver);

        // Переходим на другую секцию и обратно, чтобы проверить переключение
        constructorPage.clickSaucesSection();
        constructorPage.clickBunsSection();

        assertTrue("Раздел 'Булки' должен быть активен",
                constructorPage.isBunsSectionActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    public void testNavigateToSaucesSection() {
        ConstructorPage constructorPage = new ConstructorPage(driver);

        constructorPage.clickSaucesSection();

        assertTrue("Раздел 'Соусы' должен быть активен",
                constructorPage.isSaucesSectionActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    public void testNavigateToFillingsSection() {
        ConstructorPage constructorPage = new ConstructorPage(driver);

        constructorPage.clickFillingsSection();

        assertTrue("Раздел 'Начинки' должен быть активен",
                constructorPage.isFillingsSectionActive());
    }

}
