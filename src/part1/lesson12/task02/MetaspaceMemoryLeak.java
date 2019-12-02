package part1.lesson12.task02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.locks.LockSupport;

/**
 * MetaspaceMemoryLeak
 * <p>
 * IMPORTANT: start program with the following VM option:
 * -XX:MaxMetaspaceSize=11m
 *
 * @author Ekaterina Belolipetskaya
 */
public class MetaspaceMemoryLeak {
    static long parkBeforeStartInNanos = 30_000_000_000L;
    static long parkInNanos = 100_000_000L;
    static String fileName = "./src/inputfiles/classes.txt";

    public static void main(String[] args) throws Exception {
        getMetaspaceMemoryLeak();
    }

    /**
     * Start program with the following VM option:
     * -XX:MaxMetaspaceSize=11m
     * <p>
     * In this case memory leak in Metaspace will be demonstrated.
     * Exception in thread "main" java.lang.OutOfMemoryError: Metaspace
     * will be generated.
     *
     * @throws Exception if any error occurs.
     */
    public static void getMetaspaceMemoryLeak() throws Exception{
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            LockSupport.parkNanos(parkBeforeStartInNanos);
            while (reader.ready()) {
                String classPackageName = reader.readLine();
                System.out.println(classPackageName);
                Class oClass = Class.forName(classPackageName);
                LockSupport.parkNanos(parkInNanos);
            }
        }
    }
}
