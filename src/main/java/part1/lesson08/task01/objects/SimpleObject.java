package part1.lesson08.task01.objects;

/**
 * SimpleObject
 * Created for testing serializer
 *
 * @author Ekaterina Belolipetskaya
 */
public class SimpleObject {
    public static long serialVersionUID = 1L;
    public int simpleInt;
    protected double simpleDouble;
    private String simpleString;

    public SimpleObject(int simpleInt, double simpleDouble, String simpleString) {
        this.simpleInt = simpleInt;
        this.simpleDouble = simpleDouble;
        this.simpleString = simpleString;
    }

    public SimpleObject() {
        this.simpleInt = 1;
        this.simpleDouble = 1.2;
        this.simpleString = "simpleString";
    }

    @Override
    public String toString() {
        return "SimpleObject{" +
                "simpleInt=" + simpleInt +
                ", simpleDouble=" + simpleDouble +
                ", simpleString='" + simpleString + '\'' +
                '}';
    }
}
