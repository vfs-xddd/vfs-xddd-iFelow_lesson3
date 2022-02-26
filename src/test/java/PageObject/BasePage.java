package PageObject;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.sleep;

public class BasePage {

    String login = "kachMax";
    String password = "123qaz!@#QAZ";
    protected static String tasks_href;
    protected  static WebDriver driver;


    @BeforeAll
    public static void setupSettings() {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);
        Configuration.timeout = 5000;
        Configuration.holdBrowserOpen = true;
    }

    @BeforeEach
    public void prepare_for_tests() {
        AuthorisationPage.open()
                .isOpened()
                .send_login()
                .send_password()
                .click_submitBtn();

        MainPage.init()
                .isOpened();
    }

    @AfterAll
    public static void closeWebDriver() {
        WebDriverRunner.closeWebDriver();
    }

}
