package model;

import java.util.Objects;

public class Participante {

    private String id;
    private String nombre;

    public Participante(String id, String nombre) {
        this.id     = id;
        this.nombre = nombre;
    }

    public Participante(String nombre) {
        this.nombre = nombre;
    }

    public Participante() {
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Participante that = (Participante) obj;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return String.format("Participante{id=%s, nombre='%s'}", id, nombre);
    }
}
