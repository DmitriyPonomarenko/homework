package part1.lesson02.task03.sorters;

import part1.lesson02.task03.entity.Person;

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
