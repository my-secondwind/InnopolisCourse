package part1.lesson06.task02;

/**
 * WordGenerator
 * <p>
 * Implements method that generate words
 * according the following algorithm:
 * A word consists of 1 <= n <= 15 Latin letters.
 * Uses an array of words. Probability of occurrence one of the words from this array
 * in the next sentence (1/probability).
 *
 * @author Ekaterina Belolipetskaya
 */
class WordGenerator {
    private static char[] symbolsArray = new char[26];

    static {
        for (int iChar = 0; iChar < 26; ++iChar) {
            symbolsArray[iChar] = (char) ('a' + (char) iChar);
        }
    }

    /**
     * Generate words according the following algorithm:
     * A word consists of 1 <= n <= 15 Latin letters.
     * Uses an array of words. Probability of occurrence one of the words from this array
     * in the next sentence (1/probability).
     *
     * @param words       array of words
     * @param probability of one of the words in the array {@code words} entering the sentence
     *                    (1 - 100%, 2-50%, 3-33% ets., computing as 1/{@code probability})
     * @return word that suites all of the conditions above
     */
    String generateWorld(String[] words, int probability) {
        if (FileGenerator.RANDOM.nextInt(probability) == 0) {
            return words[FileGenerator.RANDOM.nextInt(words.length)];
        } else {
            StringBuilder word = new StringBuilder();
            int wordLength = FileGenerator.RANDOM.nextInt(14) + 1;

            for (int i = 0; i < wordLength; i++) {
                word.append(symbolsArray[FileGenerator.RANDOM.nextInt(symbolsArray.length)]);
            }
            return word.toString();
        }
    }
}
