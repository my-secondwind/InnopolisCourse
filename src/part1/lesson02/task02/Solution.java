package part1.lesson02.task02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Solution
 *
 * Generate N random numbers. For each number k, calculate the square root of q.
 * If the square of the integer part of q equals k, then the number is printed.
 * If negative numbers appears throws ArithmeticException and printed them.cv
 *
 * @author Ekaterina Belolipetskaya
 */
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        Set<Integer> exceptionSet = new HashSet<>();

        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int number = random.nextInt();

            if (number<0)  {
                exceptionSet.add(number);
                continue;
            }

            double sqtrOfNumber = Math.sqrt(number);

            if (Math.pow((long)sqtrOfNumber, 2)==number)
                System.out.println(number);
        }

        try{
            if (exceptionSet.size()>0)
                throw new ArithmeticException();
        } catch (ArithmeticException e){
            System.out.println("Не удалось извлечь квадратный корень из следующих чисел:");
            System.out.println(exceptionSet);
            throw e;
        }
    }
}
