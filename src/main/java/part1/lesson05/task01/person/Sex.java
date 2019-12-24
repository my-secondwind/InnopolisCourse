package part1.lesson05.task01.person;

/**
 * Sex
 * <p>
 * Enum for sex identification
 *
 * @author Ekaterina Belolipetskaya
 */
public enum Sex {

    MAN("man"),
    WOMAN("woman");

    private String value;

    Sex(String name) {
        this.value = name;
    }

    public String getDisplayName() {
        return this.value;
    }

}
