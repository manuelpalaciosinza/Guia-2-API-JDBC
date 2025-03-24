package Repositories;

import Connections.ConexionSQLITE;
import Entities.AlumnosEntity;
import Entities.DireccionEntity;
import org.sqlite.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
                    ("INSERT INTO direcciones (calle, altura, alumno_id) VALUES (?, ?, ?)")){
            preparedStatement.setString(1,direccionEntity.getCalle()); ///Los sets le indican al prepare statement que valores ocupan cada ?
            preparedStatement.setInt(2,direccionEntity.getAltura());
            preparedStatement.setInt(3,direccionEntity.getAlumno_id());
            preparedStatement.executeUpdate(); ///Siempre que sea insercion o modificacion, uso executeUpdate
        }
    }

    @Override
    public Optional<DireccionEntity> findById(int id) throws SQLException {
        try (Connection connection = ConexionSQLITE.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM direcciones WHERE id = ?")){
            preparedStatement.setInt(1,id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    return Optional.of(new DireccionEntity(id,
                            resultSet.getString("calle"),
                            resultSet.getInt("altura"),
                            resultSet.getInt("alumno_id")));
                }
                else {
                    return Optional.empty();
                }
            }
        }
    }

    @Override
    public void delete(DireccionEntity direccionEntity) throws SQLException {
        try(Connection connection = ConexionSQLITE.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM alumnos WHERE id = ?")){
            preparedStatement.setInt(1,direccionEntity.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteById(int id) throws SQLException {
        try(Connection connection = ConexionSQLITE.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM direcciones WHERE id = ?")){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public int count() throws SQLException {
        try (Connection connection = ConexionSQLITE.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT COUNT(*) FROM direcciones"
        )){
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        }
    }

    @Override
    public ArrayList<DireccionEntity> findAll() throws SQLException {
        ArrayList <DireccionEntity> listaDirecciones = new ArrayList<DireccionEntity>();
        try (Connection connection = ConexionSQLITE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT id,calle,altura,alumno_id FROM direcciones")){
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    DireccionEntity direccion = new DireccionEntity();
                    direccion.setId(resultSet.getInt("id"));
                    direccion.setCalle(resultSet.getString("calle"));
                    direccion.setAltura(resultSet.getInt("altura"));
                    direccion.setAlumno_id(resultSet.getInt("alumno_id"));

                    listaDirecciones.add(direccion); // Agregar la direccion a la lista
                }
            }
        }
        return listaDirecciones; ///Retorno la lista completa
    }
    public ArrayList<DireccionEntity> findByIdAlumno(int id_alumno)throws SQLException{
        ArrayList <DireccionEntity> listaDirecciones = new ArrayList<DireccionEntity>();
        try (Connection connection = ConexionSQLITE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT id,calle,altura,alumno_id FROM direcciones WHERE alumno_id = ?"))
        {
            preparedStatement.setInt(1,id_alumno);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    DireccionEntity direccion = new DireccionEntity();
                    direccion.setId(resultSet.getInt("id"));
                    direccion.setCalle(resultSet.getString("calle"));
                    direccion.setAltura(resultSet.getInt("altura"));
                    direccion.setAlumno_id(resultSet.getInt("alumno_id"));

                    listaDirecciones.add(direccion); // Agregar la direccion a la lista
                }
            }
        }
        return listaDirecciones; ///Retorno la lista completa
    }

    public int countById(int id) throws SQLException {
        ///Metodo que me deja hacer verificaciones para saber si ya hay direcciones con un id
        try (Connection connection = ConexionSQLITE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT COUNT(*) FROM direcciones WHERE id = ?")){
            preparedStatement.setInt(1,id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next())
                {
                    return resultSet.getInt(1);
                }
                else return 0;
            }
        }
    }

}
