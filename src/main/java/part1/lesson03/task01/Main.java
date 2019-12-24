package part1.lesson03.task01;

/**
 * You can try functionality of MathBox class here
 *
 * @author Ekaterina Belolipetskaya
 */
public class Main {
    public static void main(String[] args) {
        //try to create mathBox
        Number[] numberArray = {1, 4, 7, 8, (byte)4};
        MathBox mathBox = new MathBox(numberArray);

        //try summator method for mathBox
        System.out.println(mathBox.summator());

        //try to remove Integer
        mathBox.removeInteger(4);

        //try splitter method for mathBox
        mathBox.splitter(2);

        //try redefined method toString
        System.out.println(mathBox);
    }
}
