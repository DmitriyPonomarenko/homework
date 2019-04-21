package part1.lesson02.task03;

import java.util.Arrays;

/**
 * сортировщик стандартным методом
 */
public class DefaultSorter implements Sorter {

    @Override
    public void sort(Person[] array) {
        Arrays.sort(array);
    }
}
