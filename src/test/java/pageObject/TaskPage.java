package pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

/** <code>Page</code>
 * Страница задачи.
 * @author Maksim_Kachaev
 * */
public class TaskPage {

    @FindBy(how = How.XPATH, using = "//span[@class='assign-to-me-link']")
    private static SelenideElement take_task_toMe;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Бизнес-процесс')]")
    private SelenideElement bisness_pr_btn;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Выполнено')]")
    private SelenideElement done_task;

    @FindBy(how = How.XPATH, using = "//span[@id='assignee-val']/span[@class='user-hover']")
    private SelenideElement task_on_me;

    @FindBy(how = How.XPATH, using = "//span[@id='status-val']/span")
    private SelenideElement task_ready;



    /**Ожидает открытия страницы задачи.
     * @see Condition#visible*/
    @CanIgnoreReturnValue
    public static TaskPage waitIsOpened() {
        TaskPage page = page(TaskPage.class);
        take_task_toMe.shouldBe(Condition.visible);
        return page;
    }

    @Step("Взять задачу себе")
    @CanIgnoreReturnValue
    public TaskPage take_task_toMe() {
        String userLogin = System.getProperty("login");
        take_task_toMe.click();
        task_on_me.shouldHave(Condition.attribute("rel", userLogin));
        return page(this);
    }

    @Step("Установить статус выполнено")
    @CanIgnoreReturnValue
    public TaskPage task_to_done_status() {
        bisness_pr_btn.click();
        done_task.click();
        task_ready.shouldHave(Condition.exactText("Готово"));
        return page(this);
    }


}

