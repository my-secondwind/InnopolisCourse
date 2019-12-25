package part5.lesson17.task02.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import part5.lesson17.task02.connectionManager.ConnectionManager;
import part5.lesson17.task02.connectionManager.ConnectionManagerJdbcImpl;
import part5.lesson17.task02.model.User;

import java.sql.*;

/**
 * UserDaoImpl
 * <p>
 * Implements CRUD methods for User object.
 *
 * @author Ekaterina Belolipetskaya
 */
public class UserDaoImpl implements GenericDao<User> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
    private static ConnectionManager connectionManager =
            ConnectionManagerJdbcImpl.getInstance();
    public static final String INSERT_USER_STATEMENT = "INSERT INTO users values (DEFAULT, ?, ?, ?, ?, ?, ?)";
    public static final String SELECT_USER_STATEMENT = "SELECT * FROM users WHERE id = ?";
    public static final String UPDATE_USER_STATEMENT = "UPDATE users SET name = ?, birthday = ?, login_id = ?, " +
            "city = ?, email = ?, description = ? WHERE id = ?";
    public static final String DELETE_USER_STATEMENT = "DELETE FROM users WHERE id = ?";

    /**
     * Add object into DB.
     *
     * @param object that is added to DB.
     * @return {@code true} if object was added successfully.
     */

    @Override
    public boolean addObject(User object) {
        boolean result = false;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_STATEMENT)) {
            preparedStatement.setString(1, object.getName());
            preparedStatement.setDate(2, Date.valueOf(object.getBirthday()));
            preparedStatement.setInt(3, object.getLoginId());
            preparedStatement.setString(4, object.getCity());
            preparedStatement.setString(5, object.getEmail());
            preparedStatement.setString(6, object.getDescription());
            result = (preparedStatement.executeUpdate() == 1);
        } catch (SQLException e) {
            LOGGER.error("Error during adding object {}", object, e);
        }
        return result;
    }

    /**
     * Get object from DB by ID.
     *
     * @param id of object to be found.
     * @return object that created based pn info from DB
     */
    @Override
    public User getObjectById(Integer id) {
        User user = new User();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_STATEMENT)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            createUserObject(user, resultSet);
        } catch (SQLException e) {
            LOGGER.error("Error during getting object by Id {}", id, e);
        }
        return user;
    }

    /**
     * Set object's fields according to DB info.
     *
     * @param user      object to be filled.
     * @param resultSet with info about object.
     * @throws SQLException - if any SQL errors occurs.
     */
    private void createUserObject(User user, ResultSet resultSet) throws SQLException {
        user.setId(resultSet.getInt(1));
        user.setName(resultSet.getString(2));
        user.setBirthday(resultSet.getString(3));
        user.setLoginId(resultSet.getInt(4));
        user.setCity(resultSet.getString(5));
        user.setEmail(resultSet.getString(6));
        user.setDescription(resultSet.getString(7));
    }

    /**
     * Update DB row for the following object.
     *
     * @param object updated object.
     * @return {@code true} if object was updated successfully.
     */
    @Override
    public boolean updateObjectById(User object) {
        boolean result = false;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_STATEMENT)) {
            preparedStatement.setString(1, object.getName());
            preparedStatement.setDate(2, Date.valueOf(object.getBirthday()));
            preparedStatement.setInt(3, object.getLoginId());
            preparedStatement.setString(4, object.getCity());
            preparedStatement.setString(5, object.getEmail());
            preparedStatement.setString(6, object.getDescription());
            preparedStatement.setInt(7, object.getId());
            result = (preparedStatement.executeUpdate() == 1);
        } catch (SQLException e) {
            LOGGER.error("Error during updating object by Id {}", object, e);
        }
        return result;
    }

    /**
     * Delete object by ID.
     *
     * @param id of he object to be deleted.
     * @return {@code true} if object was deleted successfully.
     */
    @Override
    public boolean deleteObjectById(Integer id) {
        boolean result = false;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_STATEMENT)) {
            preparedStatement.setInt(1, id);
            result = (preparedStatement.executeUpdate() == 1);
        } catch (SQLException e) {
            LOGGER.error("Error during deleting object by Id {}", id, e);
        }
        return result;
    }
}
