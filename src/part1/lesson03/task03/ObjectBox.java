package part1.lesson03.task03;

import java.util.*;

/**
 * ObjectBox
 * <p>
 * Contains fields uuid and objectSet:
 * uuid is used for equals and hashCode methods
 * objectSet stores set of Objects that is defined in Constructor
 * <p>
 * The following methods are defined in this class:
 * addObject - add object to the set
 * deleteObject - delete object from the set
 * dump - print objects from the set into the string
 * Methods toString, equal and hashCode are redefined.
 *
 * @author Ekaterina Belolipetskaya
 */
public class ObjectBox<T> {
    private final UUID uuid;
    protected Set<T> objectSet;

    ObjectBox(T[] array) {
        this.uuid = UUID.randomUUID();
        this.objectSet = new HashSet<>(Arrays.asList(array));
    }

    /**
     * Adds the specified element to this set if it is not already present
     *
     * @param object element to be added to this set
     * @return {@code true} if this set did not already contain the specified
     * element
     */
    public boolean addObject(T object) {
        return objectSet.add(object);
    }

    /**
     * Removes the specified element from this set if it is present
     *
     * @param object object to be removed from this set, if present
     * @return {@code true} if this set contained the specified element
     */
    public boolean deleteObject(T object) {
        return objectSet.remove(object);
    }

    /**
     * Print objects from the set into the string
     */
    public void dump() {
        System.out.println(objectSet.toString());
    }

    /**
     * Method returns a string that
     * "textually represents" this object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "ObjectBox{" +
                "uuid=" + uuid +
                ", objectSet=" + objectSet +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Objects are the same if its uuids are the same.
     *
     * @param o the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ObjectBox)) return false;
        ObjectBox<?> objectBox = (ObjectBox<?>) o;
        return uuid.equals(objectBox.uuid);
    }

    /**
     * Returns a hash code value for the object.
     * Hash code computes based on uuid value of the object
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
