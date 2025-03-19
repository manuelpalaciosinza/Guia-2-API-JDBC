package Entities;

import java.util.ArrayList;
import java.util.List;

public class AlumnosEntity {
    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private List<DireccionEntity> direcciones;

    public AlumnosEntity(int id, String nombre, String apellido, int edad, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.email = email;
        this.direcciones = new ArrayList<DireccionEntity>();
    }

    public AlumnosEntity(String apellido, String nombre, int edad, String email) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
        this.direcciones = new ArrayList<DireccionEntity>();
    }

    public AlumnosEntity(){
        direcciones = new ArrayList<DireccionEntity>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<DireccionEntity> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<DireccionEntity> direcciones) {
        this.direcciones = direcciones;
    }

    @Override
    public String toString() {
        return "AlumnosEntity{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", email='" + email + '\'' +
                ", direcciones=" + direcciones +
                '}';
    }
}
