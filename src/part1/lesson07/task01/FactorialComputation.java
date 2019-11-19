package part1.lesson07.task01;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * FactorialComputation
 * <p>
 * Generate random array.
 * Sort it.
 * Compute factorials for all values from random
 * array.
 *
 * @author Ekaterina Belolipetskaya
 */
class FactorialComputation {
    private final Random random = new Random();
    private List<BigInteger> valuesList = new ArrayList<>();
    private List<BigInteger> factorialList = new ArrayList<>();

    void computeForRandomArray() {
        setValues();
        Collections.sort(valuesList);
        computeAllFactorials();
    }

    /**
     * Set random array
     */
    private void setValues() {
        int listSize = 20;
        for (int i = 0; i < listSize; i++) {
            valuesList.add(BigInteger.valueOf(random.nextInt(100000)));
            System.out.print(valuesList.get(i) + " ");
        }
        System.out.println();
    }

    /**
     * Made a list of factorials to use them for further calculations.
     * In cycle calls computeFactorial for each value in random array.
     */
    private void computeAllFactorials() {
        factorialList.add(computeFactorial(BigInteger.valueOf(1), valuesList.get(0)));
        for (int i = 1; i < valuesList.size(); i++) {
            BigInteger factorial = factorialList.get(i - 1).multiply(computeFactorial(valuesList.get(i - 1).add(BigInteger.valueOf(1)), valuesList.get(i)));
            System.out.println("Factorial " + valuesList.get(i) + " = " + factorial);
            factorialList.add(factorial);
        }
    }

    /**
     * Compute factorial part from start to end.
     *
     * @param start value for factorial calculation
     * @param end   value for factorial calculation
     * @return result of computing factorial part from start to end.
     */
    private BigInteger computeFactorial(BigInteger start, BigInteger end) {
        ForkJoinPool pool = new ForkJoinPool();
        BigInteger number1 = BigInteger.valueOf(1);
        BigInteger factorial = pool.invoke(new FactorialRecursiveTask(start, end));
        pool.shutdown();
        return factorial;
    }
}
