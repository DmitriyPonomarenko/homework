package part1.lesson02.task03;

import part1.lesson02.task03.customExceptions.DuplicatePersonException;
import part1.lesson02.task03.entity.Person;
import part1.lesson02.task03.entity.Sex;
import part1.lesson02.task03.sorters.BubbleSorter;
import part1.lesson02.task03.sorters.DefaultSorter;
import part1.lesson02.task03.sorters.Sorter;
import part1.lesson02.task03.util.RussianNameGen;

/**
 * пример на сортировку
 */
public class Main {

    private static final int N = 10000;
    private static final int MIN_NAME_LENGTH = 3;
    private static final int MAX_NAME_LENGTH = 10;

    public static void main(String[] args) {
        Person[] persons = generatePersons();
        Sorter[] sorters = new Sorter[2];
        sorters[0] = new BubbleSorter();
        sorters[1] = new DefaultSorter();
        for (int i = 0; i < sorters.length; i++) {
            Person[] sortedPersons = persons.clone();
            long startTime = System.nanoTime();
            sorters[i].sort(sortedPersons);
            long estimatedTime = System.nanoTime() - startTime;
            if (i == 0) {
                for (Person person : sortedPersons) {
                    System.out.println(person);
                }
            }
            System.out.println("Время выполнения алгоритма " + sorters[i].getClass().getSimpleName() + ": " + estimatedTime / 1000000 + " мс");
        }
    }

    /**
     * генерация людей
     *
     * @return массив людей
     */
    private static Person[] generatePersons() {
        Person[] persons = new Person[N];
        for (int i = 0; i < persons.length; i++) {
            persons[i] = new Person();
            persons[i].setAge((int) (Math.random() * 101));
            if (Math.random() <= 0.5) {
                persons[i].setSex(Sex.MAN);
            } else {
                persons[i].setSex(Sex.WOMAN);
            }
            persons[i].setName(RussianNameGen.getRandomName((int) (Math.random() * (MAX_NAME_LENGTH - MIN_NAME_LENGTH + 1) + MIN_NAME_LENGTH)));
            for (int j = 0; j < i; j++) {
                try {
                    if (persons[i].equals(persons[j])) {
                        throw new DuplicatePersonException("Найден двойник: " + persons[i]);
                    }
                } catch (DuplicatePersonException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return persons;
    }
}
