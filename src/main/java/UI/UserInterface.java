package UI;

import Entities.AlumnosEntity;
import Entities.DireccionEntity;
import Repositories.AlumnosRepository;
import Repositories.DireccionRepository;
import Services.AlumnosService;
import Services.DireccionesService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private static UserInterface instance;
    private static AlumnosService alumnosService;
    private static DireccionesService direccionesService;
    private static Scanner scanner;

    private UserInterface (){
        direccionesService = DireccionesService.getInstanceOf();
        alumnosService = AlumnosService.getInstanceOf();
        scanner = new Scanner(System.in);
    }

    public static UserInterface getInstanceOf(){
        if(instance == null){
            instance = new UserInterface();
        }
        return instance;
    }

    public static void verMenu(){
        int continuar = 9;
        int opcion = 0;
        do {

            System.out.printf("Bienvenido al menu principal!" +
                    "\nIngrese 1 para insertar un alumno" +
                    "\nIngrese 2 para insertar una direccion" +
                    "\nIngrese 3 para ver todos los alumnos con sus direcciones" +
                    "\nIngrese 4 para ver todas las direcciones" +
                    "\nIngrese 5 para ver todas las direcciones de un alumno" +
                    "\nIngrese 6 para modificar la edad de un alumno" +
                    "\nIngrese 7 para eliminar un alumno" +
                    "\nIngrese 8 para eliminar una direccion\n");

            opcion = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcion) {
                    case 1 -> alumnosService.registrarAlumno(cargarAlumno());
                    case 2 -> direccionesService.agregarDireccion(cargarDireccion());
                    case 3 -> System.out.println(alumnosService.listarAlumnosConDirecciones());
                    case 4 -> System.out.println(direccionesService.verTodas());
                    case 5 -> System.out.println(retornarListaPorAlumno());
                    case 6 -> actualizarEdadAlumno();
                    case 7 -> eliminarAlumno();
                    case 8 -> eliminarDireccion();
                    default -> System.out.println("\nOpcion ingresada invalida");
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }

            System.out.printf("Ingrese 1 para volver al menu principal\n");
            continuar = scanner.nextInt();
            scanner.nextLine();
        }while (continuar == 1);
    }


    private static AlumnosEntity cargarAlumno (){
        AlumnosEntity alumno = new AlumnosEntity();
        System.out.println("\nIngrese nombre del alumno: ");
        alumno.setNombre(scanner.nextLine());
        System.out.println("\nIngrese apellido del alumno: ");
        alumno.setApellido(scanner.nextLine());
        System.out.println("\nIngrese la edad del alumno: ");
        alumno.setEdad(scanner.nextInt());
        scanner.nextLine();
        System.out.println("\nIngrese el email del alumno: ");
        alumno.setEmail(scanner.nextLine());
        return alumno;
    }
    private static DireccionEntity cargarDireccion(){
        DireccionEntity direccion = new DireccionEntity();
        System.out.println("\nIngrese nombre de la calle: ");
        direccion.setCalle(scanner.nextLine());
        System.out.println("\nIngrese altura de la calle: ");
        direccion.setAltura(scanner.nextInt());
        scanner.nextLine();
        System.out.println("\nIngrese la id del alumno: ");
        direccion.setAlumno_id(scanner.nextInt());
        scanner.nextLine();
        return direccion;
    }
    private static String retornarListaPorAlumno() throws SQLException{
        System.out.println("Ingrese la id del alumno cuyas direcciones quiere ver: ");
        int id_alumno = scanner.nextInt();
        scanner.nextLine();
        return direccionesService.verDireccionesAlumno(id_alumno);
    }
    private static void actualizarEdadAlumno() throws SQLException{
        System.out.println(alumnosService.listarAlumnosConDirecciones());
        System.out.println("\nIngrese el id del alumno que quiere modificar: ");
        int id_modificar = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\nIngrese la nueva edad del alumno: ");
        int nueva_edad = scanner.nextInt();
        scanner.nextLine();
        alumnosService.modificarEdad(id_modificar,nueva_edad);
        System.out.println("Alumno modificado: " + alumnosService.verAlumnoPorId(id_modificar));
    }
    private static void eliminarAlumno() throws SQLException{
        System.out.println(alumnosService.listarAlumnosConDirecciones());
        System.out.println("\nIngrese el id del alumno que quiere eliminar: ");
        int id_modificar = scanner.nextInt();
        scanner.nextLine();
        alumnosService.eliminarAlumno(id_modificar);
        System.out.println(alumnosService.listarAlumnosConDirecciones());
    }
    private static void eliminarDireccion()throws SQLException{
        System.out.println(direccionesService.verTodas());
        System.out.println("\nIngrese el id de a direccion a eliminar: ");
        int id_eliminar = scanner.nextInt();
        scanner.nextLine();
        direccionesService.eliminarDireccion(id_eliminar);
        System.out.println(direccionesService.verTodas());
    }

}
