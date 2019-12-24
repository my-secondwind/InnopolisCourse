package part1.lesson05.task01.pets;

import part1.lesson05.task01.exceptions.IdentityException;
import part1.lesson05.task01.person.Person;

import java.util.*;

/**
 * PetsMap
 * <p>
 * Contains Pets in TreeMap:
 * kye - pets uuid,
 * value - object of class Pet.
 * <p>
 * Implements the following methods:
 * <p>
 * addPet - add new Pet to the TreeMap
 * findPetByName - returns Pet that was found by name
 * updatePet - update Pet's value that was found by uuid
 * printSet - print Pets' set sorted by Comparable algorithm
 * that is implemented in the class Pet.
 *
 * @author Ekaterina Belolipetskaya
 */
public class PetsMap {
    private Map<UUID, Pet> petsMap = new TreeMap<>();

    /**
     * Add pet into petsMap
     *
     * @param pet - object Pet that will be added to {@code petsMap}
     * @throws IdentityException in case {@code pet} is already exists in {@code petsMap}
     */
    public void addPet(Pet pet) throws IdentityException {
        if (petsMap.containsKey(pet.getUuid()))
            throw new IdentityException("Попытка добавления дубликата объекта класса Pet");
        petsMap.put(pet.getUuid(), pet);
    }

    /**
     * Try to find Pet by {@code name} using {@code quickSearch}
     *
     * @param name - name of the object to be searching
     * @return - object Pet that was an input value or null,
     * if Pet with such name is absent
     */
    public Pet findPetByName(String name) {
        List<Pet> petsList = new ArrayList<>(petsMap.values());
        Collections.sort(petsList, Comparator.comparing(Pet::getName));
        int start = 0;
        int end = petsList.size();
        return quickSearch(petsList, start, end, name);
    }

    /**
     * Private method that implements searching algorithm
     * in sorted list.
     *
     * @param petsList - list where search will be
     * @param start    - start index
     * @param end      - end index
     * @param name     - name of the object to be searching
     * @return - object Pet that was an input value or null,
     * if Pet with such name is absent
     */
    private Pet quickSearch(List<Pet> petsList, int start, int end, String name) {
        if (start > end) return null;
        int middle = start + (end - start) / 2;
        Pet middlePet = petsList.get(middle);
        if (middlePet.getName().equals(name)) return middlePet;
        if (middlePet.getName().compareTo(name) > 0) {
            end = middle - 1;
        } else {
            start = middle + 1;
        }
        return quickSearch(petsList, start, end, name);
    }

    /**
     * Update info about pet (owner and weight) for
     * pet with specified uuid
     *
     * @param uuid   of the pet that will be updated
     * @param owner  new info about owner
     * @param weight new info about weight
     * @return {code true} if update was successful
     * {code false} if object pet wasn't identified by
     * specified uuid
     */
    public boolean updatePet(UUID uuid, Person owner, int weight) {
        Pet updatedPet = petsMap.get(uuid);
        if (updatedPet == null) return false;
        updatedPet.setOwner(owner);
        updatedPet.setWeight(weight);
        return true;
    }

    /**
     * Prints Pets' set sorted by Comparable algorithm
     * that is implemented in the class Pet.
     */
    public void printSet() {
        Set<Pet> petsSet = new TreeSet<>(petsMap.values());
        System.out.println(petsSet);
    }

}
