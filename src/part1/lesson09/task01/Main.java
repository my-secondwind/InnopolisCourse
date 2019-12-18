package part1.lesson09.task01;

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
    public static final String CLASS_FILE_EXTENSION = ".class";
    public static final String JAVA_FILE_EXTENSION = ".java";

    public static void main(String[] args) throws Exception {
        new FileMaker().makeFile();
        new FileCompiler().compile(FILE_NAME);
        new FileExecutor().execute(FILE_NAME);
    }
}
