package utils;

import java.util.Random;

public class NewTaskValueGenerator {
    private static final String description = "Test task";

    /**Создает случайное имя для бага.
     * */
    private static String getRandomName() {
        Random rand = new Random();
        int int_random = rand.nextInt(10000);
        return  "MK" + int_random;
    }

    public static String[] getValues() {
        return new String[] {getRandomName(), description};
    }
}
