package hooks;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/** Класс конфигурации веб драйвера.
 *
 * @author Maksim_Kachaev
 * */
public class WebDriverHandler {

    /**Настраевает конфигурацию веб драйвера
     * @return готовый драйвер для потока тестов
     * */
    public static WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        Configuration.timeout = 5000;
        Configuration.holdBrowserOpen = false;

        return driver;
    }
}
