package tests;

import hooks.TestsConfig;
import io.qameta.allure.Description;
import pageObject.AuthorisationPage;
import pageObject.MainPage;
import pageObject.ProjectsPage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.NewTaskValueGenerator;

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
                .send_login(System.getProperty("login"))
                .send_password(System.getProperty("password"))
                .click_submitBtn();
    }

    /**UI test*/
    @Test
    @DisplayName("Тест кейс 1")
    @Description("Проверка соответсвия общего количества заведенных задач в выбранном проекте количеству задач в шапке.")
    public void test1() {
        String test_href =  MainPage.waitIsOpened()
                                    .navProjectsClick()
                                    .navProjectsGetTestHref();

                            ProjectsPage.open(test_href)
                                    .taskListClick()
                                    .testTasksCount();
    }

    /**UI test*/
    @Test
    @DisplayName("Тест кейс 2")
    @Description("Проверка создания нового бага, простановки статусов до закрытого.")
    public void test2() {
         String[] taskValues = NewTaskValueGenerator.getValues();

        MainPage.waitIsOpened()
                .navCreateClick()
                .sendTaskName(taskValues[0])
                .sendTaskDescription(taskValues[1])
                .clickNewTaskFormSubmitBtn()
                .searchCreatedTask()
                .takeTaskToMe()
                .taskToDoneStatus();
    }
}