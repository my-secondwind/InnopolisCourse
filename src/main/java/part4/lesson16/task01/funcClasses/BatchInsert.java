package part4.lesson16.task01.funcClasses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import part4.lesson16.task01.connectionManager.ConnectionManager;
import part4.lesson16.task01.connectionManager.ConnectionManagerJdbcImpl;
import part4.lesson16.task01.model.enums.RolesNames;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static part3.lesson15.task01.dao.RoleDaoImpl.INSERT_ROLE_STATEMENT;

/**
 * BatchInsert
 *
 * @author Ekaterina Belolipetskaya
 */
public class BatchInsert {
    private static final Logger LOGGER = LoggerFactory.getLogger(BatchInsert.class);
    private static final ConnectionManager CONNECTION_MANAGER =
            ConnectionManagerJdbcImpl.getInstance();

    /**
     * Do batch insert into DB.
     */
    public static void doInsert() {
        try (Connection connection = CONNECTION_MANAGER.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ROLE_STATEMENT)) {
            LOGGER.debug("Do insert");
            preparedStatement.setObject(1, RolesNames.ADMINISTRATION.name());
            preparedStatement.setString(2, null);
            preparedStatement.addBatch();
            preparedStatement.setObject(1, RolesNames.BILLING.name());
            preparedStatement.setString(2, null);
            preparedStatement.addBatch();
            preparedStatement.setObject(1, RolesNames.CLIENTS.name());
            preparedStatement.setString(2, null);
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            LOGGER.error("Error during insert", e);
        }
    }
}
