package Repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    ///Las clases que implementen de Repository seran las encargadas de implementar operaciones CRUD y conexiones a la DB
    ///En cambio, las clases services se encargan de la logica de negocio, combinando Repositorys con verificaciones

    public void save(T t) throws SQLException;
    public Optional<T> findById (int id) throws SQLException;
    public void deleteById (int id) throws SQLException;
    public int count()throws SQLException;
    public ArrayList<T> findAll()throws SQLException;

}
