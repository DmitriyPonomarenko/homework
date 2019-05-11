package part1.lesson05.task01;

import part1.lesson02.task03.entity.Person;
import part1.lesson02.task03.entity.Sex;
import part1.lesson02.task03.util.RussianNameGen;
import part1.lesson05.task01.storage.Pet;
import part1.lesson05.task01.storage.PetFile;
import part1.lesson05.task01.exceptions.DuplicatePetException;
import part1.lesson05.task01.exceptions.PetNotFoundException;

/**
 * использование картотеки домашних животных
 */
public class Main {

    private static final int MIN_NAME_LENGTH = 3;
    private static final int MAX_NAME_LENGTH = 10;
    private static final float MAX_WEIGHT = 100;

    public static void main(String[] args) {
        PetFile petFile = new PetFile();
        Pet pet = null;
        String oldName = null;
        try {
            for (int i = 1; i <= 10; i++) {
                pet = generateNewPet();
                petFile.addPet(pet);
                if (i == 5) {
                    oldName = pet.getName();
                }
            }
            petFile.addPet(pet);
        } catch (DuplicatePetException e) {
            System.out.println(e.getMessage());
        }
        petFile.dump();
        System.out.println();
        pet = generateNewPet();
        try {
            petFile.updatePetByUid(5, pet);
        } catch (PetNotFoundException | DuplicatePetException e) {
            System.out.println(e.getMessage());
        }
        petFile.dump();
        System.out.println("Поиск по старому имени " + oldName);
        System.out.println(petFile.findPetByName(oldName));
        System.out.println("Поиск по новому имени " + pet.getName());
        System.out.println(petFile.findPetByName(pet.getName()));
    }

    /**
     * генерация случайных данных
     *
     * @return домашнее жтвотное
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
