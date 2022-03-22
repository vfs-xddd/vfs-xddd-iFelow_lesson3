package pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

/** <code>Page</code>
 * Страница авторизации.
 * @author Maksim_Kachaev
 * */
public class AuthorisationPage {
    private final String h1 = "Добро пожаловать в Jira";

    @FindBy(how = How.XPATH, using = "//div[@class='aui-page-header-main']//h1[text()='"+ h1 + "']")
    private SelenideElement welcome_h1;

    @FindBy(how = How.XPATH, using = "//input[@id='login-form-username']")
    private SelenideElement login_field;

    @FindBy(how = How.XPATH, using = "//input[@id='login-form-password']")
    private SelenideElement password_field;

    @FindBy(how = How.XPATH, using = "//input[@value='Вход']")
    private SelenideElement loginBtn;

    @Step("Проверить что страница открыта")
    public AuthorisationPage isOpened() {
        welcome_h1.shouldBe(Condition.visible);
        return page(this);
    }

    @Step("Открыть страницу: {start_page_url}")
    public static AuthorisationPage open(String start_page_url) {
        Selenide.open(start_page_url, AuthorisationPage.class);
        return page(AuthorisationPage.class);
    }

    @Step("Ввести логин: {login} ")
    public AuthorisationPage send_login(String login) {
        login_field.click();
        login_field.sendKeys(login);
        return page(this);
    }

    @Step("Ввести пароль: {password} ")
    public AuthorisationPage send_password(String password) {
        password_field.click();
        password_field.sendKeys(password);
        return page(this);
    }

    @Step("Нажать кнопку <Войти>")
    public AuthorisationPage click_submitBtn() {
        loginBtn.click();
        return page(this);
    }
}
