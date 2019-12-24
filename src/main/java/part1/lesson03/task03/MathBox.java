package part1.lesson03.task03;

import java.util.HashSet;
import java.util.Set;

/**
 * MathBox
 * <p>
 * Contains fields uuid and mathSet:
 * uuid is used for equals and hashCode methods
 * mathSet stores set of Number that is defined in Constructor
 * <p>
 * The following methods are defined in this class:
 * summator - returns the summary of all Number in set
 * splitter - replaces all values in set by division result
 * (value/denominator)
 * removeInteger - remove specified Integer value from the set
 * Methods toString, equal and hashCode are redefined.
 *
 * @author Ekaterina Belolipetskaya
 */
public class MathBox extends ObjectBox {

    MathBox(Number[] array) {
        super(array);
    }

    /**
     * Calculate the summary of all Numbers in set
     * Calculate the summary for Integer/Byte/Short, Long
     * and Double/Float values separately. After that
     * summarizes intermediate results.
     *
     * @return the summary of all Numbers in {@code mathSet}
     */
    public  Number summator() {
        int intSum = 0;
        long longSum = 0;
        double doubleSum = 0;

        for (Object object : objectSet) {
            Number number = (Number) object;
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
     *
     * @param denominator the value by which the division occurs
     */
    public void splitter(Number denominator) {
        Set<Number> updatedSet = new HashSet<>();
        for (Object object : objectSet) {
            double result = ((Number) object).doubleValue() / denominator.doubleValue();
            updatedSet.add(result);
        }
        objectSet.clear();
        objectSet = updatedSet;
    }

    /**
     * Adds the specified element to this set if it is not already present
     *
     * @param object element to be added to this set
     * @return {@code true} if this set did not already contain the specified
     * element
     * @throws ClassCastException in case {@code object} isn't Number object
     */
    @Override
    public boolean addObject(Object object) {
        if (object instanceof Number) return super.addObject(object);
        throw new ClassCastException();
    }

    /**
     * Method returns a string that
     * "textually represents" this object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "MathBox{" +
                ", mathSet=" + objectSet +
                '}';
    }
}
