package Services;

import Entities.AlumnosEntity;
import Repositories.AlumnosRepository;
import Repositories.DireccionRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlumnosService {

    ///Implementacion del patron de diseño singleton para la clase Alumnos service. Esta clase utilizará instancias de Direcciones y Alumnos Repository
    private static AlumnosService instance;
    private AlumnosRepository alumnosRepository;
    private DireccionRepository direccionesRepository;

    private AlumnosService() {
        this.alumnosRepository = AlumnosRepository.getInstanceOf();
        this.direccionesRepository = DireccionRepository.getInstanceOf();
    }

    public static AlumnosService getInstanceOf() {
        if (instance == null) {
            instance = new AlumnosService();
        }
        return instance;
    }

    public void registrarAlumno (AlumnosEntity alumno)throws SQLException {
        if (alumnosRepository.countById(alumno.getId()) != 0 || alumnosRepository.countByEmail(alumno.getEmail()) != 0){ ///verifico que haya 0 alumnos con ese id
            throw new SQLException("Ya existe un alumno registrado con ese id");
        }
        alumnosRepository.save(alumno);
    }

    public void eliminarAlumno (AlumnosEntity alumno) throws SQLException{
        if (alumnosRepository.countById(alumno.getId()) == 0){
            throw new SQLException("El alumno ingresado no existe en la base de datos");
        }
        alumnosRepository.delete(alumno);
    }
    public String listarAlumnosConDirecciones ()throws SQLException{
        StringBuilder lista = new StringBuilder("Listado de Alumnos: ");
        ArrayList<AlumnosEntity> listaAlumnos = alumnosRepository.findAll();
        for (AlumnosEntity alumno : listaAlumnos){
            alumno.setDirecciones(direccionesRepository.findByIdAlumno(alumno.getId()));
            lista.append(alumno.toString());
        }
        return lista.toString();
    }
}
