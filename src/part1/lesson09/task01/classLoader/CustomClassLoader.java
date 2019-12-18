package part1.lesson09.task01.classLoader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import static part1.lesson09.task01.Main.CLASS_FILE_EXTENSION;
import static part1.lesson09.task01.Main.FILE_NAME;

/**
 * CustomClassLoader
 * <p>
 * Provides custom class loader for class in {@code FILE_NAME}.
 *
 * @author Ekaterina Belolipetskaya
 */
public class CustomClassLoader extends ClassLoader {
    private static final Logger LOGGER = LogManager.getLogger(CustomClassLoader.class);
    public static final String CLASS_FUNC_NAME = "CustomClassLoader: findClass";
    public static final String FIND_CLASS_INFO = "CustomClassLoader: findClass starts work: ";

    /**
     * Loads the class with the specified <a href="#binary-name">binary name</a>.
     * This method searches for classes in the same manner as the {@link
     * #loadClass(String, boolean)} method in parent class ClassLoader.
     * It is invoked if necessary.
     *
     * @param name The <a href="#binary-name">binary name</a> of the class
     * @return The resulting {@code Class} object
     * @throws ClassNotFoundException if the class was not found
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (Objects.equals(FILE_NAME, name)) {
            return findClass(name);
        }
        return super.loadClass(name);
    }

    /**
     * Finds the class with the specified <a href="#binary-name">binary name</a>.
     * This method should be overridden by class loader implementations that
     * follow the delegation model for loading classes, and will be invoked by
     * the {@code loadClass} method after checking the
     * parent class loader for the requested class.
     *
     * @param name The <a href="#binary-name">binary name</a> of the class
     * @return The resulting {@code Class} object
     * @throws ClassNotFoundException if the class could not be found
     */

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        LOGGER.debug(FIND_CLASS_INFO + name);
        if (Objects.equals(FILE_NAME, name)) {
            try {
                byte[] bytes = Files.readAllBytes(Paths.get(FILE_NAME + CLASS_FILE_EXTENSION));
                return defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                LOGGER.error(CLASS_FUNC_NAME, e);
            }
        }
        return super.findClass(name);
    }
}