package part1.lesson05.task01.storage;

import part1.lesson05.task01.exceptions.DuplicatePetException;
import part1.lesson05.task01.exceptions.PetNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
 * картотека домашних животных
 * хранит отсортированные данные
 * и индексы для быстрого поиска
 */
public class PetFile {

    private TreeSet<Pet> sortedPets = new TreeSet<>();
    private HashMap<Integer, Pet> idPets = new HashMap<>();
    private HashMap<String, List<Pet>> findPets = new HashMap<>();
    private int nextGenUid = 1;

    /**
     * добавляет животное в картотеку
     *
     * @param pet новое животное
     * @throws DuplicatePetException проверка на уникальность
     */
    public void addPet(Pet pet) throws DuplicatePetException {
        addPetWithDuplicateCheck(pet);
        pet.setUid(nextGenUid++);
        addIndexes(pet);
    }

    private void addPetWithDuplicateCheck(Pet pet) throws DuplicatePetException {
        if (!sortedPets.add(pet)) {
            throw new DuplicatePetException("Найден дубликат домашнего животного " + pet);
        }
    }

    private void addIndexes(Pet pet) {
        idPets.put(pet.getUid(), pet);
        List<Pet> listPet = findPets.get(pet.getName());
        if (listPet == null) {
            listPet = new ArrayList<>();
            findPets.put(pet.getName(), listPet);
        }
        listPet.add(pet);
    }

    /**
     * поиск животного по кличке
     *
     * @param name кличка животного
     * @return найденное животное
     */
    public List<Pet> findPetByName(String name) {
        List<Pet> listPet = findPets.get(name);
        if (listPet == null) {
            listPet = new ArrayList<>();
        }
        return listPet;
    }

    private Pet findPetByUid(int uid) {
        return idPets.get(uid);
    }

    /**
     * изменение данных животного по его идентификатору]
     *
     * @param uid идентификатор животного
     * @param pet данные животного
     * @throws PetNotFoundException  идентификатор не найден
     * @throws DuplicatePetException изменение данных приводит к появлению дубликата
     */
    public void updatePetByUid(int uid, Pet pet) throws PetNotFoundException, DuplicatePetException {
        Pet oldPet = findPetByUid(uid);
        if (oldPet == null) {
            throw new PetNotFoundException("Животное с uid = " + pet.getUid() + " не найдено");
        }
        addPetWithDuplicateCheck(pet);
        sortedPets.remove(oldPet);
        findPets.get(oldPet.getName()).remove(oldPet);
        pet.setUid(uid);
        addIndexes(pet);
    }

    /**
     * вывод на экран списка животных в отсортированном порядке
     */
    public void dump() {
        for (Pet pet : sortedPets) {
            System.out.println(pet);
        }
    }
}
