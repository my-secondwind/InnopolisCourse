package part1.lesson06.task02;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * FileGenerator
 * <p>
 * Implements methods that generate files according
 * the following algorithm:
 * The text consists of paragraphs.
 * THere are 1 <= n <= 20 sentences in one paragraph.
 * At the end of the paragraph is a line break and a carriage return.
 * Class uses SentenceGenerator for generating sentences.
 *
 * @author Ekaterina Belolipetskaya
 */
class FileGenerator {
    static final Random RANDOM = new Random();

    private SentenceGenerator sentenceGenerator = new SentenceGenerator();

    /**
     * Method that is called for generating files
     *
     * @param path        where files should be saved
     * @param n           number of files
     * @param size        of the files (in bytes)
     * @param words       array of words
     * @param probability of one of the words in the array {@code words} entering the sentence
     *                    (1 - 100%, 2-50%, 3-33% ets., computing as 1/{@code probability})
     */
    void getFiles(String path, int n, int size, String[] words, int probability) {
        for (int i = 0; i < n; i++) {
            generateFile(generateFileName(path, i), size, words, probability);
        }
    }

    /**
     * Generate text file name
     *
     * @param path       where files should be saved
     * @param fileNumber number of the file
     * @return full file name
     */
    private String generateFileName(String path, int fileNumber) {
        return path + File.separator + "textFile" + fileNumber + ".txt";
    }

    /**
     * Generate text by the algorithm:
     * The text consists of paragraphs.
     * There are 1 <= n <= 20 sentences in one paragraph.
     * At the end of the paragraph is a line break and a carriage return.
     * SentenceGenerator id used for generating sentences.
     * Array of words and probability is used for words generation.
     *
     * @param fullFileName full file name (path+fileName)
     * @param size         of the file (in bytes)
     * @param words        array of words
     * @param probability  of one of the words in the array {@code words} entering the sentence
     *                     (1 - 100%, 2-50%, 3-33% ets., computing as 1/{@code probability})
     */
    private void generateFile(String fullFileName, int size, String[] words, int probability) {
        int paragraphLength = 0;
        int sentenceCounter = 0;
        byte[] paragraphDelimiter = new byte[]{'\r', '\n'};
        int currentFileSize = 0;

        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fullFileName))) {
            while (true) {
                if (sentenceCounter > 0) {
                    outputStream.write(32);
                    currentFileSize++;

                    if (paragraphLength == 0) {
                        outputStream.write(paragraphDelimiter);
                        currentFileSize += 2;
                        paragraphLength = RANDOM.nextInt(19) + 1;
                    }
                    paragraphLength--;
                }

                String sentence = sentenceGenerator.generateSentence(words, probability);
                int remainFileSize = size - (currentFileSize + sentence.length());
                if (remainFileSize < 0) {
                    sentence = sentence.substring(0, size - currentFileSize - 1) + "."; //cut sentence to fit the file size
                } else if (remainFileSize > 0 && remainFileSize < 4) { // min sentence size 3 (space + one character + one punct)
                    sentence = sentence.substring(0, sentence.length() - 3); //cut punctuation mark and /r/n if present
                    sentence = sentence + "abcdf".substring(0, remainFileSize - 1) + "."; //add symbols to fill full the file
                }
                outputStream.write(sentence.getBytes());
                currentFileSize += sentence.length();
                if (currentFileSize == size) break;
                sentenceCounter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
