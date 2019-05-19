package part1.lesson08;

import part1.lesson02.task03.entity.Person;
import part1.lesson02.task03.entity.Sex;
import part1.lesson02.task03.util.RussianNameGen;
import part1.lesson05.task01.storage.Pet;
import part1.lesson08.task02.Serializator;

/**
 * пример на сериализацию/десериализацию объекта
 */
public class Main {

    private static final int MIN_NAME_LENGTH = 3;
    private static final int MAX_NAME_LENGTH = 10;
    private static final float MAX_WEIGHT = 100;

    public static void main(String[] args) throws Exception {
        Pet pet = generateNewPet();
        System.out.println(pet);
        Serializator.serialize(pet, "Test.txt");
        Pet pet2 = (Pet) Serializator.deSerialize("Test.txt");
        System.out.println(pet2);
    }

    /**
     * генерация случайных данных
     *
     * @return домашнее животное
     */
    private static Pet generateNewPet() {
        Person person = new Person();
        person.setAge((int) (Math.random() * 101));
        if (Math.random() <= 0.5) {
            person.setSex(Sex.MAN);
        } else {
            person.setSex(Sex.WOMAN);
        }
        person.setName(RussianNameGen.getRandomName((int) (Math.random() * (MAX_NAME_LENGTH - MIN_NAME_LENGTH + 1) + MIN_NAME_LENGTH)));
        Pet pet = new Pet();
        pet.setOwner(person);
        pet.setName(RussianNameGen.getRandomName((int) (Math.random() * (MAX_NAME_LENGTH - MIN_NAME_LENGTH + 1) + MIN_NAME_LENGTH)));
        pet.setWeight((float) (Math.random() * MAX_WEIGHT));
        return pet;
    }

}
