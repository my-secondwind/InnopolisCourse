package part1.lesson11.task01.person;

import part1.lesson11.task01.exceptions.IdentityException;

import java.util.Objects;

/**
 * Person
 * <p>
 * Describes person via his age, sex and name.
 * Implements interface Comparable.
 *
 * @author Ekaterina Belolipetskaya
 */
public class Person implements Comparable<Person> {
    private final String name;
    private int age;
    private final Sex sex;

    public Person(String name, int age, Sex sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                sex == person.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sex);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }

    /**
     * Comparison based on the next algorithm:
     * <p>
     * -man goes first
     * -higher on the list one who is older
     * -names are sorted alphabetically
     *
     * @param person - the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws IdentityException - if the specified objects are identical.
     */
    @Override
    public int compareTo(Person person){
        if (this == person) return 0;

        if (this.sex.equals(Sex.MAN) && person.sex.equals(Sex.WOMAN)) return -3;
        if (this.sex.equals(Sex.WOMAN) && person.sex.equals(Sex.MAN)) return 3;

        if (this.age != person.age)
            return Integer.compare(this.age, person.age);

        if (this.name.compareTo(person.name) != 0)
            return this.name.compareTo(person.name);

        throw new IdentityException();
    }
}
