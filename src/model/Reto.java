package model;

import java.util.ArrayList;
import java.util.List;

public class Reto {
    private int id;
    private String nombre;
    private int puntosMaximos;
    private int tiempoMinutos;
    private EstadoReto estado;
    private List<Resultado> resultados;

    public Reto(int id, String nombre, int puntosMaximos, int tiempoMinutos) {
        this.id = id;
        this.nombre = nombre;
        this.puntosMaximos = puntosMaximos;
        this.tiempoMinutos = tiempoMinutos;
        this.estado = EstadoReto.PENDIENTE;  // Por defecto inicia en PENDIENTE
        this.resultados = new ArrayList<>();
    }

    public Reto() {
        this.resultados = new ArrayList<>();
    }

    public void agregarResultado(Resultado resultado) {
        if (resultado != null) {
            this.resultados.add(resultado);
        }
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

    public int getPuntosMaximos() {
        return puntosMaximos;
    }

    public void setPuntosMaximos(int puntosMaximos) {
        this.puntosMaximos = puntosMaximos;
    }

    public int getTiempoMinutos() {
        return tiempoMinutos;
    }

    public void setTiempoMinutos(int tiempoMinutos) {
        this.tiempoMinutos = tiempoMinutos;
    }

    public EstadoReto getEstado() {
        return estado;
    }

    public void setEstado(EstadoReto estado) {
        this.estado = estado;
    }

    public List<Resultado> getResultados() {
        return resultados;
    }

    public void setResultados(List<Resultado> resultados) {
        this.resultados = resultados;
    }

    @Override
    public String toString() {
        return String.format("Reto{id=%d, nombre='%s', puntosMaximos=%d, tiempoMinutos=%d, estado=%s, resultados=%s}",
                id, nombre, puntosMaximos, tiempoMinutos, estado, resultados);
    }
}