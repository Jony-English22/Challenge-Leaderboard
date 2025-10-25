package model;

public class Resultado {
    private String nombreParticipante;
    private int puntosObtenidos;
    private int posicion;

    public Resultado(String nombreParticipante, int puntosObtenidos, int posicion) {
        this.nombreParticipante = nombreParticipante;
        this.puntosObtenidos = puntosObtenidos;
        this.posicion = posicion;
    }

    public Resultado() {
    }

    public String getNombreParticipante() {
        return nombreParticipante;
    }

    public void setNombreParticipante(String nombreParticipante) {
        this.nombreParticipante = nombreParticipante;
    }

    public int getPuntosObtenidos() {
        return puntosObtenidos;
    }

    public void setPuntosObtenidos(int puntosObtenidos) {
        this.puntosObtenidos = puntosObtenidos;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return String.format("Resultado{nombreParticipante='%s', puntosObtenidos=%d, posicion=%d}",
                nombreParticipante, puntosObtenidos, posicion);
    }
}