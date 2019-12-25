package part1.lesson09.task01.fileHandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import part1.lesson09.task01.Worker;
import part1.lesson09.task01.classLoader.CustomClassLoader;

/**
 * FileExecutor
 * <p>
 * Load file with {@code fileName} and execute if.
 *
 * @author Ekaterina Belolipetskaya
 */
public class FileExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileExecutor.class);
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
        ClassLoader classLoader = new CustomClassLoader();
        Class<?> newClass = classLoader.loadClass(fileName);
        Worker newWorker = (Worker) newClass.getConstructor().newInstance();
        newWorker.doWork();
    }
}
