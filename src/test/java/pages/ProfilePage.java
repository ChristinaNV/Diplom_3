package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProfilePage extends BasePage {

    // Locators
    @FindBy(how = How.XPATH, using = "//a[text()='Профиль']")
    private WebElement profileTab;

    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    private WebElement logoutButton;

    @FindBy(how = How.XPATH, using = "//p[text()='Конструктор']")
    private WebElement constructorLink;

    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private WebElement logo;

    @FindBy(how = How.XPATH, using = "//a[text()='История заказов']")
    private WebElement orderHistoryTab;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать на кнопку 'Выйти'")
    public void clickLogoutButton() {
        logoutButton.click();
        waitForPageLoad();
    }

    @Step("Нажать на 'Конструктор'")
    public void clickConstructorLink() {
        constructorLink.click();
        waitForPageLoad();
    }

    @Step("Нажать на логотип")
    public void clickLogo() {
        logo.click();
        waitForPageLoad();
    }

    @Step("Проверка отображения страницы профиля")
    public boolean isProfilePageDisplayed() {
        return profileTab.isDisplayed();
    }

}
