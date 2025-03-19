package Repositories;

import java.sql.SQLException;

public interface Repository<T> {

    public void save(T t) throws SQLException;
    public T findById (int id) throws SQLException;
    public void delete (T t) throws SQLException;
    public void deleteById (int id) throws SQLException;
    public int count()throws SQLException;

}
