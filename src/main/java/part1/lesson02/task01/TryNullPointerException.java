package part1.lesson02.task01;

/**
 * TryNullPointerException
 *
 * Generate NullPointerException using String-method for null String object
 *
 * @author Ekaterina Belolipetskaya
 */
public class TryNullPointerException {
    public void generateException() throws NullPointerException{
        String str = null;
        System.out.println(str.contains("f"));
    }
}
