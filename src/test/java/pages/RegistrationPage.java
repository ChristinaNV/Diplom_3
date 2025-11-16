package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//fieldset[1]//input")
    private WebElement nameInput;

    @FindBy(how = How.XPATH, using = "//fieldset[2]//input")
    private WebElement emailInput;

    @FindBy(how = How.XPATH, using = "//input[@type='password']")
    private WebElement passwordInput;

    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    private WebElement registerButton;

    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private WebElement loginLink;

    @FindBy(how = How.CLASS_NAME, using = "input__error")
    private WebElement passwordError;

    @FindBy(how = How.XPATH, using = "//h2[text()='Регистрация']")
    private WebElement registrationHeader;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввести имя: {name}")
    public void setName(String name) {
        wait.until(ExpectedConditions.visibilityOf(nameInput));
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    @Step("Ввести email: {email}")
    public void setEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    @Step("Ввести пароль: {password}")
    public void setPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    @Step("Нажать на кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        registerButton.click();
        waitForPageLoad();
    }

    @Step("Нажать на 'Войти'")
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        loginLink.click();
        waitForPageLoad();
    }

    @Step("Регистрация нового пользователя: {name}/{email}/{password}")
    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    @Step("Проверка отображения ошибки пароля")
    public boolean isPasswordErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(passwordError));
            return passwordError.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверка отображения страницы регистрации")
    public boolean isRegistrationPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(registrationHeader));
            return registrationHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
