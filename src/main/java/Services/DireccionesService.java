package Services;

import Entities.AlumnosEntity;
import Entities.DireccionEntity;
import Repositories.AlumnosRepository;
import Repositories.DireccionRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class DireccionesService {


    ///Implementacion de Singleton
    private static DireccionesService instance;
    private DireccionRepository direccionRepository;
    private AlumnosRepository alumnosRepository;

    private DireccionesService(){
        direccionRepository = DireccionRepository.getInstanceOf();
        alumnosRepository = AlumnosRepository.getInstanceOf();
    }

    public static DireccionesService getInstanceOf() {
        if (instance == null) {
            instance = new DireccionesService();
        }
        return instance;
    }

    public void agregarDireccion(DireccionEntity direccion) throws SQLException{
        if (direccionRepository.countById(direccion.getId()) != 0)
        {
            throw new SQLException("Ya existe una direccion registrada con esta id");
        }
        if (alumnosRepository.countById(direccion.getAlumno_id()) == 0)
        {
            throw new SQLException("No existe ningun alumno con la id ingresada");
        }
        direccionRepository.save(direccion);
    }

    public String verDireccionesAlumno(int id_alumno) throws SQLException{
        StringBuilder lista = new StringBuilder("Direcciones del alumno: ");
        ArrayList<DireccionEntity> listaDirecciones = direccionRepository.findByIdAlumno(id_alumno);
        for (DireccionEntity direccion : listaDirecciones){
            lista.append(direccion.toString());
        }
        return lista.toString();
    }
    public String verTodas() throws SQLException{
        StringBuilder lista = new StringBuilder("Direcciones: ");
        ArrayList<DireccionEntity> listaDirecciones = direccionRepository.findAll();
        for (DireccionEntity direccion : listaDirecciones){
            lista.append(direccion.toString());
        }
        return listaDirecciones.toString();
    }

    public void eliminarDireccion (int id) throws SQLException{
        if (direccionRepository.countById(id) == 0){
            throw new SQLException("La direccion ingresada no existe en la base de datos");
        }
        direccionRepository.deleteById(id);
    }

    public void eliminarDireccionConIdAlumno (int id_alumno) throws SQLException{
        if (alumnosRepository.countById(id_alumno) == 0){
            throw new SQLException("El alumno ingresado no existe en la base de datos");
        }
        direccionRepository.deleteByIdAlumno(id_alumno);
    }

}
