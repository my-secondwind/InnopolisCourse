package part1.lesson02.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * TryIllegalAccessException
 *
 * Generate IllegalAccessException if login/password is incorrect
 *
 * @author Ekaterina Belolipetskaya
 */
public class TryIllegalAccessException {
    public void generateException() throws IllegalAccessException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите логин: ");
        String name = reader.readLine();
        System.out.print("Введите пароль: ");
        String password = reader.readLine();
        if (!("kate".equals(name) && "qwerty".equals(password)))
            throw new IllegalAccessException();    }
}
