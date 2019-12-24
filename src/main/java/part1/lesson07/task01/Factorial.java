package part1.lesson07.task01;

import java.math.BigInteger;

/**
 * Factorial
 *
 * @author Ekaterina Belolipetskaya
 */
class Factorial {
    /**
     * Calculate factorial in simple way by
     * multiplying values in cycle
     * @param startValue for multiplying
     * @param endValue for multiplying
     * @return result of multiplying
     */
    static BigInteger calculate(BigInteger startValue, BigInteger endValue) {
        BigInteger one = new BigInteger("1");
        BigInteger result = new BigInteger("1");
        for (; startValue.compareTo(endValue.add(one)) < 0; startValue = startValue.add(one)) {
            result = result.multiply(startValue);
        }
        return result;
    }
}
