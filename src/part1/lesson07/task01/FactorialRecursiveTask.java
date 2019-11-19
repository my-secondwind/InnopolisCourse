package part1.lesson07.task01;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * FactorialRecursiveTask
 * <p>
 * Recursive task for computing factorial
 *
 * @author Ekaterina Belolipetskaya
 */
public class FactorialRecursiveTask extends RecursiveTask<BigInteger> {

    private BigInteger startValue;
    private BigInteger endValue;
    private static final BigInteger THRESHOLD = BigInteger.valueOf(2000);

    FactorialRecursiveTask(BigInteger startValue, BigInteger endValue) {
        this.startValue = startValue;
        this.endValue = endValue;
    }

    /**
     * If delta between start and end value is lower or equals THRESHOLD,
     * factorial is calculated in one task/thread.
     * If delta bigger, task is decomposed to two separate tasks and
     * these tasks are invoked by ForkJoinPool.
     *
     * @return result of multiply of start and end value
     */
    @Override
    protected BigInteger compute() {
        if (endValue.subtract(startValue).compareTo(THRESHOLD) <= 0) {
            return Factorial.calculate(startValue, endValue);
        }
        // mid = startValue + (endValue-startValue)/2
        BigInteger mid = startValue.add(endValue.subtract(startValue).divide(BigInteger.valueOf(2)));
        ForkJoinTask<BigInteger> left = new FactorialRecursiveTask(startValue, mid);
        ForkJoinTask<BigInteger> right = new FactorialRecursiveTask(mid.add(BigInteger.valueOf(1)), endValue);
        ForkJoinTask.invokeAll(right, left);
        BigInteger result = new BigInteger("1");
        result = result.multiply(right.join());
        result = result.multiply(left.join());
        return result;
    }
}
