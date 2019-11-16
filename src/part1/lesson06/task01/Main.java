package part1.lesson06.task01;

/**
 * Try textParser
 *
 * @author Ekaterina Belolipetskaya
 */
public class Main {
    private static final String FILENAME = "./src/inputfiles/text.txt";

    public static void main(String[] args) {
        TextParser textParser = new TextParser(FILENAME);
        textParser.parse();
        System.out.println(textParser);
    }
}
