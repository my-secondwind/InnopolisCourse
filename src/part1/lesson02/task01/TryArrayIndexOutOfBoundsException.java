package part1.lesson02.task01;

/**
 * ArrayIndexOutOfBoundsException
 *
 * Generate ArrayIndexOutOfBoundsException using index out of bound
 *
 * @author Ekaterina Belolipetskaya
 */
public class TryArrayIndexOutOfBoundsException {
    public void generateException() throws ArrayIndexOutOfBoundsException{
        int[] array = new int[7];
        System.out.println(array[7]);    }
}
