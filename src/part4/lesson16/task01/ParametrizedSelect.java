package part4.lesson16.task01;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import part4.lesson16.task01.connectionManager.ConnectionManager;
import part4.lesson16.task01.connectionManager.ConnectionManagerJdbcImpl;
import part4.lesson16.task01.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ParametrizedSelect
 * @author Ekaterina Belolipetskaya
 */
public class ParametrizedSelect {
    private static final Logger LOGGER = LogManager.getLogger(ParametrizedSelect.class);
    private static ConnectionManager connectionManager =
            ConnectionManagerJdbcImpl.getInstance();

    private static int loginId = 13;
    private static String name = "Kate";

    /**
     * Do Parametrized Select on login_id and name at the same time.
     */
    public static void doParameterizedSelect() {
        User user = new User();
        String select = "SELECT * FROM users WHERE login_id = ? AND name = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            LOGGER.info(ParametrizedSelect.class);
            preparedStatement.setInt(1, loginId);
            preparedStatement.setString(2, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setBirthday(resultSet.getString(3));
                user.setLoginId(resultSet.getInt(4));
                user.setCity(resultSet.getString(5));
                user.setEmail(resultSet.getString(6));
                user.setDescription(resultSet.getString(7));
                LOGGER.info(user);
            }
        } catch (SQLException e) {
            LOGGER.throwing(Level.ERROR, e);
        }
    }
}
