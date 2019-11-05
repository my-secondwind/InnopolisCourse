package part1.lesson02.task01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * TryExceptions
 * Class for trying Exceptions.
 * Generate the following exceptions:
 * - NullPointerException
 * - ArrayIndexOutOfBoundsException
 * - IllegalAccessException
 * depending on random int value (0-2).
 * @author Ekaterina Belolipetskaya
 */
public class TryExceptions {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Random random = new Random();
        int caseException = random.nextInt(3);

        switch (caseException) {
            case 0:
                new TryNullPointerException().generateException();
            case 1:
                new TryArrayIndexOutOfBoundsException().generateException();
            case 2:
                new TryIllegalAccessException().generateException();
        }
    }
}
