package PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

public class TaskPage extends BasePage{

    @FindBy(how = How.XPATH, using = "//span[@class='assign-to-me-link']")
    private SelenideElement take_task_toMe;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Бизнес-процесс')]")
    private SelenideElement bisness_pr_btn;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Выполнено')]")
    private SelenideElement done_task;

    @Step("Проверить страница задачи открыта")
    public TaskPage isOpened() {
        take_task_toMe.shouldBe(Condition.visible);
        return page(this);
    }

    private static TaskPage open() {
        return page(TaskPage.class);
    }

    public static TaskPage init() {
        return page(TaskPage.class);
    }

    @Step("Взять задачу себе")
    public TaskPage take_task_toMe() {
        take_task_toMe.shouldBe(Condition.visible).click();
        return page(this);
    }

    @Step("Установить статус выполнено")
    public TaskPage task_to_done_status() {
        bisness_pr_btn.click();
        done_task.shouldBe(Condition.visible).click();
        return page(this);
    }


}

