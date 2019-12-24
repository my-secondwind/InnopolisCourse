package part5.lesson17.task02.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import part5.lesson17.task02.TestResultLoggerExtension;
import part5.lesson17.task02.connectionManager.ConnectionManager;
import part5.lesson17.task02.connectionManager.ConnectionManagerJdbcImpl;
import part5.lesson17.task02.model.Role;
import part5.lesson17.task02.model.enums.RolesNames;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Ekaterina Belolipetskaya
 */
@ExtendWith(TestResultLoggerExtension.class)
class RoleDaoImplTest {
    public static final String ADMINS_ONLY = "Admins only";
    private static final Integer ABSENT_ID = 2;
    public static final int EXEC_UPDATE_COUNT_ONE = 1;
    public static final Role NULL_ROLE = new Role();
    public static final int EXEC_UPDATE_COUNT_ZERO = 0;
    @Spy
    private ConnectionManager connectionManager = ConnectionManagerJdbcImpl.getInstance();
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;
    private Role role;
    private RoleDaoImpl roleDao;
    private static final int roleId = 1;

    @BeforeEach
    void setUp() throws SQLException {
        initMocks(this);
        doReturn(connection).when(connectionManager).getConnection();
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(preparedStatement.executeUpdate()).thenReturn(EXEC_UPDATE_COUNT_ONE);
        roleDao = new RoleDaoImpl(connectionManager);
        role = new Role(roleId, RolesNames.ADMINISTRATION, ADMINS_ONLY);
    }

    @Test
    void addObject() {
        boolean result = roleDao.addObject(role);

        verify(connectionManager, times(1)).getConnection();
        assertTrue(result);
    }

    @Test
    void addNullObject() {
        Role nullRole = new Role();
        assertThrows(NullPointerException.class, () -> roleDao.addObject(nullRole));

        verify(connectionManager, times(1)).getConnection();
    }

    @Test
    void addNull() {
        assertThrows(NullPointerException.class, () -> roleDao.addObject(null));

        verify(connectionManager, times(1)).getConnection();
    }

    @Test
    void getObjectById() throws SQLException {
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(roleId);
        when(resultSet.getString(2)).thenReturn(RolesNames.ADMINISTRATION.name());
        when(resultSet.getString(3)).thenReturn(ADMINS_ONLY);

        Role resultRole = roleDao.getObjectById(roleId);

        verify(connectionManager, times(1)).getConnection();

        assertEquals(role, resultRole);
    }

    @Test
    void getObjectByAbsentId() {
        Role resultRole = roleDao.getObjectById(roleId);

        verify(connectionManager, times(1)).getConnection();

        assertEquals(NULL_ROLE, resultRole);
    }

    @Test
    void getObjectByNullId() {
        assertThrows(NullPointerException.class, () -> roleDao.getObjectById(null));

        verify(connectionManager, times(1)).getConnection();
    }

    @Test
    void updateObjectById() {
        boolean result = roleDao.updateObjectById(role);

        verify(connectionManager, times(1)).getConnection();

        assertTrue(result);
    }

    @Test
    void updateObjectByAbsentId() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(EXEC_UPDATE_COUNT_ZERO);
        boolean result = roleDao.updateObjectById(role);

        verify(connectionManager, times(1)).getConnection();

        assertFalse(result);
    }

    @Test
    void updateObjectByIdNullName() {
        RolesNames rolesName = null;
        role.setName(rolesName);
        assertThrows(NullPointerException.class, () -> roleDao.updateObjectById(role));

        verify(connectionManager, times(1)).getConnection();
    }

    @Test
    void deleteObjectById() {
        boolean result = roleDao.deleteObjectById(roleId);

        verify(connectionManager, times(1)).getConnection();

        assertTrue(result);
    }

    @Test
    void deleteObjectByAbsentId() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(EXEC_UPDATE_COUNT_ZERO);
        boolean result = roleDao.deleteObjectById(roleId);

        verify(connectionManager, times(1)).getConnection();

        assertFalse(result);
    }

    @Test
    void deleteObjectByNullId() {
        assertThrows(NullPointerException.class, () -> roleDao.deleteObjectById(null));

        verify(connectionManager, times(1)).getConnection();
    }
}