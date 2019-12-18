package part1.lesson09.task01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.tools.ToolProvider;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static part1.lesson09.task01.Main.CLASS_FILE_EXTENSION;
import static part1.lesson09.task01.Main.JAVA_FILE_EXTENSION;

/**
 * FileCompiler
 * <p>
 * Compile file with {@code fileName}.
 *
 * @author Ekaterina Belolipetskaya
 */
public class FileCompiler {
    private static final Logger LOGGER = LogManager.getLogger(FileCompiler.class);
    public static final String COMPILATION_STARTS_INFO = "FileCompiler: compilation starts...";
    public static final String COMPILATION_FINISHED_INFO = "Compilation finished - OK.";
    public static final String CLASS_FUNC_NAME = "FileCompiler: compile";


    /**
     * Compile file {@code fileName} by javax tool.
     *
     * @param fileName - class file to be compiled.
     */
    public boolean compile(String fileName) {
        try {
            LOGGER.debug(COMPILATION_STARTS_INFO);
            Files.deleteIfExists(Paths.get(fileName + CLASS_FILE_EXTENSION));
            ToolProvider.getSystemJavaCompiler().run(null, null, null, fileName + JAVA_FILE_EXTENSION);
            LOGGER.debug(COMPILATION_FINISHED_INFO);
            return true;
        } catch (IOException e) {
            LOGGER.error(CLASS_FUNC_NAME, e);
        }
        return false;
    }
}