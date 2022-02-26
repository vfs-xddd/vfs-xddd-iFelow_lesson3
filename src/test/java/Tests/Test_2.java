package Tests;

import PageObject.BasePage;
import PageObject.MainPage;
import PageObject.TaskPage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.sleep;

public class Test_2 extends BasePage {

    @Test
    public void Test2() {
        MainPage.init()
                .nav_create_click()
                .new_task_form_isOpened()
                .send_task_name()
                .send_task_description()
                .click_new_task_form_submitBtn()
                .search_created_task();
        TaskPage.init()
                .isOpened()
                .take_task_toMe()
                .task_to_done_status();
        sleep(3000);
    }
}
