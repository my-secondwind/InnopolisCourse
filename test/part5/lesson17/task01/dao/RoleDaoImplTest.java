package part5.lesson17.task01.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import part5.lesson17.task01.TestResultLoggerExtension;
import part5.lesson17.task01.connectionManager.ConnectionManager;
import part5.lesson17.task01.connectionManager.ConnectionManagerJdbcImpl;
import part5.lesson17.task01.creationMains.CreationMainUsers;
import part5.lesson17.task01.model.Role;
import part5.lesson17.task01.model.enums.RolesNames;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Ekaterina Belolipetskaya
 */
@ExtendWith(TestResultLoggerExtension.class)
class RoleDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger(RoleDaoImplTest.class);
    public static final String ADMINS_ONLY = "Admins only";
    private static final Integer ABSENT_ID = 2;
    private static ConnectionManager connectionManager;
    private static Connection connection;
    private Role role = new Role(1, RolesNames.ADMINISTRATION, ADMINS_ONLY);
    private RoleDaoImpl roleDao;
    private static final int roleId = 1;

    @BeforeAll
    public static void setUpBeforeAll() throws SQLException {
        connectionManager = spy(ConnectionManagerJdbcImpl.getInstance());
        connection = spy(connectionManager.getConnection());

        doReturn(connection).when(connectionManager).getConnection();
        doNothing().when(connection).close();
    }

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        clearInvocations(connectionManager, connection);
        CreationMainUsers.create();
        roleDao = new RoleDaoImpl(connectionManager);
    }

    @AfterAll
    public static void tearDownAfterAll() throws SQLException {
        connection.close();
    }

    @Test
    void addObject() throws SQLException {
        boolean result = roleDao.addObject(role);

        verify(connectionManager, times(1)).getConnection();
        verify(connection, times(1)).close();
        assertTrue(result);
    }

    @Test
    void addNullObject() throws SQLException {
        Role nullRole = new Role();
        assertThrows(NullPointerException.class, () -> roleDao.addObject(nullRole));

        verify(connectionManager, times(1)).getConnection();
        verify(connection, times(1)).close();
    }

    @Test
    void addNull() throws SQLException {
        assertThrows(NullPointerException.class, () -> roleDao.addObject(null));

        verify(connectionManager, times(1)).getConnection();
        verify(connection, times(1)).close();
    }

    @Test
    void getObjectById() throws SQLException {
        roleDao.addObject(role);
        Role resultRole = roleDao.getObjectById(roleId);

        verify(connectionManager, times(2)).getConnection();
        verify(connection, times(2)).close();

        assertEquals(role, resultRole);
    }

    @Test
    void getObjectByAbsentId() throws SQLException {
        assertDoesNotThrow(() -> roleDao.getObjectById(roleId));

        verify(connectionManager, times(1)).getConnection();
        verify(connection, times(1)).close();
    }

    @Test
    void getObjectByNullId() throws SQLException {
        assertThrows(NullPointerException.class, () -> roleDao.getObjectById(null));

        verify(connectionManager, times(1)).getConnection();
        verify(connection, times(1)).close();
    }

    @Test
   void updateObjectById() throws SQLException {
        roleDao.addObject(role);
        role.setName(RolesNames.BILLING);
        boolean result = roleDao.updateObjectById(role);

        verify(connectionManager, times(2)).getConnection();
        verify(connection, times(2)).close();

        assertTrue(result);
   }

    @Test
    void updateObjectByAbsentId() throws SQLException {
        roleDao.addObject(role);
        role.setId(ABSENT_ID);
        boolean result = roleDao.updateObjectById(role);

        verify(connectionManager, times(2)).getConnection();
        verify(connection, times(2)).close();

        assertFalse(result);
    }

    @Test
    void updateObjectByIdNullName() throws SQLException {
        roleDao.addObject(role);
        RolesNames rolesName = null;
        role.setName(rolesName);
        assertThrows(NullPointerException.class, () -> roleDao.updateObjectById(role));

        verify(connectionManager, times(2)).getConnection();
        verify(connection, times(2)).close();
    }

    @Test
    void deleteObjectById() throws SQLException {
        roleDao.addObject(role);
        boolean result = roleDao.deleteObjectById(roleId);

        verify(connectionManager, times(2)).getConnection();
        verify(connection, times(2)).close();

        assertTrue(result);
    }

    @Test
    void deleteObjectByAbsentId() throws SQLException {
        boolean result = roleDao.deleteObjectById(roleId);

        verify(connectionManager, times(1)).getConnection();
        verify(connection, times(1)).close();

        assertFalse(result);
    }

    @Test
    void deleteObjectByNullId() throws SQLException {
        assertThrows(NullPointerException.class, () -> roleDao.deleteObjectById(null));

        verify(connectionManager, times(1)).getConnection();
        verify(connection, times(1)).close();
    }
}