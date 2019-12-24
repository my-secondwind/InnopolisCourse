package part1.lesson09.task01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import part1.lesson09.task01.classLoader.CustomClassLoader;

/**
 * FileExecutor
 * <p>
 * Load file with {@code fileName} and execute if.
 *
 * @author Ekaterina Belolipetskaya
 */
public class FileExecutor {
    private static final Logger LOGGER = LogManager.getLogger(FileExecutor.class);
    public static final String EXECUTION_STARTS_INFO = "FileExecutor: execution starts...";

    /**
     * Load file {@code fileName} by custom class loader.
     * Creat the object of new class and execute its method.
     *
     * @param fileName - file with class code to be loader and
     *                 executed.
     * @throws Exception - if any errors occurs.
     */
    public void execute(String fileName) throws Exception {
        LOGGER.debug(EXECUTION_STARTS_INFO);
        ClassLoader cl = new CustomClassLoader();
        Class<?> newClass = cl.loadClass(fileName);
        Worker newWorker = (Worker) newClass.getConstructor().newInstance();
        newWorker.doWork();
    }
}
