package part3.lesson15.task01.connectionManager;

import java.sql.Connection;

/**
 * ConnectionManager
 *
 * Interface for getting connection
 *
 * @author Ekaterina Belolipetskaya
 */
public interface ConnectionManager {
    Connection getConnection();
}
