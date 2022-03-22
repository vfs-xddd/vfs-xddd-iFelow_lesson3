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
import java.util.Random;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

/** <code>Page</code>
 * Главная страница.
 * @author Maksim_Kachaev
 * */
public class MainPage {
    private static final String h1 = "System Dashboard";
    //private static final String main_page_url = "https://edujira.ifellow.ru/secure/Dashboard.jspa";
    //protected String task_description = "Test task";
    protected String task_name;


    @FindBy(how = How.XPATH, using = "//div[@id='dashboard-content']//h1[text()='" + h1 +"']")
    private static SelenideElement main_h1;

    @FindBy(how = How.XPATH, using = "//h1[@id='logo']//following-sibling::ul/*/a")
    private static List<SelenideElement> nav;

    @FindBy(how = How.XPATH, using = "//input[@id='summary']")
    private SelenideElement task_name_xpath;

    @FindBy(how = How.XPATH, using = "//div[@id='mceu_0-body']")
    private SelenideElement task_description_xpath;

    @FindBy(how = How.XPATH, using = "//input[@id='create-issue-submit']")
    private SelenideElement new_task_form_submitBtn;

    @FindBy(how = How.XPATH, using = "//input[@id='quickSearchInput']")
    private SelenideElement search_field;

    /**Ждет главную страницу
     * @see Condition#visible*/
    @Step("Ожидать открытия главной страницы.")
    @CanIgnoreReturnValue
    public static MainPage waitIsOpened() {
        MainPage page = page(MainPage.class);
        main_h1.shouldBe(Condition.visible);
        return page;
    }

    @Step("Нажать меню проекты")
    @CanIgnoreReturnValue
    public MainPage nav_projects_click() {
        get_nav_elem("Проекты").click();
        get_nav_elem("Проекты").shouldHave(Condition.cssClass("active"));
        return page(this);
    }

    @Step("Открыть форму создания задачи.")
    @CanIgnoreReturnValue
    public MainPage nav_create_click() {
        get_nav_elem("Создать").click();
        new_task_form_waitIsOpened();
        return page(this);
    }

    /**Ожидать открытия формы создания задачи
     * @see Condition#visible*/
    @CanIgnoreReturnValue
    private MainPage new_task_form_waitIsOpened() {
        task_name_xpath.shouldBe(Condition.visible);
        return page(this);
    }

    /**@return page url link*/
    @Step("Получить ссылку проекта")
    @CanIgnoreReturnValue
    public String nav_projects_get_TEST_href() {
        return Objects.requireNonNull(get_nav_elem("Проекты").getAttribute("href"));
    }

    @Step("Ввести имя задачи: (random) {task_name}")
    @CanIgnoreReturnValue
    public MainPage send_task_name(String task_name) {
        //get_random_name();
        task_name_xpath.sendKeys(task_name);
        this.task_name = task_name;
        return page(this);
    }

    @Step("Ввести описание задачи: {task_description}")
    @CanIgnoreReturnValue
    public MainPage send_task_description(String task_description) {
        //$x("//iframe[@id='mce_0_ifr']").shouldBe(Condition.visible);

        WebDriverRunner.driver().switchTo()     //driver
                .frame("mce_0_ifr")
                .findElement(By.id("tinymce"))
                .sendKeys(task_description);
        $x("//body[@id='tinymce']/p").shouldHave(Condition.exactText(task_description));
        WebDriverRunner.driver().switchTo().defaultContent();
        return page(this);
    }

    @Step("Нажать кнопку создать задачу - закрыть форму.")
    @CanIgnoreReturnValue
    public MainPage click_new_task_form_submitBtn() {
        new_task_form_submitBtn.click();
        new_task_form_submitBtn.shouldNotBe(Condition.visible);
        return page(this);
    }

    @Step("Найти созданную задачу")
    @CanIgnoreReturnValue
    public TaskPage search_created_task() {
        search_field.sendKeys(task_name);
        if (search_field.exists()) {search_field.sendKeys(Keys.ENTER);}
        return TaskPage.waitIsOpened();
    }

    /**Возвращает элемент меню по имени.
     * @return элемент страницы*/
    private SelenideElement get_nav_elem(@NotNull String name) {
        SelenideElement el  = nav
                .stream()
                .filter(webElement -> webElement.getText().contains(name))
                .collect(Collectors.toList())
                .get(0);
        Assertions.assertNotNull(el);
        return el;
    }

    /**Создает случайное имя для бага. Сохраняет в this.task_name
     * */
    private void get_random_name() {
        Random rand = new Random();
        int int_random = rand.nextInt(10000);
        this.task_name = "MK" + int_random;
    }

}
