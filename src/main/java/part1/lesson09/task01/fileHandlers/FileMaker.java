package part1.lesson09.task01.fileHandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * FileMaker
 * <p>
 * Make file *.class.
 * Read console for method's code.
 *
 * @author Ekaterina Belolipetskaya
 */
public class FileMaker {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileMaker.class);
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
    public void makeFile(String fullFileName) throws IOException {
        readMethodCode();
        saveClassToFile(fullFileName);
    }

    /**
     * Complete class code with necessary strings.
     * Delete file if exist and save new *.java file.
     *
     * @throws IOException if any IO errors occurs.
     */
    private void saveClassToFile(String fullFileName) throws IOException {
        Files.deleteIfExists(Paths.get(fullFileName));
        Files.write(Paths.get(fullFileName), (BEGIN_OF_CLASS + methodCode + END_OF_CLASS).getBytes());
    }
}
