package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConstructorPage extends BasePage {

    // Locators - используем более точные локаторы
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'tab_tab__1SPyG')]//span[text()='Булки']")
    private WebElement bunsTab;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'tab_tab__1SPyG')]//span[text()='Соусы']")
    private WebElement saucesTab;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'tab_tab__1SPyG')]//span[text()='Начинки']")
    private WebElement fillingsTab;

    @FindBy(how = How.XPATH, using = "//h2[text()='Булки']")
    private WebElement bunsHeader;

    @FindBy(how = How.XPATH, using = "//h2[text()='Соусы']")
    private WebElement saucesHeader;

    @FindBy(how = How.XPATH, using = "//h2[text()='Начинки']")
    private WebElement fillingsHeader;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'tab_tab__1SPyG') and contains(@class, 'tab_tab_type_current__2BEPc')]")
    private WebElement currentActiveTab;

    public ConstructorPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать на раздел 'Булки'")
    public void clickBunsSection() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab));
        // Используем JavaScript клик для обхода проблемы с перекрытием
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", bunsTab);
        waitForPageLoad();
    }

    @Step("Нажать на раздел 'Соусы'")
    public void clickSaucesSection() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", saucesTab);
        waitForPageLoad();
    }

    @Step("Нажать на раздел 'Начинки'")
    public void clickFillingsSection() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", fillingsTab);
        waitForPageLoad();
    }

    @Step("Проверка что раздел 'Булки' активен")
    public boolean isBunsSectionActive() {
        wait.until(ExpectedConditions.visibilityOf(bunsHeader));
        return bunsHeader.isDisplayed();
    }

    @Step("Проверка что раздел 'Соусы' активен")
    public boolean isSaucesSectionActive() {
        wait.until(ExpectedConditions.visibilityOf(saucesHeader));
        return saucesHeader.isDisplayed();
    }

    @Step("Проверка что раздел 'Начинки' активен")
    public boolean isFillingsSectionActive() {
        wait.until(ExpectedConditions.visibilityOf(fillingsHeader));
        return fillingsHeader.isDisplayed();
    }

    @Step("Получение текста текущей активной вкладки")
    public String getCurrentActiveTabText() {
        return currentActiveTab.getText();
    }

}
