package Repositories;

import Connections.ConexionSQLITE;
import Entities.DireccionEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

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
        try(Connection connection = ConexionSQLITE.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO direcciones (calle, altura, id_alumno) VALUES (, ?, ?, ?)")){
            preparedStatement.setString(1,direccionEntity.getCalle()); ///Los sets le indican al prepare statement que valores ocupan cada ?
            preparedStatement.setInt(2,direccionEntity.getAltura());
            preparedStatement.setInt(3,direccionEntity.getAlumno_id());
            preparedStatement.executeUpdate(); ///Siempre que sea insercion o modificacion, uso executeUpdate
        }
    }

    @Override
    public Optional<DireccionEntity> findById(int id) throws SQLException {
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
