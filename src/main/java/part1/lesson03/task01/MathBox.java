package part1.lesson03.task01;

import java.util.*;

/**
 * MathBox
 *
 * Contains fields uuid and mathSet:
 * uuid is used for equals and hashCode methods
 * mathSet stores set of Number that is defined in Constructor
 *
 * The following methods are defined in this class:
 * summator - returns the summary of all Number in set
 * splitter - replaces all values in set by division result
 *  (value/denominator)
 * removeInteger - remove specified Integer value from the set
 * Methods toString, equal and hashCode are redefined.
 *
 * @author Ekaterina Belolipetskaya
 */
public class MathBox{
    private final UUID uuid;
    private Set<Number> mathSet;

    MathBox(Number[] array) {
        this.uuid = UUID.randomUUID();
        this.mathSet = new HashSet<>(Arrays.asList(array));
    }

    /**
     * Calculate the summary of all Numbers in set
     * Calculate the summary for Integer/Byte/Short, Long
     * and Double/Float values separately. After that
     * summarizes intermediate results.
     *
     * @return the summary of all Numbers in {@code mathSet}
     */
    public Number summator() {
        int intSum = 0;
        long longSum = 0;
        double doubleSum = 0;

        for (Number number : mathSet) {
            if (number instanceof Integer ||
                    number instanceof Byte ||
                    number instanceof Short) {
                intSum = intSum + number.intValue();
            }
            if (number instanceof Long) {
                longSum = longSum + number.longValue();
            }
            if (number instanceof Double ||
                    number instanceof Float) {
                doubleSum = doubleSum + number.doubleValue();
            }
        }

        if (doubleSum != 0) {
            return doubleSum + (double) intSum + (double) longSum;
        }

        if (longSum != 0) {
            return longSum + (long) intSum;
        }

        return intSum;
    }

    /**
     * Performing sequential division of all elements stored in {@code mathSet}
     * into a denominator, which is an argument to the method.
     * The data stored in {@code mathSet} is completely replaced by the division results.
     * @param denominator the value by which the division occurs
     */
    public void splitter(Number denominator){
        Set<Number> updatedSet = new HashSet<>();
        for (Number number :mathSet) {
            double result = number.doubleValue()/denominator.doubleValue();
            updatedSet.add(result);
        }
        mathSet.clear();
        mathSet = updatedSet;
    }

    /**
     * Remove specified Integer value, if the following
     * contains in {@code mathSet}
     * @param value object to be removed from this set, if present
     * @return {@code true} if this set contained the specified element
     */
    public boolean removeInteger(Integer value){
        return mathSet.remove(value);
    }

    /**
     * Method returns a string that
     * "textually represents" this object.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "MathBox{" +
                "mathSet=" + mathSet +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Objects are the same if its uuids are the same.
     * @param o the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     *          argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MathBox)) return false;
        MathBox mathBox = (MathBox) o;
        return uuid.equals(mathBox.uuid);
    }

    /**
     * Returns a hash code value for the object.
     * Hash code computes based on uuid value of the object
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

}
