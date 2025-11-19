package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//input[@name='name']")
    private WebElement emailInput;

    @FindBy(how = How.XPATH, using = "//input[@name='Пароль']")
    private WebElement passwordInput;

    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    private WebElement loginButton;

    @FindBy(how = How.XPATH, using = "//a[text()='Зарегистрироваться']")
    private WebElement registerLink;

    @FindBy(how = How.XPATH, using = "//a[text()='Восстановить пароль']")
    private WebElement recoverPasswordLink;

    @FindBy(how = How.XPATH, using = "//h2[text()='Вход']")
    private WebElement loginHeader;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод email: {email}")
    public void setEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    @Step("Ввод пароля: {password}")
    public void setPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    @Step("Нажатие кнопки 'Войти'")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
        waitForPageLoad();
    }

    @Step("Нажатие ссылки 'Зарегистрироваться'")
    public void clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink));
        registerLink.click();
        waitForPageLoad();
    }

    @Step("Нажатие ссылки 'Восстановить пароль'")
    public void clickRecoverPasswordLink() {
        wait.until(ExpectedConditions.elementToBeClickable(recoverPasswordLink));
        recoverPasswordLink.click();
        waitForPageLoad();
    }

    @Step("Логин с email: {email} и паролем: {password}")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }

    @Step("Проверка отображения страницы логина")
    public boolean isLoginPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loginHeader));
            return loginHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForLoginPageLoad() {
        wait.until(ExpectedConditions.visibilityOf(loginHeader));
    }

}
