package part1.lesson08.task01;

import part1.lesson08.task01.objects.SimpleObject;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Ekaterina Belolipetskaya
 */
public class Main {
    static final String PATH_TO_SERIALIZE = "./out/serialized.txt";

    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, InstantiationException, InvocationTargetException {
        SimpleObject simpleObject = new SimpleObject(123, 34.56, "first");
        new Serializer().serialize(simpleObject, PATH_TO_SERIALIZE);
        SimpleObject simpleObject2 = (SimpleObject) new Serializer().deSerialize(PATH_TO_SERIALIZE);
        System.out.println(simpleObject2);
    }
}
