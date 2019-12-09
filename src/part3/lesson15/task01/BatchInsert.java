package part3.lesson15.task01;

import part3.lesson15.task01.connectionManager.ConnectionManager;
import part3.lesson15.task01.connectionManager.ConnectionManagerJdbcImpl;
import part3.lesson15.task01.model.enums.RolesNames;

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
    private static ConnectionManager connectionManager =
            ConnectionManagerJdbcImpl.getInstance();

    /**
     * Do batch insert into DB.
     */
    public static void doInsert() {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ROLE_STATEMENT)) {
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
            e.printStackTrace();
        }
    }
}
