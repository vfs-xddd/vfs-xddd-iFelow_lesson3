package PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

public class ProjectsPage {

    @FindBy(how = How.XPATH, using = "//span[@id='subnav-title']/span[text()='Список задач']")
    private SelenideElement tasks_h1;

    @FindBy(how = How.XPATH, using = "//a[contains(@data-link-id, 'plan-scrum')]")
    private SelenideElement left_menu_tasks_list;

    @FindBy(how = How.XPATH, using = "//div[contains(text(), 'проблем') and @class='ghx-issue-count']")
    private SelenideElement printed_task_count;

    @FindBy(how = How.XPATH, using = "//div[@id='ghx-content-group']//div[contains(@class, 'js-issue-list')]/div[contains(@class, 'js-issue')]")
    private List<SelenideElement> list_of_tasks_elem;

    @Step("Открыть ссылку проекта")
    public static ProjectsPage open() {
        Selenide.open(System.getProperty("tasks_href"), ProjectsPage.class);
        return page(ProjectsPage.class);
    }

    @Step("Проверить что страница проекта открыта")
    public ProjectsPage isOpened() {
        tasks_h1.shouldBe(Condition.visible);
        return page(this);
    }
    @Step("Нажать кнопку в меню задачи")
    public ProjectsPage taskList_click() {
        left_menu_tasks_list.click();
        return page(this);
    }

    @Step("Проверить соответствие кол-ва задач")
    @DisplayName(".assertEquals(expected_tasks_count, printed_tasks_count)")
    public ProjectsPage test_tasks_count() {
        int printed_tasks_count = Integer.valueOf(printed_task_count.getText().split(" ")[0]);
        if (printed_tasks_count > 0) {while (list_of_tasks_elem.isEmpty()) {sleep(100);}}
        int expected_tasks_count = list_of_tasks_elem.size();
        Assertions.assertEquals(expected_tasks_count, printed_tasks_count);
        return page(this);
    }
}
