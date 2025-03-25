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

    public void eliminarAlumno (int id_alumno) throws SQLException{
        if (alumnosRepository.countById(id_alumno) == 0){
            throw new SQLException("El alumno ingresado no existe en la base de datos");
        }
        alumnosRepository.deleteById(id_alumno);
        direccionesRepository.deleteByIdAlumno(id_alumno); //Aseguro que no queden registros huerfanos

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
    public void modificarEdad(int idAlumno, int nuevaEdad) throws SQLException{
        if (alumnosRepository.countById(idAlumno) == 0){
            throw new SQLException("No existe ningun alumno registrado con esa id");
        }
        alumnosRepository.updateAge(idAlumno,nuevaEdad);
    }
    public String verAlumnoPorId (int id) throws  SQLException{
        AlumnosEntity alumnosEntity = alumnosRepository.findById(id).orElse(new AlumnosEntity());
        alumnosEntity.setDirecciones(direccionesRepository.findByIdAlumno(id));
        StringBuilder mensaje = new StringBuilder("\nAlumno: ");
        mensaje.append(alumnosEntity.toString());
        return mensaje.toString();
    }
}
