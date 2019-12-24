package part1.lesson12.task01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.LockSupport;

/**
 * MemoryLeakInHeap
 * IMPORTANT: start program with the following VM option:
 * -XX:+UseParallelGC -Xmx100m
 * @author Ekaterina Belolipetskaya
 */
public class MemoryLeakInHeap {
    static final List<Object[]> LIST = new ArrayList<>();
    static final Random RANDOM = new Random();
    static final int LOOP_COUNT = 100_000_000;

    /**
     * Start program with the following VM option:
     * -XX:+UseParallelGC -Xmx100m
     * <p>
     * In this case memory leak in Java heap space will be demonstrated.
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     * will be generated.
     */
    public static void getMemoryLeakInHeap() {
        for (int i = 0; i < LOOP_COUNT; i++) {
            LIST.add(new Object[RANDOM.nextInt(1000)]);
            if (i % 10 == 0) {
                LIST.remove(0);
                LockSupport.parkNanos(1000);
            }
        }
        System.out.println(LIST.size());
    }

    public static void main(String[] args) {
        getMemoryLeakInHeap();
    }
}
