package part1.lesson05.task01.storage;

import part1.lesson02.task03.entity.Person;

import java.util.Objects;

/**
 * домашнее животное
 */
public class Pet implements Comparable<Pet> {

    private int uid;
    private Person owner;
    private String name;
    private float weight;

    public int getUid() {
        return uid;
    }

    void setUid(int uid) {
        this.uid = uid;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Float.compare(pet.weight, weight) == 0 &&
                Objects.equals(owner, pet.owner) &&
                Objects.equals(name, pet.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, name, weight);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "uid=" + uid +
                ", owner=" + owner +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Pet anotherPet) {
        int result = this.getOwner().compareTo(anotherPet.getOwner());
        if (result != 0) return result;
        result = this.getName().compareTo(anotherPet.getName());
        if (result != 0) return result;
        return Float.compare(this.getWeight(), anotherPet.getWeight());
    }
}
