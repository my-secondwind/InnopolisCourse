package part1.lesson05.task01.person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * PersonGenerator
 * <p>
 * Generate the object Person.
 * age - random value from 0 to 100
 * sex - random value from class Sex
 * name - random value from dictionary, depending on sex
 * Names dictionaries loading from files.
 *
 * @author Ekaterina Belolipetskaya
 */
public class PersonGenerator {
    private final Random random = new Random();
    private List<String> manNames = new ArrayList<>();
    private List<String> womanNames = new ArrayList<>();

    public void initGenerator() {
        String fileNameForManNames = "./src/inputfiles/manNames.txt";
        String fileNameForWomanNames = "./src/inputfiles/womanNames.txt";
        readNames(fileNameForManNames, manNames);
        readNames(fileNameForWomanNames, womanNames);
    }

    private void readNames(String fileName, List<String> list) {
        try (Stream<String> streamFromFiles = Files.lines(Path.of(fileName))) {
            streamFromFiles.forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Person generate() {
        int age = random.nextInt(101);
        int sexRandom = random.nextInt(2);
        Sex sex = sexRandom == 0 ? Sex.MAN : Sex.WOMAN;

        String name;
        if (sexRandom == 0) {
            int nameRandom = random.nextInt(manNames.size());
            name = manNames.get(nameRandom);
        } else {
            int nameRandom = random.nextInt(womanNames.size());
            name = womanNames.get(nameRandom);
        }
        return new Person(name, age, sex);
    }

}

