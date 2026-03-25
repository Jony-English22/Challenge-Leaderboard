package modelo;

public class Resultado {

    private String nombreParticipante;
    private int    puntosObtenidos;
    private int    posicion;

    public Resultado(String pNombreParticipante, int pPuntosObtenidos, int pPosicion) {
        this.nombreParticipante = pNombreParticipante;
        this.puntosObtenidos    = pPuntosObtenidos;
        this.posicion           = pPosicion;
    }

    public String getNombreParticipante() {
        return nombreParticipante;
    }

    public int getPuntosObtenidos() {
        return puntosObtenidos;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setNombreParticipante(String pNombreParticipante) {
        this.nombreParticipante = pNombreParticipante;
    }

    public void setPuntosObtenidos(int pPuntosObtenidos) {
        this.puntosObtenidos = pPuntosObtenidos;
    }

    public void setPosicion(int pPosicion) {
        this.posicion = pPosicion;
    }

    @Override
    public String toString() {
        return String.format(
                "Resultado{nombreParticipante:'%s', puntosObtenidos:%d, posicion:%d}",
                nombreParticipante, puntosObtenidos, posicion
        );
    }
}
