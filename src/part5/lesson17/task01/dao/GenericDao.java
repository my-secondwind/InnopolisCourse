package part5.lesson17.task01.dao;

/**
 * Interface for general DAO
 *
 * @author Ekaterina Belolipetskaya
 */
public interface GenericDao<T> {
    boolean addObject(T object);

    T getObjectById(Integer id);

    boolean updateObjectById(T object);

    boolean deleteObjectById(Integer id);
}
