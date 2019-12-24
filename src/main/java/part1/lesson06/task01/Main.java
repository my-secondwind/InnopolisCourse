package part1.lesson06.task01;

/**
 * Try textParser
 *
 * @author Ekaterina Belolipetskaya
 */
public class Main {
    private static final String INPUTFILENAME = "./src/inputfiles/text.txt";
    private static final String OUTPUTFILENAME = "./out/result.txt";

    public static void main(String[] args) {
        TextParser textParser = new TextParser(INPUTFILENAME);
        textParser.parse();
        System.out.println(textParser);
        textParser.dumpToFile(OUTPUTFILENAME);
    }
}
