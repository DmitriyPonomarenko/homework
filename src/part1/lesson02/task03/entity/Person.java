package part1.lesson02.task03.entity;

import java.util.Objects;

/**
 * человек
 */
public class Person implements Comparable<Person> {

    private int age;
    private Sex sex;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0 || age > 100) throw new IllegalArgumentException("0 <= age <= 100");
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Person anotherPerson) {
        if (this.getSex() == Sex.MAN && anotherPerson.getSex() == Sex.WOMAN) return -1;
        if (this.getSex() == Sex.WOMAN && anotherPerson.getSex() == Sex.MAN) return 1;
        if (this.getAge() != anotherPerson.getAge()) return -Integer.compare(this.getAge(), anotherPerson.getAge());
        return this.getName().compareTo(anotherPerson.getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            Person anotherPerson = (Person) obj;
            return (this.getName().equals(anotherPerson.getName()) && this.getAge() == anotherPerson.getAge());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }

    @Override
    public String toString() {
        return "age = " + this.getAge() + "; sex = " + this.getSex() + "; name = '" + this.getName() + "'";
    }

}