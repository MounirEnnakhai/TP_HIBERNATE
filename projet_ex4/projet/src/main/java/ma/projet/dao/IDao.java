package ma.projet.dao;

import java.util.List;

public interface IDao<T> {
    void save(T entity);
    void update(T entity);
    void delete(T entity);
    T findById(int id);
    List<T> findAll();
}
