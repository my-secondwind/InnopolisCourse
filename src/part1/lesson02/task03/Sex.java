package part1.lesson02.task03;

/**
 * Sex
 *
 * Enum for sex identification
 *
 * @author Ekaterina Belolipetskaya
 */
public enum Sex {

    MAN("man"),
    WOMAN("woman");

    private String value;

    private Sex(String name) {
        this.value = name;
    }

    public String getDisplayName() {
        return this.value;
    }

}
