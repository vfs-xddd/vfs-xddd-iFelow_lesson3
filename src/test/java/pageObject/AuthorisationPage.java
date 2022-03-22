package pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

/** <code>Page</code>
 * Страница авторизации.
 * @author Maksim_Kachaev
 * */
public class AuthorisationPage {
    private static final String h1 = "Добро пожаловать в Jira";

    @FindBy(how = How.XPATH, using = "//div[@class='aui-page-header-main']//h1[text()='"+ h1 + "']")
    private static  SelenideElement welcome_h1;

    @FindBy(how = How.XPATH, using = "//input[@id='login-form-username']")
    private SelenideElement login_field;

    @FindBy(how = How.XPATH, using = "//input[@id='login-form-password']")
    private SelenideElement password_field;

    @FindBy(how = How.XPATH, using = "//input[@value='Вход']")
    private SelenideElement loginBtn;

    @Step("Открыть страницу: {start_page_url}")
    @CanIgnoreReturnValue
    public static AuthorisationPage open(String start_page_url) {
        AuthorisationPage page = Selenide.open(start_page_url, AuthorisationPage.class);
        welcome_h1.shouldBe(Condition.visible);
        return page;
    }

    @Step("Ввести логин: {login} ")
    @CanIgnoreReturnValue
    public AuthorisationPage send_login(String login) {
        login_field.sendKeys(login);
        return page(this);
    }

    @Step("Ввести пароль: {password} ")
    @CanIgnoreReturnValue
    public AuthorisationPage send_password(String password) {
        password_field.sendKeys(password);
        return page(this);
    }

    @Step("Нажать кнопку <Войти>")
    @CanIgnoreReturnValue
    public MainPage click_submitBtn() {
        loginBtn.click();
        return MainPage.waitIsOpened();
    }

}
