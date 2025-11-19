package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    private WebElement loginAccountButton;

    @FindBy(how = How.XPATH, using = "//p[text()='Личный Кабинет']")
    private WebElement personalAccountButton;

    @FindBy(how = How.XPATH, using = "//h1[text()='Соберите бургер']")
    private WebElement mainHeader;

    @FindBy(how = How.XPATH, using = "//button[contains(text(), 'Оформить заказ')]")
    private WebElement createOrderButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажатие кнопки 'Войти в аккаунт'")
    public void clickLoginAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginAccountButton));
        loginAccountButton.click();
        waitForPageLoad();
    }

    @Step("Нажатие кнопки 'Личный Кабинет'")
    public void clickPersonalAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton));
        personalAccountButton.click();
        waitForPageLoad();
    }

    @Step("Проверка загрузки главной страницы")
    public boolean isMainPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOf(mainHeader));
            return mainHeader.isDisplayed();
        } catch (Exception e) {
            // Дополнительная проверка через URL
            String currentUrl = driver.getCurrentUrl();
            return currentUrl.contains("stellarburgers") &&
                    (currentUrl.endsWith("/") || currentUrl.contains("constructor"));
        }
    }

    @Step("Проверка что пользователь залогинен")
    public boolean isUserLoggedIn() {
        try {
            wait.until(ExpectedConditions.visibilityOf(createOrderButton));
            return createOrderButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForMainPageLoad() {
        wait.until(ExpectedConditions.visibilityOf(mainHeader));
    }

    @Step("Проверка видимости кнопки 'Войти в аккаунт'")
    public boolean isLoginAccountButtonDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loginAccountButton));
            return loginAccountButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверка видимости кнопки 'Личный кабинет'")
    public boolean isPersonalAccountButtonDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(personalAccountButton));
            return personalAccountButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
