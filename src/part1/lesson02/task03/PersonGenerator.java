package part1.lesson02.task03;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * PersonGenerator
 *
 * Generate the object Person.
 * age - random value from 0 to 100
 * sex - random value from class Sex
 * name - random value from dictionary, depending on sex
 * Names dictionaries loading from files.
 *
 * @author Ekaterina Belolipetskaya
 */
public class PersonGenerator {
    private static Random random = new Random();
    private static String fileNameForManNames =  "./src/part1/lesson02/task03/manNames.txt";
    private static String fileNameForWomanNames = "./src/part1/lesson02/task03/womanNames.txt";
    private static List<String> manNames = new ArrayList<>();
    private static List<String> womanNames = new ArrayList<>();


    static {
        readNames(fileNameForManNames, manNames);
        readNames(fileNameForWomanNames, womanNames);
    }

    public static void readNames(String fileName, List list){
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))){
            while (reader.ready()){
                list.add(reader.readLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Person generate(){
        Person person = new Person();
        person.setAge(random.nextInt(101));

        int sex = random.nextInt(2);
        person.setSex(sex==0? Sex.MAN: Sex.WOMAN);

        if (sex==0) {
            int name = random.nextInt(manNames.size());
            person.setName(manNames.get(name));
        }
        else {
            int name = random.nextInt(womanNames.size());
            person.setName(womanNames.get(name));
        }
        return person;
    }

}
