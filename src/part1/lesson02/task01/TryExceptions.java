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
                String str = null;
                System.out.println(str.contains("f"));
            case 1:
                int[] array = new int[7];
                System.out.println(array[7]);
            case 2:
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Введите логин: ");
                String name = reader.readLine();
                System.out.print("Введите пароль: ");
                String password = reader.readLine();
                if (!(name.equals("kate") && password.equals("qwerty")))
                    throw new IllegalAccessException();
        }
    }
}
