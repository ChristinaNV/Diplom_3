package tests;

import clients.UserClient;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;
import pages.RegistrationPage;

import static org.junit.Assert.assertTrue;


public class BaseTest {

    protected WebDriver driver;
    protected UserClient userClient;
    protected User user;
    protected String accessToken;

    private final String browser = System.getProperty("browser", "chrome");

    @Before
    public void setUp() {
        setupDriver();
        driver.manage().window().maximize();
        driver.get("https://stellarburgers.education-services.ru/");

        userClient = new UserClient();

        // Создание тестового пользователя
        user = new User(
                "testuser_" + System.currentTimeMillis() + "@example.com",
                "password123",
                "TestUser"
        );
    }

    private void setupDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        switch (browser.toLowerCase()) {
            case "yandex":
                setupYandexBrowser(options);
                break;
            case "chrome":
            default:
                setupChromeBrowser(options);
                break;
        }

        driver = new ChromeDriver(options);
    }

    private void setupChromeBrowser(ChromeOptions options) {
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        // Дополнительные опции для Chrome
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-extensions");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
    }

    private void setupYandexBrowser(ChromeOptions options) {
        // Пути к Яндекс.Браузеру для разных ОС
        String os = System.getProperty("os.name").toLowerCase();
        String yandexPath;

        if (os.contains("win")) {
            // Windows
            yandexPath = "C:\\Users\\%USERNAME%\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";
        } else if (os.contains("mac")) {
            // macOS
            yandexPath = "/Applications/Yandex.app/Contents/MacOS/Yandex";
        } else {
            // Linux
            yandexPath = "/usr/bin/yandex-browser";
        }

        options.setBinary(yandexPath);
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-extensions");

        options.addArguments("--start-maximized");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        System.out.println("Using Yandex Browser from: " + yandexPath);
    }

    @After
    public void tearDown() {
        // Нажать на тестового пользователя
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
        if (driver != null) {
            driver.quit();
        }
    }

    // Вспомогательный метод для создания пользователя через API и получения токена доступа
    protected String createUserAndGetAccessToken() {
        Response response = userClient.createUser(user);
        if (response.statusCode() == 200) {
            accessToken = userClient.getAccessToken(response);
            return accessToken;
        }
        return null;
    }

    // Getter для браузера
    public String getBrowser() {
        return browser;
    }

    // Методы для навигации

    protected void navigateToRegistrationPage(MainPage mainPage, LoginPage loginPage, RegistrationPage registrationPage) {
        waitForMainPageLoaded(mainPage);
        mainPage.clickLoginAccountButton();
        waitForLoginPageLoaded(loginPage);
        loginPage.clickRegisterLink();
        waitForRegistrationPageLoaded(registrationPage);
    }

    protected void navigateToLoginPageViaAccountButton(MainPage mainPage, LoginPage loginPage) {
        waitForMainPageLoaded(mainPage);
        mainPage.clickLoginAccountButton();
        waitForLoginPageLoaded(loginPage);
    }

    protected void navigateToLoginPageViaPersonalAccount(MainPage mainPage, LoginPage loginPage) {
        waitForMainPageLoaded(mainPage);
        mainPage.clickPersonalAccountButton();
        waitForLoginPageLoaded(loginPage);
    }

    protected void loginUser(MainPage mainPage, LoginPage loginPage, String email, String password) {
        navigateToLoginPageViaAccountButton(mainPage, loginPage);
        loginPage.login(email, password);
        waitForMainPageLoaded(mainPage);
        assertTrue("Пользователь должен быть залогинен", mainPage.isUserLoggedIn());
    }

    protected void navigateToProfilePage(MainPage mainPage, LoginPage loginPage, ProfilePage profilePage, String email, String password) {
        loginUser(mainPage, loginPage, email, password);
        mainPage.clickPersonalAccountButton();
        waitForProfilePageLoaded(profilePage);
    }

    // Методы ожидания загрузки страниц
    protected void waitForMainPageLoaded(MainPage mainPage) {
        assertTrue("Главная страница должна отображаться", mainPage.isMainPageLoaded());
    }

    protected void waitForLoginPageLoaded(LoginPage loginPage) {
        assertTrue("Страница логина должна отображаться", loginPage.isLoginPageDisplayed());
    }

    protected void waitForRegistrationPageLoaded(RegistrationPage registrationPage) {
        assertTrue("Страница регистрации должна отображаться", registrationPage.isRegistrationPageDisplayed());
    }

    protected void waitForProfilePageLoaded(ProfilePage profilePage) {
        assertTrue("Страница профиля должна отображаться", profilePage.isProfilePageDisplayed());
    }

    // Метод для получения accessToken после UI-логина
    protected String getAccessTokenForUser(String email, String password) {
        User loginUser = new User(email, password, "");
        Response response = userClient.login(loginUser);
        if (response.statusCode() == 200) {
            return userClient.getAccessToken(response);
        }
        return null;
    }

}
