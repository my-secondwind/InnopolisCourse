package part1.lesson02.task03;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Describes interface for sorting algorithms.
 *
 * @author Ekaterina Belolipetskaya
 */
public interface Sortable {
    public Set<Person> exceptionSet = new HashSet<>();
    void sort(List<Person> arrayList);
}
