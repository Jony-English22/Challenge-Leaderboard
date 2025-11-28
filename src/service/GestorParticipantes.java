package service;

import model.Participante;
import model.Reto;
import java.util.List;
import java.util.Map;

public class GestorParticipantes {
    private List<Participante> participantes;
    private GestorDatos        gestorDatos;
    private GestorRetos        gestorRetos;

    public GestorParticipantes(GestorDatos gestorDatos) {
        this.gestorDatos = gestorDatos;
    }

    public void setGestorRetos(GestorRetos gestorRetos) {
        this.gestorRetos = gestorRetos;
    }

    public boolean registrarParticipante(String nombre) {
        if (nombre.equals())
        return false;
    }

    public boolean eliminarParticipante(String nombre) {
        return true;
    }

    public Participante obtenerParticipante(String nombre) {
        return ;
    }

    public List<Participante> listarParticipantes() {
     return ;
    }

    public int calcularPuntosTotales(String nombre) {
        return ;
    }

    public boolean existeParticipantes(String nombre) {
        return ;
    }

    public List<Map.Entry<Participante, Integer>> obtenerRankingOrdenado() {

    }

    public void cargarParticipantes(List<Participante> participantes) {

    }

    public List<Participante> getParticipantes() {

    }
}
