package utils;

import java.util.Random;

/**Класс генератор текстовых значений.
 * <p>Создает дефолтные значения для полей формы при создании новой задачи</p>
 * @see pageObject.MainPage#sendTaskName
 * @see pageObject.MainPage#sendTaskDescription
 * @author Maksim_Kachaev*/
public class NewTaskValueGenerator {
    private static final String description = "Test task";

    /**Создает случайное имя для бага.
     * */
    private static String getRandomName() {
        Random rand = new Random();
        int int_random = rand.nextInt(10000);
        return  "MK" + int_random;
    }

    /**Возвращает массив значений
     * @return [имя задачи, описание]*/
    public static String[] getValues() {
        return new String[] {getRandomName(), description};
    }
}
