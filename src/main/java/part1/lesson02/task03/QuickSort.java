package part1.lesson02.task03;

import java.util.List;

/**
 * QuickSort
 *
 * Implements sorting algorithm, which is leveraging the divide-and-conquer principle.
 *
 * @author Ekaterina Belolipetskaya
 */
public class QuickSort implements Sortable {
    @Override
    public void sort(List<Person> arrayList) {
        int start = 0;
        int end = arrayList.size() - 1;
        quickSort(arrayList, start, end);
    }

    private void quickSort(List<Person> arrayList, int start, int end) {
        if (arrayList.size() == 0) return;

        if (start >= end) return;

        int middle = start - (start - end) / 2;
        Person cur = arrayList.get(middle);

        int i = start, j = end;
        while (i < j) {
            try {
                while (i < middle && arrayList.get(i).compareTo(cur) >= 0) {
                    i++;
                }
            } catch (IdentityException e) {
                if (!exceptionSet.contains(arrayList.get(i)))
                    exceptionSet.add(arrayList.get(i));
                if (i < middle) {
                    i++;
                }
            }

            try {
                while (j > middle && arrayList.get(j).compareTo(cur) <= 0) {
                    j--;
                }
            } catch (IdentityException e) {
                if (!exceptionSet.contains(arrayList.get(j)))
                    exceptionSet.add(arrayList.get(j));
                if (j > middle) {
                    j--;
                }
            }

            if (i < j) {
                swap(arrayList, i, j);
                if (i == middle)
                    middle = j;
                else if (j == middle)
                    middle = i;
            }

        }

        quickSort(arrayList, start, middle);
        quickSort(arrayList, middle + 1, end);
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
