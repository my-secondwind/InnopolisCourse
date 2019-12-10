package part4.lesson16.task01.connectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ConnectionManagerJdbcImpl
 *
 * Implements connection manager for JDBC.
 *
 * @author Ekaterina Belolipetskaya
 */
public class ConnectionManagerJdbcImpl implements ConnectionManager {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionManagerJdbcImpl.class);
    private static ConnectionManager connectionManager;
    private static final String POSTGRES_DRIVER = "org.postgresql.Driver";
    private static final String POSTGRES_URL = "jdbc:postgresql://localhost:5432/users";
    private static final String POSTGRES_USER = "postgres";
    private static final String POSTGRES_PASSWORD = "qwerty12345";
    private static final String GET_CONNECTION = "get connection";

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
            LOGGER.info(GET_CONNECTION);
            Class.forName(POSTGRES_DRIVER);
            connection = DriverManager.getConnection(
                    POSTGRES_URL,
                    POSTGRES_USER,
                    POSTGRES_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.throwing(Level.ERROR, e);
        }
        return connection;
    }
}
