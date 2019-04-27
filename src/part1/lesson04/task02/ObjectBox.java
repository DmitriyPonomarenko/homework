package part1.lesson04.task02;

import java.util.HashSet;

/**
 * обёртка над коллекцией HashSet<Object>
 */
public class ObjectBox {

    private HashSet<Object> values = new HashSet<>();

    /**
     * добавляет объект в коллекцию
     *
     * @param e добавляемый объект
     */
    public void addObject(Object e) {
        values.add(e);
    }

    /**
     * проверяет наличие объекта в коллекции и при наличии удаляет его
     *
     * @param e удаляемый объект
     */
    public void deleteObject(Object e) {
        values.remove(e);
    }

    /**
     * выводит содержимое коллекции в строку
     */
    public void dump() {
        System.out.println("ObjectBox{" +
                "values=" + values +
                '}');
    }
}
