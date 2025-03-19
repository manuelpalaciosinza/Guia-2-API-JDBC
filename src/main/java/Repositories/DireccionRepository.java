package Repositories;

import Entities.DireccionEntity;

import java.sql.SQLException;

public class DireccionRepository implements Repository<DireccionEntity>{

    /// Implementacion del patron Singleton -> En lugar de usar constructor, nos aseguramos
    /// de que siempre haya una sola instancia
    private static DireccionRepository instance;
    private DireccionRepository(){};
    public static DireccionRepository getInstanceOf(){
        if (instance == null){
            instance = new DireccionRepository();
        }
        return instance;
    }

    @Override
    public void save(DireccionEntity direccionEntity) throws SQLException {

    }

    @Override
    public DireccionEntity findById(int id) throws SQLException {
        return null;
    }

    @Override
    public void delete(DireccionEntity direccionEntity) throws SQLException {

    }

    @Override
    public void deleteById(int id) throws SQLException {

    }

    @Override
    public int count() throws SQLException {
        return 0;
    }
}
