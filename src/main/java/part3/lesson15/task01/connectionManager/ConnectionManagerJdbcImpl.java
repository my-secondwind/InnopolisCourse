package part3.lesson15.task01.connectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ConnectionManagerJdbcImpl
 *
 * Implements connection manager for JDBC.
 *
 * @author Ekaterina Belolipetskaya
 */
public class ConnectionManagerJdbcImpl implements ConnectionManager {
    private static ConnectionManager connectionManager;
    private static final String POSTGRES_DRIVER = "org.postgresql.Driver";
    private static final String POSTGRES_URL = "jdbc:postgresql://localhost:5432/users";
    private static final String POSTGRES_USER = "postgres";
    private static final String POSTGRES_PASSWORD = "qwerty123";

    private ConnectionManagerJdbcImpl() {
    }

    public static ConnectionManager getInstance() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManagerJdbcImpl();
        }
        return connectionManager;
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(POSTGRES_DRIVER);
            connection = DriverManager.getConnection(
                    POSTGRES_URL,
                    POSTGRES_USER,
                    POSTGRES_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
