package pageObject;

import com.codeborne.selenide.*;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.sun.istack.NotNull;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

/** <code>Page</code>
 * Главная страница.
 * @author Maksim_Kachaev
 * */
public class MainPage {
    private static final String H1 = "System Dashboard";
    protected String taskName;


    @FindBy(how = How.XPATH, using = "//div[@id='dashboard-content']//h1[text()='" + H1 +"']")
    private static SelenideElement mainH1;

    @FindBy(how = How.XPATH, using = "//*[@id='logo']//following-sibling::ul/*/a")
    private static List<SelenideElement> nav;

    @FindBy(how = How.XPATH, using = "//input[@id='summary']")
    private SelenideElement taskNameXpath;

    @FindBy(how = How.XPATH, using = "//input[@id='create-issue-submit']")
    private SelenideElement newTaskFormSubmitBtn;

    @FindBy(how = How.XPATH, using = "//input[@id='quickSearchInput']")
    private SelenideElement searchField;

    /**Ждет главную страницу
     * @see Condition#visible*/
    @Step("Ожидать открытия главной страницы.")
    @CanIgnoreReturnValue
    public static MainPage waitIsOpened() {
        MainPage page = page(MainPage.class);
        mainH1.shouldBe(Condition.visible);
        return page;
    }

    @Step("Нажать меню проекты")
    @CanIgnoreReturnValue
    public MainPage navProjectsClick() {
        getNavElem("Проекты").click();
        getNavElem("Проекты").shouldHave(Condition.cssClass("active"));
        return page(this);
    }

    @Step("Открыть форму создания задачи.")
    @CanIgnoreReturnValue
    public MainPage navCreateClick() {
        getNavElem("Создать").click();
        newTaskFormWaitIsOpened();
        return page(this);
    }

    /**Ожидать открытия формы создания задачи
     * @see Condition#visible*/
    @CanIgnoreReturnValue
    private MainPage newTaskFormWaitIsOpened() {
        taskNameXpath.shouldBe(Condition.visible);
        return page(this);
    }

    /**@return page url link*/
    @Step("Получить ссылку проекта")
    @CanIgnoreReturnValue
    public String navProjectsGetTestHref() {
        return Objects.requireNonNull(getNavElem("Проекты").getAttribute("href"));
    }

    @Step("Ввести имя задачи: (random) {taskName}")
    @CanIgnoreReturnValue
    public MainPage sendTaskName(String taskName) {
        taskNameXpath.sendKeys(taskName);
        this.taskName = taskName;
        return page(this);
    }

    @Step("Ввести описание задачи: {taskDescription}")
    @CanIgnoreReturnValue
    public MainPage sendTaskDescription(String taskDescription) {
        WebDriverRunner.driver().switchTo()     //driver
                .frame("mce_0_ifr")
                .findElement(By.id("tinymce"))
                .sendKeys(taskDescription);
        $x("//body[@id='tinymce']/p").shouldHave(Condition.exactText(taskDescription));
        WebDriverRunner.driver().switchTo().defaultContent();
        return page(this);
    }

    @Step("Нажать кнопку создать задачу - закрыть форму.")
    @CanIgnoreReturnValue
    public MainPage clickNewTaskFormSubmitBtn() {
        newTaskFormSubmitBtn.click();
        newTaskFormSubmitBtn.shouldNotBe(Condition.visible);
        return page(this);
    }

    @Step("Найти созданную задачу")
    @CanIgnoreReturnValue
    public TaskPage searchCreatedTask() {
        searchField.sendKeys(taskName);
        if (searchField.exists()) {
            searchField.sendKeys(Keys.ENTER);}
        return TaskPage.waitIsOpened();
    }

    /**Возвращает элемент меню по имени.
     * @return элемент страницы*/
    private SelenideElement getNavElem( String name) {
        SelenideElement el  = nav
                .stream()
                .filter(webElement -> webElement.getText().contains(name))
                .collect(Collectors.toList())
                .get(0);
        Assertions.assertNotNull(el);
        return el;
    }

}
