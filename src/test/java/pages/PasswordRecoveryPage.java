package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PasswordRecoveryPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private WebElement loginLink;

    @FindBy(how = How.XPATH, using = "//h2[text()='Восстановление пароля']")
    private WebElement recoveryHeader;

    public PasswordRecoveryPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать на ссылку 'Войти'")
    public void clickLoginLink() {
        loginLink.click();
        waitForPageLoad();
    }

    @Step("Проверка отображения страницы восстановления пароля")
    public boolean isPasswordRecoveryPageDisplayed() {
        return recoveryHeader.isDisplayed();
    }

}
