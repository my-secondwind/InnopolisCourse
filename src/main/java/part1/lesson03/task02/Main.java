package part1.lesson03.task02;

/**
 * You can try ObjectBox
 *
 * @author Ekaterina Belolipetskaya
 */
public class Main {
    public static void main(String[] args) {
        Number[] numberArray = {1, 4, 7, 8, (byte) 4};
        ObjectBox<Number> mathBox = new ObjectBox<>(numberArray);
        mathBox.dump();
        mathBox.addObject(1.);
        mathBox.dump();
        mathBox.deleteObject(4);
        mathBox.dump();
    }
}
