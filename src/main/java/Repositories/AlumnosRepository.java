package Repositories;

import Entities.AlumnosEntity;
import Connections.ConexionSQLITE;
import org.sqlite.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

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
            preparedStatement.setString(1,alumnosEntity.getNombre()); ///Los sets le indican al prepare statement que valores ocupan cada ?
            preparedStatement.setString(2,alumnosEntity.getApellido());
            preparedStatement.setInt(3,alumnosEntity.getEdad());
            preparedStatement.setString(4,alumnosEntity.getEmail());
            preparedStatement.executeUpdate(); ///Siempre que sea insercion o modificacion, uso executeUpdate
        }
    }

    @Override
    public Optional<AlumnosEntity> findById(int id) throws SQLException {
        try (Connection connection = ConexionSQLITE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("SELECT * FROM alumnos WHERE id = ?")){
            preparedStatement.setInt(1,id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){ ///Si necesito recibir informacion, uso executeQuery
                if (resultSet.next()){
                    return Optional.of(new AlumnosEntity(
                            resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getInt("edad"),
                        resultSet.getString("email")));
                }
                else {
                    return Optional.empty();
                }
            }
        }
    }

    @Override
    public void delete(AlumnosEntity alumnosEntity) throws SQLException {
        try (Connection connection = ConexionSQLITE.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM alumnos WHERE id = ?")){
            preparedStatement.setInt(1,alumnosEntity.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteById(int id) throws SQLException {
        try(Connection connection = ConexionSQLITE.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM alumnos WHERE id = ?")){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public int count() throws SQLException {
        try (Connection connection = ConexionSQLITE.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT COUNT(*) FROM alumnos")){
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getInt(1);
                }
                else {
                    return 0;
                }
            }
        }
    }

    public void updateAge(int id, int newAge) throws SQLException{
        try (Connection connection = ConexionSQLITE.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                ("UPDATE alumnos SET age = ? WHERE id = ?"))){
            preparedStatement.setInt(1,newAge);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        }
    }
}
