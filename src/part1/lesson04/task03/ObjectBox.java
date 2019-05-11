package part1.lesson04.task03;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

/**
 * обёртка над коллекцией HashSet<T>
 */
public class ObjectBox<T> {

    HashSet<T> values = new HashSet<>();

    /**
     * создаёт коллекцию из массива
     *
     * @param source исходный массив
     */
    public ObjectBox(T[] source) {
        values.addAll(Arrays.asList(source));
    }

    /**
     * добавляет объект в коллекцию
     *
     * @param e добавляемый объект
     */
    public void addObject(T e) {
        values.add(e);
    }

    /**
     * проверяет наличие объекта в коллекции и при наличии удаляет его
     *
     * @param e удаляемый объект
     */
    public void deleteObject(T e) {
        values.remove(e);
    }

    /**
     * выводит содержимое коллекции в строку
     */
    public void dump() {
        System.out.println(toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectBox<?> objectBox = (ObjectBox<?>) o;
        return Objects.equals(values, objectBox.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "values=" + values +
                '}';
    }
}
