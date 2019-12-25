package part4.lesson16.task01.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import part4.lesson16.task01.connectionManager.ConnectionManager;
import part4.lesson16.task01.connectionManager.ConnectionManagerJdbcImpl;
import part4.lesson16.task01.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * RoleDaoImpl
 * <p>
 * Implements CRUD methods for Role object.
 *
 * @author Ekaterina Belolipetskaya
 */
public class RoleDaoImpl implements GenericDao<Role> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleDaoImpl.class);
    private static ConnectionManager connectionManager =
            ConnectionManagerJdbcImpl.getInstance();
    public static final String INSERT_ROLE_STATEMENT = "INSERT INTO role values (DEFAULT, ?, ?)";
    public static final String SELECT_ROLE_STATEMENT = "SELECT * FROM role WHERE id = ?";
    public static final String UPDATE_ROLE_STATEMENT = "UPDATE role SET name = ?, description = ? WHERE id = ?";
    public static final String DELETE_ROLE_STATEMENT = "DELETE FROM role WHERE id = ?";


    /**
     * Add object into DB.
     *
     * @param object that is added to DB.
     * @return {@code true} if object was added successfully.
     */
    @Override
    public boolean addObject(Role object) {
        boolean result = false;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ROLE_STATEMENT)) {
            LOGGER.debug("Adding object {}", object);
            preparedStatement.setObject(1, object.getName().name());
            preparedStatement.setString(2, object.getDescription());
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
    public Role getObjectById(Integer id) {
        Role role = new Role();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROLE_STATEMENT)) {
            LOGGER.debug("Getting object by Id {}", id);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            createRoleObject(role, resultSet);
        } catch (SQLException e) {
            LOGGER.error("Error during getting the object by Id {}", id, e);
        }
        return role;
    }

    /**
     * Set object's fields according to DB info.
     *
     * @param role      object to be filled.
     * @param resultSet with info about object.
     * @throws SQLException - if any SQL errors occurs.
     */
    private void createRoleObject(Role role, ResultSet resultSet) throws SQLException {
        role.setId(resultSet.getInt(1));
        role.setName(resultSet.getString(2));
        role.setDescription(resultSet.getString(3));
    }

    /**
     * Update DB row for the following object.
     *
     * @param object updated object.
     * @return {@code true} if object was updated successfully.
     */
    @Override
    public boolean updateObjectById(Role object) {
        boolean result = false;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ROLE_STATEMENT)) {
            LOGGER.debug("Updating object by Id {}", object);
            preparedStatement.setString(1, object.getName().name());
            preparedStatement.setString(2, object.getDescription());
            preparedStatement.setInt(3, object.getId());
            result = (preparedStatement.executeUpdate() == 1);
        } catch (SQLException e) {
            LOGGER.error("Error during updating the object by Id {}", object, e);
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
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ROLE_STATEMENT)) {
            LOGGER.debug("Deleting object by Id {}", id);
            preparedStatement.setInt(1, id);
            result = (preparedStatement.executeUpdate() == 1);
        } catch (SQLException e) {
            LOGGER.error("Error during deleting the object by Id {}", id, e);
        }
        return result;
    }
}
