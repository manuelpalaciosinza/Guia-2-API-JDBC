package Entities;

public class DireccionEntity {
    private int id;
    private String calle;
    private int altura;
    private int alumno_id;

    public DireccionEntity(int id, String calle, int altura, int alumno_id) {
        this.id = id;
        this.calle = calle;
        this.altura = altura;
        this.alumno_id = alumno_id;
    }
    public DireccionEntity(String calle, int altura, int alumno_id) {
        this.id = 0;
        this.calle = calle;
        this.altura = altura;
        this.alumno_id = alumno_id;
    }
    public DireccionEntity(){
        id = 0;
        calle = null;
        altura = 0;
        alumno_id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getAlumno_id() {
        return alumno_id;
    }

    public void setAlumno_id(int alumno_id) {
        this.alumno_id = alumno_id;
    }

    @Override
    public String toString() {
        return "DireccionEntity{" +
                "id=" + id +
                ", calle='" + calle + '\'' +
                ", altura=" + altura +
                ", alumno_id=" + alumno_id +
                '}';
    }
}
