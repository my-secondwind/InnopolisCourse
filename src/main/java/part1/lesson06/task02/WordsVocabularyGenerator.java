package part1.lesson06.task02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Implements method that generate vocabulary
 * of latin words.
 *
 * @author Ekaterina Belolipetskaya
 */
public class WordsVocabularyGenerator {
    private static final String FILENAME = "./src/inputfiles/vocab.txt";

    /**
     * Generate vocabulary of n words.
     * N defines by random number from 1 to 1000
     *
     * @return vocabulary of words
     */
    public String[] generate() {
        int vocabularyLength = FileGenerator.RANDOM.nextInt(1000) + 1;
        String[] words = new String[vocabularyLength];
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FILENAME)))) {
            for (int i = 0; i < words.length && reader.ready(); i++) {
                words[i] = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }
}
