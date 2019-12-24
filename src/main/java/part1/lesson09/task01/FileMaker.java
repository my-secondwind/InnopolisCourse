package part1.lesson09.task01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import static part1.lesson09.task01.Main.FILE_NAME;
import static part1.lesson09.task01.Main.JAVA_FILE_EXTENSION;

/**
 * FileMaker
 * <p>
 * Make file *.class.
 * Read console for method's code.
 *
 * @author Ekaterina Belolipetskaya
 */
public class FileMaker {
    private static final Logger LOGGER = LogManager.getLogger(FileMaker.class);
    private static final String BEGIN_OF_CLASS = "import part1.lesson09.task01.Worker; public class SomeClass implements Worker {" + System.lineSeparator() +
            "public void doWork(){" + System.lineSeparator();
    private static final String END_OF_CLASS = "}}";
    public static final String EMPTY_STRING = "";
    public static final String READ_CONSOLE_INFO = "FileMaker: start reading console for method's code...";
    private StringBuilder methodCode = new StringBuilder();

    /**
     * Read method's code from console until empty string entered.
     *
     * @throws IOException if any IO errors occurs.
     */
    private void readMethodCode() throws IOException {
        LOGGER.debug(READ_CONSOLE_INFO);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while (true) {
                line = reader.readLine();
                if (EMPTY_STRING.equals(line)) break;
                methodCode.append(line).append(System.lineSeparator());
            }
        }
    }

    /**
     * Invoke methods that:
     * - read code from console,
     * - complete file and save it to file.
     *
     * @throws IOException if ane IO errors occurs.
     */
    public void makeFile() throws IOException {
        readMethodCode();
        saveClassToFile();
    }

    /**
     * Complete class code with necessary strings.
     * Delete file if exist and save new *.java file.
     *
     * @throws IOException if any IO errors occurs.
     */
    private void saveClassToFile() throws IOException {
        Files.deleteIfExists(Paths.get(FILE_NAME + JAVA_FILE_EXTENSION));
        Files.write(Paths.get(FILE_NAME + JAVA_FILE_EXTENSION), (BEGIN_OF_CLASS + methodCode + END_OF_CLASS).getBytes());
    }
}
