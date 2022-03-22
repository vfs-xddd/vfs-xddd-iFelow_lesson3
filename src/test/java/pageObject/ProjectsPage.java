package pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

/** <code>Page</code>
 * Страница проектов.
 * @author Maksim_Kachaev
 * */
public class ProjectsPage {

    @FindBy(how = How.XPATH, using = "//span[@id='subnav-title']/span[text()='Список задач']")
    private SelenideElement tasksH1;

    @FindBy(how = How.XPATH, using = "//a[contains(@data-link-id, 'plan-scrum')]")
    private static SelenideElement leftMenuTasksList;

    @FindBy(how = How.XPATH, using = "//div[contains(text(), 'проблем') and @class='ghx-issue-count']")
    private SelenideElement printedTaskCount;

    @FindBy(how = How.XPATH, using = "//div[@id='ghx-content-group']//div[contains(@class, 'js-issue-list')]/div[contains(@class, 'js-issue')]")
    private ElementsCollection listOfTasksElem;


    @Step("Открыть ссылку проекта")
    @CanIgnoreReturnValue
    public static ProjectsPage open(String task_href) {
        ProjectsPage page = Selenide.open(task_href, ProjectsPage.class);
        leftMenuTasksList.shouldBe(Condition.visible);
        return page;
    }

    /**Ждет пока будет развернут <code>Список задач</code>>
     * @see Condition#visible
     * */
    @CanIgnoreReturnValue
    public ProjectsPage waitIsOpened() {
        tasksH1.shouldBe(Condition.visible);
        return page(this);
    }
    @Step("Нажать кнопку в меню задачи")
    @CanIgnoreReturnValue
    public ProjectsPage taskListClick() {
        leftMenuTasksList.click();
        waitIsOpened();
        return page(this);
    }

    @Step("Проверить соответствие кол-ва задач")
    @CanIgnoreReturnValue
    @DisplayName(".assertEquals(expected_tasks_count, printed_tasks_count)")
    public ProjectsPage testTasksCount() {
        int printed_tasks_count = Integer.parseInt(printedTaskCount.getText().split(" ")[0]);
        if (printed_tasks_count > 0) {while (listOfTasksElem.isEmpty()) {sleep(100);}}
        int expected_tasks_count = listOfTasksElem.size();
        Assertions.assertEquals(expected_tasks_count, printed_tasks_count);
        return page(this);
    }
}
