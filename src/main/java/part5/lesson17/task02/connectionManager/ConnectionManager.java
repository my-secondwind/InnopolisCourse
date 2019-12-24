package part5.lesson17.task02.connectionManager;

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
