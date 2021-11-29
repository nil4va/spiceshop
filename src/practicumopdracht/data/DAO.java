package practicumopdracht.data;

import java.util.List;

/**
 * interface voor alle dao's
 * @author Nilava Kazal [studentennummer: 500847707]
 */

public interface DAO<T> {
    List<T> getAll();
    void addOrUpdate(T object);
    void remove(T object);
    boolean save();
    boolean load();

}
