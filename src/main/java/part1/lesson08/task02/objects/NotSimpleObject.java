package part1.lesson08.task02.objects;

import part1.lesson08.task02.objects.SimpleObject;

/**
 * @author Ekaterina Belolipetskaya
 */
public class NotSimpleObject {
    public static long serialVersionUID = 1L;
    public int notSimpleInt;
    protected double notSimpleDouble;
    private SimpleObject simpleObject;
    private String notSimpleString;

    public NotSimpleObject(int notSimpleInt, double notSimpleDouble, String notSimpleString, SimpleObject simpleObject) {
        this.notSimpleInt = notSimpleInt;
        this.notSimpleDouble = notSimpleDouble;
        this.notSimpleString = notSimpleString;
        this.simpleObject = simpleObject;
    }

    public NotSimpleObject() {
        this.notSimpleInt = 1;
        this.notSimpleDouble = 1.2;
        this.notSimpleString = "simpleString";
        this.simpleObject = new SimpleObject(1, 1.34, "second");
    }

    @Override
    public String toString() {
        return "NotSimpleObject{" +
                "notSimpleInt=" + notSimpleInt +
                ", notSimpleDouble=" + notSimpleDouble +
                ", notSimpleString='" + notSimpleString + '\'' +
                ", simpleObject=" + simpleObject +
                '}';
    }
}