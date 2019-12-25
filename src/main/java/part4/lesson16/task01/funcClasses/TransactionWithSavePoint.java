package part4.lesson16.task01.funcClasses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import part4.lesson16.task01.connectionManager.ConnectionManager;
import part4.lesson16.task01.connectionManager.ConnectionManagerJdbcImpl;
import part4.lesson16.task01.model.Role;
import part4.lesson16.task01.model.User;
import part4.lesson16.task01.model.enums.RolesNames;

import java.sql.*;

import static part3.lesson15.task01.dao.RoleDaoImpl.INSERT_ROLE_STATEMENT;
import static part3.lesson15.task01.dao.UserDaoImpl.INSERT_USER_STATEMENT;

/**
 * TransactionWithSavePoint
 *
 * @author Ekaterina Belolipetskaya
 */
public class TransactionWithSavePoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionWithSavePoint.class);
    private static ConnectionManager connectionManager =
            ConnectionManagerJdbcImpl.getInstance();
    private static final String INSERT_USER_ROLE_STATEMENT = "INSERT INTO user_role VALUES (DEFAULT, ?, ?)";
    private static final Role ROLE = new Role(null, RolesNames.ADMINISTRATION, "Admins only");
    private static final User USER = new User(null, "Ivan", "1994-09-15", 15,
            "Moscow", "ivan@gmail.com", "another user");
    private static final int USER_ID = 1;
    private static final int ROLE_ID = 1;

    /**
     * Do Transaction With Save Point.
     * Get connection and three insert statements.
     * Call for execTransaction to dd three inserts in one transaction.
     */
    public static void doTransactionWithSavePoint() {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement insertRoleStatement = connection.prepareStatement(INSERT_ROLE_STATEMENT);
             PreparedStatement insertUserStatement = connection.prepareStatement(INSERT_USER_STATEMENT);
             PreparedStatement insertUserRoleStatement = connection.prepareStatement(INSERT_USER_ROLE_STATEMENT)) {
            LOGGER.debug("Do Transaction With Save Point");
            connection.setAutoCommit(false);
            execTransaction(connection, insertRoleStatement, insertUserStatement, insertUserRoleStatement);
        } catch (SQLException e) {
            LOGGER.error("Error during getting connection/statement or rolling back the transaction", e);
        }
    }

    /**
     * Do three inserts in one transaction.
     *
     * @param connection              DB connection.
     * @param insertRoleStatement     first insert.
     * @param insertUserStatement     second insert.
     * @param insertUserRoleStatement third insert.
     * @throws SQLException if any SQL errors occurs.
     */
    private static void execTransaction(Connection connection, PreparedStatement insertRoleStatement, PreparedStatement insertUserStatement,
                                        PreparedStatement insertUserRoleStatement) throws SQLException {
        try {
            setPreparedStatement(insertRoleStatement, ROLE);
            insertRoleStatement.execute();

            setPreparedStatement(insertUserStatement, USER);
            insertUserStatement.execute();

            Savepoint savepoint = connection.setSavepoint("role and user added");

            try {
                setPreparedStatement(insertUserRoleStatement, USER_ID, ROLE_ID);
                insertUserRoleStatement.execute();
            } catch (Exception e) {
                LOGGER.error("Error during insertUserRoleStatement execution ", e);
                connection.rollback(savepoint);
                throw e;
            }

            connection.commit();
        } catch (Exception e) {
            LOGGER.error("Error during Transaction With Save Point ", e);
            connection.rollback();
        }
    }

    /**
     * Set {@code preparedStatement} by values in {@code role}.
     *
     * @param preparedStatement to be set.
     * @param role              where values is get.
     * @throws SQLException if ane SQL errors occurs.
     */
    private static void setPreparedStatement(PreparedStatement preparedStatement, Role role) throws SQLException {
        preparedStatement.setString(1, role.getName().name());
        preparedStatement.setString(2, role.getDescription());
    }

    /**
     * Set {@code preparedStatement} by values in {@code user}.
     *
     * @param preparedStatement to be set.
     * @param user              where values is get.
     * @throws SQLException if ane SQL errors occurs.
     */
    private static void setPreparedStatement(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getName());
        preparedStatement.setDate(2, Date.valueOf(user.getBirthday()));
        preparedStatement.setInt(3, user.getLoginId());
        preparedStatement.setString(4, user.getCity());
        preparedStatement.setString(5, user.getEmail());
        preparedStatement.setString(6, user.getDescription());
    }

    /**
     * Set {@code preparedStatement} by values in {@code user}.
     *
     * @param preparedStatement to be set.
     * @param userId            value for {@code preparedStatement}.
     * @param roleId            value for {@code preparedStatement}.
     * @throws SQLException if ane SQL errors occurs.
     */
    private static void setPreparedStatement(PreparedStatement preparedStatement, int userId, int roleId) throws SQLException {
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, roleId);
    }
}
