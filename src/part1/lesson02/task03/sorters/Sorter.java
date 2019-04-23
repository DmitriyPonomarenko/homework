package part1.lesson02.task03.sorters;

import part1.lesson02.task03.entity.Person;

/**
 * сортировщик людей
 */
public interface Sorter {
    /**
     * @param array массив людей, который будет отсортирован
     */
    void sort(Person[] array);
}
