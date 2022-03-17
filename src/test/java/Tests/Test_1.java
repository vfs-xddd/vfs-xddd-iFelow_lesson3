package Tests;

import PageObject.BasePage;
import PageObject.MainPage;
import PageObject.ProjectsPage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Test_1 extends BasePage {

    @Test
    @DisplayName("Тест кейс 1")
    public void Test1() {
        MainPage.init()
                .nav_projects_click()
                .nav_projects_get_TEST_href();               //ссылка запишется в переменную BasePage.tasks_href
        ProjectsPage.open()
                .taskList_click()
                .isOpened()
                .test_tasks_count();
    }
}