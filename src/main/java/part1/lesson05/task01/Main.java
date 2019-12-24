package part1.lesson05.task01;

import part1.lesson05.task01.person.Person;
import part1.lesson05.task01.person.PersonGenerator;
import part1.lesson05.task01.pets.Pet;
import part1.lesson05.task01.pets.PetsMap;

/**
 * Try PetsMap in action
 *
 * @author Ekaterina Belolipetskaya
 */
public class Main {
    public static void main(String[] args) {
        PersonGenerator personGenerator = new PersonGenerator();
        personGenerator.initGenerator();
        PetsMap petsMap = new PetsMap();
        Pet pet1 = new Pet("Барсик",personGenerator.generate(), 8);
        Person person = personGenerator.generate();
        petsMap.addPet(pet1);
        petsMap.addPet(new Pet("Марсель",person, 8));
        petsMap.addPet(new Pet("Шустрик",person, 8));
        petsMap.addPet(new Pet("Симба",person, 8));
        petsMap.addPet(new Pet("Симба",person, 12));
        petsMap.addPet(new Pet("Апельсинка",personGenerator.generate(), 8));
        System.out.println(petsMap.findPetByName("Апельсинка"));
        petsMap.printSet();
        petsMap.updatePet(pet1.getUuid(), person, 13);
        petsMap.printSet();
    }
}
