package part1.lesson02.task03;

import part1.lesson02.task03.customExceptions.DuplicatePersonException;

/**
 * пример на сортировку
 */
public class Main {

    public static void main(String[] args) throws DuplicatePersonException {
        final int N = 10000;
        final int MINNAMELENGTH = 3;
        final int MAXNAMELENGTH = 10;
        Person[] persons = new Person[N];
        for(int i = 0; i < N; i++) {
            persons[i] = new Person();
            persons[i].setAge((int)(Math.random() * 101));
            if(Math.random() <= 0.5)
                persons[i].setSex(Sex.MAN);
            else
                persons[i].setSex(Sex.WOMAN);
            persons[i].setName(RussianNameGen.getRandomName((int)(Math.random() * (MAXNAMELENGTH - MINNAMELENGTH + 1) + MINNAMELENGTH)));
            for(int j = 0; j < i; j++) {
                if(persons[i].equals(persons[j])) throw new DuplicatePersonException("Найден двойник: " + persons[i]);
            }
        }
        Sorter[] sorters = new Sorter[2];
        sorters[0] = new BubbleSorter();
        sorters[1] = new DefaultSorter();
        for(int i = 0; i < sorters.length; i++) {
            Person[] sortedPersons = persons.clone();
            long startTime = System.nanoTime();
            sorters[i].sort(sortedPersons);
            long estimatedTime = System.nanoTime() - startTime;
            if(i == 0)
                for(Person person: sortedPersons)
                    System.out.println(person);
            System.out.println("Время выполнения алгоритма " + sorters[i].getClass().getSimpleName() + ": " + estimatedTime / 1000000 + " мс");
        }
    }
}
