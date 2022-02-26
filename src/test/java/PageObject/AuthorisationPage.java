package PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.page;

public class AuthorisationPage extends BasePage {
    private final String h1 = "Добро пожаловать в Jira";
    private final static String start_page_url = "https://edujira.ifellow.ru/login.jsp";

    @FindBy(how = How.XPATH, using = "//div[@class='aui-page-header-main']//h1[text()='"+ h1 + "']")
    private SelenideElement welcome_h1;

    @FindBy(how = How.XPATH, using = "//input[@id='login-form-username']")
    private SelenideElement login_field;

    @FindBy(how = How.XPATH, using = "//input[@id='login-form-password']")
    private SelenideElement password_field;

    @FindBy(how = How.XPATH, using = "//input[@value='Вход']")
    private SelenideElement loginBtn;

    public AuthorisationPage isOpened() {
        welcome_h1.shouldBe(Condition.visible);
        return page(this);
    }

    public static AuthorisationPage open() {
        Selenide.open(start_page_url, AuthorisationPage.class);
        return page(AuthorisationPage.class);
    }

    public AuthorisationPage send_login() {
        login_field.click();
        login_field.sendKeys(login);
        return page(this);
    }

    public AuthorisationPage send_password() {
        password_field.click();
        password_field.sendKeys(password);
        return page(this);
    }

    public AuthorisationPage click_submitBtn() {
        loginBtn.click();
        return page(this);
    }
}
