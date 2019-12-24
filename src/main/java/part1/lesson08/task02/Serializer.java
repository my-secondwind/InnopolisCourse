package part1.lesson08.task02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Serializer
 * <p>
 * Object implements methods that serialize and
 * deserialize flat object
 * (all fields of the object are primitives, or String).
 *
 * @author Ekaterina Belolipetskaya
 */
class Serializer {
    private Object serializedObject;
    private final String serialVersionUIDName = "serialVersionUID";
    private final String serialVersionUIDException = "serialVersionUIDs don't match";
    private final String endObject = "endObject";
    private final String space = " ";
    private String fileName;
    private BufferedReader bufferedReader;

    /**
     * Make serialization of the flat object.
     * Write info about object into the file.
     *
     * @param object to be serialized
     * @param file   name where object will be saved
     * @throws IllegalAccessException if access to the field is denied
     */
    void serialize(Object object, String file) throws IllegalAccessException {
        serializedObject = object;
        fileName = file;
        StringBuilder serializableInfo = getObjectInfo(serializedObject);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(serializableInfo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get info about {@code object} into the string
     *
     * @param object which info is collected to the string
     * @return info about all fields in the object
     * @throws IllegalAccessException if the access to the field is denied
     */
    private StringBuilder getObjectInfo(Object object) throws IllegalAccessException {
        StringBuilder serializableInfo = new StringBuilder();

        Class objClass = object.getClass();
        Field[] objFields = objClass.getDeclaredFields();
        for (Field field : objFields) {
            String fieldInfo = getFieldInfo(field, object);
            if (serialVersionUIDName.equals(field.getName())) {
                serializableInfo.insert(0, fieldInfo + System.lineSeparator());
            } else {
                serializableInfo.append(fieldInfo).append(System.lineSeparator());
            }
        }
        serializableInfo.insert(0, objClass.getName() + System.lineSeparator());
        serializableInfo.append(endObject);
        return serializableInfo;
    }

    /**
     * Get all info about field:
     * modifiers, type, name, value.
     *
     * @param field which info will be returned
     * @return string that consists all field's info
     * @throws IllegalAccessException if access is denied
     */
    private String getFieldInfo(Field field, Object object) throws IllegalAccessException {
        StringBuilder fieldInfo = new StringBuilder();
        fieldInfo.append(field.getModifiers()).append(" ");

        Class<?> fieldType = field.getType();
        fieldInfo.append(fieldType.getSimpleName() + " " + field.getName() + " ");

        field.setAccessible(true);

        if (fieldType == Integer.TYPE) {
            fieldInfo.append(field.getInt(object));
        } else if (fieldType == Long.TYPE) {
            fieldInfo.append(field.getLong(object));
        } else if (fieldType == Byte.TYPE) {
            fieldInfo.append(field.getByte(object));
        } else if (fieldType == Short.TYPE) {
            fieldInfo.append(field.getShort(object));
        } else if (fieldType == Character.TYPE) {
            fieldInfo.append(field.getChar(object));
        } else if (fieldType == Double.TYPE) {
            fieldInfo.append(field.getDouble(object));
        } else if (fieldType == Float.TYPE) {
            fieldInfo.append(field.getFloat(object));
        } else if (fieldType == String.class) {
            String stringValue = (String) field.get(object);
            fieldInfo.append(stringValue);
        } else {
            fieldInfo.append(System.lineSeparator());
            fieldInfo.append(getObjectInfo(field.get(object)));
        }
        return fieldInfo.toString();
    }

    /**
     * Make deserialization of the object
     *
     * @param file name from which object will be loaded
     * @return deserialized object
     * @throws IllegalAccessException    if access is denied
     * @throws InvocationTargetException if the underlying constructor
     *                                   throws an exception.
     * @throws NoSuchFieldException      if field is not found
     * @throws ClassNotFoundException    if class is not found
     * @throws NoSuchMethodException     if method is not found
     * @throws InstantiationException    if the class that declares the
     *                                   underlying constructor represents an abstract class.
     */
    Object deSerialize(String file) throws IllegalAccessException, InvocationTargetException,
            NoSuchFieldException, ClassNotFoundException, NoSuchMethodException, InstantiationException {
        Object object = null;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            this.bufferedReader = bufferedReader;
            object = getObject(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * Construct new object from the file
     *
     * @param bufferedReader input stream where info about is stored
     * @return new object that deserialized from the file
     * @throws IOException               in case exceptions occurs during bufferReader use
     * @throws ClassNotFoundException    if the class cannot be located
     * @throws NoSuchMethodException     if no such method
     * @throws IllegalAccessException    if access to the field is denied
     * @throws InvocationTargetException if the underlying constructor
     *                                   throws an exception.
     * @throws InstantiationException    if the class that declares the
     *                                   underlying constructor represents an abstract class.
     * @throws NoSuchFieldException      if no such field
     */
    private Object getObject(BufferedReader bufferedReader) throws IOException, ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        String className = bufferedReader.readLine();
        Class classForObject = Class.forName(className);
        Object object = classForObject.getDeclaredConstructor().newInstance();
        String line = bufferedReader.readLine();
        while (bufferedReader.ready() && !(endObject.equals(line))) {
            parseFieldInfo(classForObject, object, line);
            line = bufferedReader.readLine();
        }
        return object;
    }

    /**
     * Make check of the serialVersionUID
     *
     * @param serialVersionUIDInfo from file to be checked
     * @param o                    where current serialVersionUID is contained
     * @return {@code true} if serialVersionUIDs are the same
     * @throws NoSuchFieldException   if no such field
     * @throws IllegalAccessException if access is denied
     */
    private boolean checkSerialVersionUID(String serialVersionUIDInfo, Object o) throws NoSuchFieldException, IllegalAccessException {
        String[] serialVersionUID = serialVersionUIDInfo.split(space);
        long serializedSerialVersionUID = Long.parseLong(serialVersionUID[3]);
        Field oSerialVersionUID = o.getClass().getDeclaredField(serialVersionUID[2]);
        oSerialVersionUID.setAccessible(true);
        return serializedSerialVersionUID == oSerialVersionUID.getLong(o);
    }

    /**
     * Parse field info to the new created object
     *
     * @param classForObject    that is deserialized
     * @param object            that is deserialized
     * @param fieldInfoInString string from the file that will be parsed into object
     * @throws NoSuchFieldException   field is not found
     * @throws IllegalAccessException access is denied
     */
    private void parseFieldInfo(Class classForObject, Object object, String fieldInfoInString) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IOException {
        String[] fieldInfo = fieldInfoInString.split(space);
        Field oField = classForObject.getDeclaredField(fieldInfo[2]);
        oField.setAccessible(true);
        Class<?> fieldType = oField.getType();
        if (fieldType == Integer.TYPE) {
            oField.set(object, Integer.parseInt(fieldInfo[3]));
        } else if (fieldType == Long.TYPE) {
            oField.set(object, Long.parseLong(fieldInfo[3]));
        } else if (fieldType == Byte.TYPE) {
            oField.set(object, Byte.parseByte(fieldInfo[3]));
        } else if (fieldType == Short.TYPE) {
            oField.set(object, Short.parseShort(fieldInfo[3]));
        } else if (fieldType == Character.TYPE) {
            oField.set(object, fieldInfo[3].toCharArray()[0]);
        } else if (fieldType == Double.TYPE) {
            oField.set(object, Double.parseDouble(fieldInfo[3]));
        } else if (fieldType == Float.TYPE) {
            oField.set(object, Float.parseFloat(fieldInfo[3]));
        } else if (fieldType == String.class) {
            oField.set(object, fieldInfo[3]);
        } else {
            oField.set(object, getObject(bufferedReader));
        }
    }
}
