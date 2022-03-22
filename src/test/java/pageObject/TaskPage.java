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
    private static SelenideElement takeTaskToMe;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Бизнес-процесс')]")
    private SelenideElement bisnessPrBtn;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Выполнено')]")
    private SelenideElement doneTask;

    @FindBy(how = How.XPATH, using = "//span[@id='assignee-val']/span[@class='user-hover']")
    private SelenideElement taskOnMe;

    @FindBy(how = How.XPATH, using = "//span[@id='status-val']/span")
    private SelenideElement taskReady;



    /**Ожидает открытия страницы задачи.
     * @see Condition#visible*/
    @CanIgnoreReturnValue
    public static TaskPage waitIsOpened() {
        TaskPage page = page(TaskPage.class);
        takeTaskToMe.shouldBe(Condition.visible);
        return page;
    }

    @Step("Взять задачу себе")
    @CanIgnoreReturnValue
    public TaskPage takeTaskToMe() {
        String userLogin = System.getProperty("login");
        takeTaskToMe.click();
        taskOnMe.shouldHave(Condition.attribute("rel", userLogin));
        return page(this);
    }

    @Step("Установить статус выполнено")
    @CanIgnoreReturnValue
    public TaskPage taskToDoneStatus() {
        bisnessPrBtn.click();
        doneTask.click();
        taskReady.shouldHave(Condition.exactText("Готово"));
        return page(this);
    }


}

