package part1.lesson09.task01;

import part1.lesson09.task01.fileHandlers.FileCompiler;
import part1.lesson09.task01.fileHandlers.FileExecutor;
import part1.lesson09.task01.fileHandlers.FileMaker;

/**
 * Main
 * <p>
 * The program reads the doWork method code line by line from the console.
 * The code should not require the import of additional classes.
 * After entering an empty line, reading stops and the read lines are added
 * to the body of the public void doWork () method in the SomeClass.java file.
 * The SomeClass.java file is compiled by the program (in runtime) into
 * the SomeClass.class file.
 * The resulting file is loaded into the program using a custom loader
 * The method entered from the console is executed in runtime
 * (called on the instance of the object of the loaded class).
 *
 * @author Ekaterina Belolipetskaya
 */
public class Main {
    public static final String FILE_NAME = "SomeClass";
    public static final String FILE_PATH = "./src/main/java/part1/lesson09/task01/";
    public static final String CLASS_FILE_EXTENSION = ".class";
    public static final String JAVA_FILE_EXTENSION = ".java";

    public static void main(String[] args) throws Exception {
        new FileMaker().makeFile(FILE_PATH + FILE_NAME + JAVA_FILE_EXTENSION);
        new FileCompiler().compile(FILE_NAME, FILE_PATH);
        new FileExecutor().execute(FILE_NAME);
    }
}
