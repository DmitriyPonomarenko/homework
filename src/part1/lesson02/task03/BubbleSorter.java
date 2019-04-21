package part1.lesson02.task03;

/**
 * сортировщик пузырьковым методом
 */
public class BubbleSorter implements Sorter {

    @Override
    public void sort(Person[] array) {
        for(int i = 0; i < array.length; i++) {
            for(int j = i + 1; j < array.length; j++) {
                if(array[j].compareTo(array[i]) < 0) {
                    Person tempPerson = array[j];
                    array[j] = array[i];
                    array[i] = tempPerson;
                }
            }
        }
    }
}
