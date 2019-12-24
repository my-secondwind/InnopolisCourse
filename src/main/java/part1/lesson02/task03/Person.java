package part1.lesson02.task03;

import java.util.Objects;

/**
 * Person
 *
 * Describes person via his age, sex and name.
 * Implements interface Comparable.
 *
 * @author Ekaterina Belolipetskaya
 */
public class Person implements Comparable<Person>{
    private int age;
    private Sex sex;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", sex=" + sex.getDisplayName() +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * Comparison based on the next algorithm:
     *
     * -man goes first
     * -higher on the list one who is older
     * -names are sorted alphabetically
     * @param person - the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     *             is less than, equal to, or greater than the specified object.
     * @throws IdentityException - if the specified objects are identical.
     */
    @Override
    public int compareTo(Person person) throws IdentityException{
        if (this == person) return 0;

        if (this.sex.equals(Sex.MAN) && person.sex.equals(Sex.WOMAN)) return 3;
        if (this.sex.equals(Sex.WOMAN) && person.sex.equals(Sex.MAN)) return -3;

        if (this.age>person.age) return 2;
        if (this.age<person.age) return -2;

        if (this.name.compareTo(person.name)<0) return 1;
        if (this.name.compareTo(person.name)>0) return -1;

        throw new IdentityException();
    }
}
