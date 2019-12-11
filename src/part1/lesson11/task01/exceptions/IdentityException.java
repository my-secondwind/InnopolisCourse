package part1.lesson11.task01.exceptions;

/**
 * IdentityException
 * <p>
 * Signals that identity of the Object is broken:
 * fields of the created Object have values,
 * that match the values of existing Object
 *
 * @author Ekaterina Belolipetskaya
 */
public class IdentityException extends RuntimeException {

    public IdentityException() {
        super();
    }

    public IdentityException(String message) {
        super(message);
    }
}
