package part1.lesson06.task02;

/**
 * @author Ekaterina Belolipetskaya
 */
public class Main {
    private static final String PATH = "./out";

    public static void main(String[] args) {
        FileGenerator fileGenerator = new FileGenerator();
        String[] words = new WordsVocabularyGenerator().generate();
        fileGenerator.getFiles(PATH, 4, 2048, words, 2);
    }
}
