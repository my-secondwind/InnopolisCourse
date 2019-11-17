package part1.lesson06.task02;

/**
 * SentenceGenerator
 * <p>
 * Implements method that generate sentences
 * according the following algorithm:
 * The sentence consists of 1 <= n <= 15 words.
 * A sentence may contain commas after arbitrary words.
 * Words are separated by a single space.
 * Capitalized sentence.
 * The sentence ends (.|!|?) + " "
 * Class uses WordGenerator.
 *
 * @author Ekaterina Belolipetskaya
 */
class SentenceGenerator {
    private WordGenerator wordGenerator = new WordGenerator();

    /**
     * Generate sentence
     * according the following algorithm:
     * The sentence consists of 1 <= n <= 15 words.
     * A sentence may contain commas after arbitrary words.
     * Words are separated by a single space.
     * Capitalized sentence.
     * The sentence ends (.|!|?) + " "
     * Uses WordGenerator for generating words.
     * Array of words and probability is used for words generation.
     *
     * @param words       array of words
     * @param probability of one of the words in the array {@code words} entering the sentence
     *                    (1 - 100%, 2-50%, 3-33% ets., computing as 1/{@code probability})
     * @return sentence that suites all of the conditions above
     */
    String generateSentence(String[] words, int probability) {
        int sentenceLength = FileGenerator.RANDOM.nextInt(14) + 1;

        int nextCommaIn = 0;

        StringBuilder sentence = new StringBuilder();

        for (int i = 0; i < sentenceLength; i++) {
            if (i != 0) {
                if (nextCommaIn == 0) {
                    sentence.append(',');
                    nextCommaIn = FileGenerator.RANDOM.nextInt(20);
                } else {
                    nextCommaIn--;
                }
                sentence.append(' ');
            } else {
                nextCommaIn = FileGenerator.RANDOM.nextInt(20);
            }
            sentence.append(wordGenerator.generateWorld(words, probability));
        }

        int punctMark = FileGenerator.RANDOM.nextInt(100);

        sentence.append(punctMark < 80 ? '.' : (punctMark < 95 ? "!" : '?'));
        return String.valueOf(sentence.charAt(0)).toUpperCase() + sentence.substring(1);
    }
}
