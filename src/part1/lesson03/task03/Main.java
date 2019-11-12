package part1.lesson03.task03;

/**
 * You can try functionality of MathBox
 * and ObjectBox classes here
 *
 * @author Ekaterina Belolipetskaya
 */
public class Main {
    public static void main(String[] args) {
        //try to create mathBox
        Number[] numberArray = {1, 4, 7, 8, (byte) 4};
        MathBox mathBox = new MathBox(numberArray);

        //try summator method for mathBox
        System.out.println(mathBox.summator());

        //try to remove Integer
        mathBox.deleteObject(4);

        //try to add Number
        mathBox.addObject(4.);

        //try to dump set
        mathBox.dump();

        //try splitter method for mathBox
        mathBox.splitter(2);

        //try redefined method toString
        System.out.println(mathBox);

        Object[] objectArray = new Object[3];
        ObjectBox<Object> objectObjectBox = new ObjectBox<>(objectArray);
        objectObjectBox.addObject(mathBox);
        objectObjectBox.dump();

    }
}
