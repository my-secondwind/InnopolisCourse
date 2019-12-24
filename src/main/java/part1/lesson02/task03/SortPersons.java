package part1.lesson02.task03;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for testing two different methods of sorting algorithms.
 * Generate arrayList of Persons.
 * Sort the same arrayLists by
 *  BubbleSort and
 *  QuickSort.
 * Print out sorted arrays and running time of these two sorting algorithms.
 *
 * @author Ekaterina Belolipetskaya
 */
public class SortPersons {
    public static List<Person> personsList = new ArrayList<>();
    private static PersonGenerator personGenerator = new PersonGenerator();

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            personsList.add(personGenerator.generate());
        }

        List<Person> personsListClone = new ArrayList<>(personsList);

        Sortable quickSort = new QuickSort();
        long timeStart = System.currentTimeMillis();
        quickSort.sort(personsListClone);
        long timeSpent=System.currentTimeMillis()-timeStart ;
        printInfoAboutResult(timeSpent, quickSort, personsListClone);

        Sortable bubbleSort = new BubbleSort();
        timeStart = System.currentTimeMillis();
        bubbleSort.sort(personsList);
        timeSpent=System.currentTimeMillis()-timeStart ;
        printInfoAboutResult(timeSpent, bubbleSort, personsList);

    }
    private static void printInfoAboutResult(long timeSpent, Sortable sort, List<Person> list){
        System.out.println("Массив отсортирован методом " + sort.getClass().getSimpleName() +
                " за " + timeSpent + " мс");
        if (sort.exceptionSet.size()!=0) {
            System.out.println("Для следующих элементов нельзя определить порядок " +
                    "следования, т.к. нарушена уникальность объекта:");
            System.out.println(sort.exceptionSet);
        }
        System.out.println("Отсортированный массив:");

        for (Person person :
                list) {
            System.out.println(person);
        }
    }
}
