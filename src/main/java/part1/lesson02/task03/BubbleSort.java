package part1.lesson02.task03;

import java.util.List;

/**
 * BubbleSort
 *
 * Implements the simplest sorting algorithm that works by repeatedly
 * swapping the adjacent elements if they are in wrong order.
 *
 * @author Ekaterina Belolipetskaya
 */
public class BubbleSort implements Sortable {
    @Override
    public void sort(List<Person> arrayList) {
        for (int i = 0; i < arrayList.size(); i++)
            for (int j = i+1; j < arrayList.size(); j++) {
                try {
                    if (arrayList.get(i).compareTo(arrayList.get(j)) < 0) {
                        swap(arrayList, i, j);
                    }
                } catch (IdentityException e){
                    if (!exceptionSet.contains(arrayList.get(i)))
                        exceptionSet.add(arrayList.get(i));
                }
        }
    }

    /**
     * Swapping the elements i and j in arrayList
     * @param arrayList - list, where swap is made
     * @param i - number of the one element to swap
     * @param j - number of the another element to swap
     */
    private void swap(List<Person> arrayList, int i, int j) {
        Person tmp = arrayList.get(j);
        arrayList.set(j, arrayList.get(i));
        arrayList.set(i, tmp);
    }
}
