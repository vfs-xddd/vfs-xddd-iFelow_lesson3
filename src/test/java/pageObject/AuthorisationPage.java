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
    private static final String H1 = "Добро пожаловать в Jira";

    @FindBy(how = How.XPATH, using = "//div[@class='aui-page-header-main']//h1[text()='"+ H1 + "']")
    private static  SelenideElement welcomeH1;

    @FindBy(how = How.XPATH, using = "//input[@id='login-form-username']")
    private SelenideElement loginField;

    @FindBy(how = How.XPATH, using = "//input[@id='login-form-password']")
    private SelenideElement passwordField;

    @FindBy(how = How.XPATH, using = "//input[@value='Вход']")
    private SelenideElement loginBtn;

    @Step("Открыть страницу: {start_page_url}")
    @CanIgnoreReturnValue
    public static AuthorisationPage open(String start_page_url) {
        AuthorisationPage page = Selenide.open(start_page_url, AuthorisationPage.class);
        welcomeH1.shouldBe(Condition.visible);
        return page;
    }

    @Step("Ввести логин: {login} ")
    @CanIgnoreReturnValue
    public AuthorisationPage send_login(String login) {
        loginField.sendKeys(login);
        return page(this);
    }

    @Step("Ввести пароль: {password} ")
    @CanIgnoreReturnValue
    public AuthorisationPage send_password(String password) {
        passwordField.sendKeys(password);
        return page(this);
    }

    @Step("Нажать кнопку <Войти>")
    @CanIgnoreReturnValue
    public MainPage click_submitBtn() {
        loginBtn.click();
        return MainPage.waitIsOpened();
    }

}
