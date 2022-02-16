import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;


public class RunTest {

    @BeforeAll
    public static void beforeTesting() {
        System.out.println("\nSystem ready for tests...");
    }

    @AfterAll
    public static void afterTesting() {
        System.out.println("\nTests completed!");
    }

    @BeforeEach
    public void addTopBorderLine() {
        System.out.println("================================================================================");
    }

    @Test
    public void main() {
        open("https://demoqa.com/automation-practice-form");
    }

    @Test
    public void test_1()
    {
        System.out.println("1");
        assertEquals("AW", "AW");
    }

    @Test
    public void test_2() {
        System.out.println(2);
        assertTrue(1>2, "check a>b");
    }
}
