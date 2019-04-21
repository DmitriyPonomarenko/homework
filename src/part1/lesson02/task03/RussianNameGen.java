package part1.lesson02.task03;

/**
 * генератор случайных последовательностей из русских букв
 * первая буква - заглавная
 * остальные - строчные
 */
public class RussianNameGen {

    private static final String CAPITALS = "ЙЦУКЕНГШЩЗХФЫВАПРОЛДЖЭЯЧСМИТБЮ";
    private static final String LOWERS = "йцукенгшщзхъфывапролджэячсмитьбю";

    /**
     *
     * @param length длина последовательности
     * @return последовательность русских букв
     */
    public static String getRandomName(int length) {
        StringBuilder result = new StringBuilder().append(CAPITALS.charAt((int)(Math.random() * CAPITALS.length())));
        for(int i = 2; i <= length; i++)
            result.append(LOWERS.charAt((int)(Math.random() * LOWERS.length())));
        return result.toString();
    }
}
