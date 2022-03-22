package tests;

import hooks.TestsConfig;
import io.qameta.allure.Description;
import pageObject.AuthorisationPage;
import pageObject.MainPage;
import pageObject.ProjectsPage;
import pageObject.TaskPage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** Главный тестовый класс.
 *  <p>Наследует конфигурацию WebDriver, Allure и входные данные Properties.</p>
 *  <code>Junit5</code>
 *  @author Maksim_Kachaev
 * */
public class TestRunner extends TestsConfig {

    /**Проходит авторизацию на стартовой странице
     * */
    @BeforeEach
    @Step("Авторизация")
    public void authorisation() {
        AuthorisationPage.open(System.getProperty("start_page_url"))
                .isOpened()
                .send_login(System.getProperty("login"))
                .send_password(System.getProperty("password"))
                .click_submitBtn();
    }

    /**UI test*/
    @Test
    @DisplayName("Тест кейс 1")
    @Description("Проверка соответсвия общего количества заведенных задач в выбранном проекте количеству задач в шапке.")
    public void test1() {
        String test_href =  MainPage.isOpened()
                                    .nav_projects_click()
                                    .nav_projects_get_TEST_href();

                            ProjectsPage.open(test_href)
                                    .taskList_click()
                                    .isOpened()
                                    .test_tasks_count();
    }

    /**UI test*/
    @Test
    @DisplayName("Тест кейс 2")
    @Description("Проверка создания нового бага, простановки статусов до закрытого.")
    public void test2() {
        MainPage.isOpened()
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
    }
}