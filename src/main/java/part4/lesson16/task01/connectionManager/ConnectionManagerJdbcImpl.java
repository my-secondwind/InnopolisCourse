package part4.lesson16.task01.connectionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ConnectionManagerJdbcImpl
 * <p>
 * Implements connection manager for JDBC.
 *
 * @author Ekaterina Belolipetskaya
 */
public class ConnectionManagerJdbcImpl implements ConnectionManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionManagerJdbcImpl.class);
    private static ConnectionManager connectionManager;
    private static final String POSTGRES_DRIVER = "org.postgresql.Driver";
    public static final String POSTGRES_URL_USERS = "jdbc:postgresql://localhost:5432/users";
    public static final String POSTGRES_USER = "postgres";
    public static final String POSTGRES_PASSWORD = "qwerty123";
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
            LOGGER.debug(GET_CONNECTION);
            Class.forName(POSTGRES_DRIVER);
            connection = DriverManager.getConnection(POSTGRES_URL_USERS, POSTGRES_USER, POSTGRES_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("Getting connection is failed.", e);
        }
        return connection;
    }
}
