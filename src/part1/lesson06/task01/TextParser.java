package part1.lesson06.task01;

import java.io.*;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * TextParser
 * <p>
 * Parse text to separate words
 * Text is getting from file {@code fileName}
 * Words is storing in {@code wordSet}
 * <p>
 * Implements method {@code parse} that parsing
 * specified file to set of words
 * <p>
 * Overrated methods: toString, equals and hashCode.
 *
 * @author Ekaterina Belolipetskaya
 */
public class TextParser {
    private String fileName;
    private Set<String> wordSet;

    public TextParser(String fileName) {
        this.fileName = fileName;
        wordSet = new TreeSet<>();
    }

    /**
     * Parse {@code fileName} to set of word.
     * Method is case insensitive.
     * One word in different cases is different words.
     */
    public void parse() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            while (reader.ready()) {
                String tempString = reader.readLine();
                String[] massiveString = tempString.trim().split("\\P{L}+");
                for (String string : massiveString) {
                    if ("".equals(string)) continue;
                    wordSet.add(string.toLowerCase());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Output set of word to specified file
     *
     * @param outputFineName where set should be saved
     */
    public void dumpToFile(String outputFineName) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFineName)))) {
            for (String s : wordSet) {
                writer.write(s + " ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "TextParser: \n" +
                "fileName='" + fileName + "',\n" +
                "wordMap=" + wordSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextParser)) return false;
        TextParser that = (TextParser) o;
        return fileName.equals(that.fileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName);
    }
}
