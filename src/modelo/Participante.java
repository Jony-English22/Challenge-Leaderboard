package modelo;

public class Participante {

    private String nombre;

    public Participante(String pNombre) {
        this.nombre = pNombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String pNombre) {
        this.nombre = pNombre;
    }

    @Override
    public Boolean equals() {
       return true;
    }

    @Override
    public String toString() {
        return String.format("Participante{nombre:'%s'}", nombre);
    }

}
