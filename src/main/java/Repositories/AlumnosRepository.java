package Repositories;

import Entities.AlumnosEntity;
import Connections.ConexionSQLITE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlumnosRepository implements Repository<AlumnosEntity> {

    /// Implementacion del patron Singleton -> En lugar de usar constructor, nos aseguramos
    /// de que siempre haya una sola instancia
    private static AlumnosRepository instance;
    private AlumnosRepository(){};
    public static AlumnosRepository getInstanceOf(){
        if (instance == null){
            instance = new AlumnosRepository();
        }
        return instance;
    }

    @Override
    public void save(AlumnosEntity alumnosEntity) throws SQLException {
        try(Connection connection = ConexionSQLITE.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO alumnos (nombre, apellido, edad, email) VALUES (?, ?, ?, ?)")){
            preparedStatement.setString(1,alumnosEntity.getNombre());
            preparedStatement.setString(2,alumnosEntity.getApellido());
        }
    }

    @Override
    public AlumnosEntity findById(int id) throws SQLException {
        return null;
    }

    @Override
    public void delete(AlumnosEntity alumnosEntity) throws SQLException {

    }

    @Override
    public void deleteById(int id) throws SQLException {

    }

    @Override
    public int count() throws SQLException {
        return 0;
    }
}
