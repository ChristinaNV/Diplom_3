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

import java.util.concurrent.TimeUnit;

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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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

}
