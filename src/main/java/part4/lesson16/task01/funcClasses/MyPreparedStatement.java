package part4.lesson16.task01.funcClasses;

import part4.lesson16.task01.dao.GenericDao;
import part4.lesson16.task01.dao.RoleDaoImpl;
import part4.lesson16.task01.dao.UserDaoImpl;
import part4.lesson16.task01.model.Role;
import part4.lesson16.task01.model.User;
import part4.lesson16.task01.model.enums.RolesNames;

/**
 * MyPreparedStatement
 *
 * @author Ekaterina Belolipetskaya
 */
public class MyPreparedStatement {

    public static final String ADMINS_ONLY = "Admins only";
    public static final String NAME = "Kate";
    public static final String BIRTHDAY = "1992-03-11";
    public static final int LOGIN_ID = 13;
    public static final String CITY = "Tambov";
    public static final String EMAIL = "kate@gmail.com";
    public static final String USER_DESCRIPTION = "first user";

    /**
     * Execute prepared statements.
     */
    public static void doStatement() {
        Role role = new Role(null, RolesNames.ADMINISTRATION, ADMINS_ONLY);
        GenericDao<Role> roleDao = new RoleDaoImpl();
        roleDao.addObject(role);

        User user = new User(null, NAME, BIRTHDAY, LOGIN_ID,
                CITY, EMAIL, USER_DESCRIPTION);
        GenericDao<User> userDao = new UserDaoImpl();
        userDao.addObject(user);
    }
}
