package part1.lesson11.task01.pets;

import part1.lesson11.task01.exceptions.IdentityException;
import part1.lesson11.task01.person.Person;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.UUID;

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
    public void addPet(Pet pet) {
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
        Optional<Pet> first = petsMap.values().stream().filter(x -> name.equals(x.getName())).findFirst();
        return first.orElse(null);
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
        petsMap.values().stream().sorted().forEach(System.out::print);
    }
}
