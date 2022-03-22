package pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
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
    private final static String main_page_url = "https://edujira.ifellow.ru/secure/Dashboard.jspa";
    protected String task_description = "Test task";
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

    @Step("Проверить что главная страница открыта")
    public static MainPage isOpened() {
        String title = "System Dashboard - Jira";
        while (!WebDriverRunner.getWebDriver().getTitle().equals(title)){sleep(100);
        }
//        main_h1.shouldBe(Condition.visible);
        return page(MainPage.class);
    }

    private static MainPage open() {
        Selenide.open(main_page_url, MainPage.class);
        return page(MainPage.class);
    }
    @Description("Инициализация")
    public static MainPage init() {
        return page(MainPage.class);
    }

    private SelenideElement get_nav_elem(String name) {
        SelenideElement el  = nav
                .stream()
                .filter(webElement -> webElement.getText().contains(name))
                .collect(Collectors.toList())
                .get(0);
        return el;
    }
    @Step("Нажать меню проекты")
    public MainPage nav_projects_click() {
        get_nav_elem("Проекты").click();
        return page(this);
    }

    @Step("Создать задачу")
    public MainPage nav_create_click() {
        get_nav_elem("Создать").click();
        return page(this);
    }

    @Step("Проверить что форма создания задачи открыта")
    public MainPage new_task_form_isOpened() {
        task_name_xpath.shouldBe(Condition.visible);
        return page(this);
    }

    /**@return page url link*/
    @Step("Получить ссылку проекта")
    public String nav_projects_get_TEST_href() {
        //System.setProperty("tasks_href", Objects.requireNonNull(get_nav_elem("Проекты").getAttribute("href")));
        return Objects.requireNonNull(get_nav_elem("Проекты").getAttribute("href"));
    }

    @Step("Ввести имя задачи")
    public MainPage send_task_name() {
        get_random_name();      //to this.task_name
        task_name_xpath.sendKeys(task_name);
        return page(this);
    }

    @Step("Ввести описание задачи")
    public MainPage send_task_description() {
        $x("//iframe[@id='mce_0_ifr']").shouldBe(Condition.visible);

        WebDriverRunner.driver().switchTo()     //driver
                .frame("mce_0_ifr")
                .findElement(By.id("tinymce"))
                .sendKeys(task_description);
        WebDriverRunner.driver().switchTo().defaultContent();
        return page(this);
    }

    @Step("Нажать кнопку создать задачу")
    public MainPage click_new_task_form_submitBtn() {
        new_task_form_submitBtn.click();
        return page(this);
    }

    @Step("Найти созданную задачу")
    public MainPage search_created_task() {
        search_field.sendKeys(task_name);
        if (search_field.exists()) {search_field.sendKeys(Keys.ENTER);}
        return page(this);
    }

    private void get_random_name() {
        Random rand = new Random();
        int int_random = rand.nextInt(10000);
        this.task_name = "MK" + int_random;
    }

}
