package part3.lesson15.task01;

import part3.lesson15.task01.dao.GenericDao;
import part3.lesson15.task01.dao.RoleDaoImpl;
import part3.lesson15.task01.dao.UserDaoImpl;
import part3.lesson15.task01.model.Role;
import part3.lesson15.task01.model.User;
import part3.lesson15.task01.model.enums.RolesNames;

/**
 * MyPreparedStatement
 * @author Ekaterina Belolipetskaya
 */
public class MyPreparedStatement {

    /**
     * Execute prepared statements.
     */
    public static void doStatement() {
        Role role = new Role(null, RolesNames.ADMINISTRATION, "Admins only");
        GenericDao<Role> roleDao = new RoleDaoImpl();
        roleDao.addObject(role);

        User user = new User(null, "Kate", "1992-03-11", 13,
                "Tambov", "kate@gmail.com", "first user");
        GenericDao<User> userDao = new UserDaoImpl();
        userDao.addObject(user);
    }
}
