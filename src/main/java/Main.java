import Entities.AlumnosEntity;
import Entities.DireccionEntity;
import Services.AlumnosService;
import Services.DireccionesService;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Obtener instancias de los servicios
            AlumnosService alumnosService = AlumnosService.getInstanceOf();
            DireccionesService direccionesService = DireccionesService.getInstanceOf();

            alumnosService.modificarEdad(8,20);
            System.out.println(alumnosService.listarAlumnosConDirecciones());


            /*// Ejemplo: Registrar un nuevo alumno
            AlumnosEntity nuevoAlumno = new AlumnosEntity();
            nuevoAlumno.setNombre("Manuel");
            nuevoAlumno.setApellido("Palacios");
            nuevoAlumno.setEdad(20);
            nuevoAlumno.setEmail("manupalacioss@gmail.com");
            alumnosService.registrarAlumno(nuevoAlumno);




            // Ejemplo: Listar todos los alumnos
            System.out.println("\nLista de todos los alumnos:");
            String listaAlumnos = alumnosService.listarAlumnosConDirecciones();
            System.out.println("\n" + listaAlumnos);



            // Ejemplo: Agregar una dirección para un alumno
            DireccionEntity nuevaDireccion = new DireccionEntity();
            nuevaDireccion.setCalle("Calle Principal");
            nuevaDireccion.setAltura(123);
            nuevaDireccion.setAlumno_id(7); // Asignar al alumno con ID 7
            direccionesService.agregarDireccion(nuevaDireccion);


            ///Ejemplo: Mostrar todas las direcciones
            System.out.println("\n\nLista de todas las direcciones:");
            System.out.println("\n" + direccionesService.verTodas());

            // Ejemplo: Mostrar todas las direcciones de un alumno
            System.out.println("\nDirecciones del alumno con ID 7:");
            String direccionesAlumno = direccionesService.verDireccionesAlumno(7);
            System.out.println(direccionesAlumno);
*/
        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

