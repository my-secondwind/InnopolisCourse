package part1.lesson08.task02;

import part1.lesson08.task02.objects.NotSimpleObject;
import part1.lesson08.task02.objects.SimpleObject;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Ekaterina Belolipetskaya
 */
public class Main {
    static final String PATH_TO_SERIALIZE = "./out/serialized.txt";
    static final Serializer SERIALIZER = new Serializer();

    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, InstantiationException, InvocationTargetException {
        SimpleObject simpleObject = new SimpleObject();
        SERIALIZER.serialize(simpleObject, PATH_TO_SERIALIZE);
        SimpleObject simpleObject2 = (SimpleObject) SERIALIZER.deSerialize(PATH_TO_SERIALIZE);
        System.out.println(simpleObject2);

        NotSimpleObject notSimpleObject = new NotSimpleObject();
        SERIALIZER.serialize(notSimpleObject, PATH_TO_SERIALIZE);
        NotSimpleObject notSimpleObject12 = (NotSimpleObject) SERIALIZER.deSerialize(PATH_TO_SERIALIZE);
        System.out.println(notSimpleObject12);

    }
}
