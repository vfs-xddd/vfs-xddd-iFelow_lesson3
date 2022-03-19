package Tests;

import PageObject.AuthorisationPage;
import PageObject.MainPage;
import PageObject.WebDriverHandler;
import Utils.PropertyManager;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


public class Tests_config {

    @BeforeAll
    public static void init_TestEnvironment(){
        init_TestProperty();
        init_WebDriver();
        add_Allure();
    }

    @BeforeEach
    @Step("Авторизация")
    public void prepare_for_tests() {
        AuthorisationPage.open(System.getProperty("start_page_url"))
                .isOpened()
                .send_login(System.getProperty("login"))
                .send_password(System.getProperty("password"))
                .click_submitBtn();
    }

//    @AfterEach
//    public void close_window() {
//        WebDriverRunner.closeWindow();
//    }
//
//    @AfterAll
//    public static void close_WebDriver() {
//        WebDriverRunner.closeWebDriver();
//    }

    private static void init_TestProperty() {new PropertyManager();}

    private static void init_WebDriver(){WebDriverRunner.setWebDriver(WebDriverHandler.getDriver());}

    private static void add_Allure() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
    }



}
