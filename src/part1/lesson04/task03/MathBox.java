package part1.lesson04.task03;

import java.util.Arrays;
import java.util.Iterator;

/**
 * обёртка над коллекцией HashSet<Number> с некоторыми математическими функциями
 */
public class MathBox extends ObjectBox<Number> {

    /**
     * создаёт коллекцию из массива
     * Элементы не могут повторяться
     *
     * @param source массив Number
     */
    public MathBox(Number[] source) {
        super(source);
    }

    /**
     * @return возвращает сумму всех элементов коллекции
     */
    public Number summator() {
        double result = 0;
        for (Number value : values) {
            if (value != null) {
                result += value.doubleValue();
            }
        }
        return result;
    }

    /**
     * выполняет поочередное деление всех хранящихся в объекте элементов.
     * Хранящиеся в объекте данные полностью заменяются результатами деления
     *
     * @param divider делитель
     */
    public void splitter(Number divider) {
        double doubleDivider = divider.doubleValue();
        Number[] newValues = new Number[values.size()];
        Iterator<Number> it = values.iterator();
        for (int i = 0; i < values.size(); i++) {
            Number value = it.next();
            if (value != null) {
                newValues[i] = value.doubleValue() / doubleDivider;
            } else {
                newValues[i] = null;
            }
        }
        values.clear();
        values.addAll(Arrays.asList(newValues));
    }

}
