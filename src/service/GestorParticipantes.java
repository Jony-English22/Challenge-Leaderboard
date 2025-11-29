package service;

import model.Participante;
import model.Reto;

import java.util.ArrayList;
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
        GestorDatos.DatosApp datos = gestorDatos.cargarDatos();
        for (Participante p: datos.participantes) {
            if (p.getNombre().equals(nombre)) {
                participantes.add(new Participante(nombre));
                return false;
            }
        }

        Participante nuevo = new Participante(nombre);
        datos.participantes.add(nuevo);
        gestorDatos.guardarDatos(datos);
        return true;

    }

    public boolean eliminarParticipante(String nombre) {
        GestorDatos.DatosApp datos = gestorDatos.cargarDatos();
        for (int i = 0; i < datos.participantes.size(); i++) {
            Participante p = datos.participantes.get(i);
            if (p.getNombre().equals(nombre)) {
                datos.participantes.remove(i);
                gestorDatos.guardarDatos(datos);
                return false;
            }
        }
        return true;
    }

    public Participante obtenerParticipante(String nombre) {
        GestorDatos.DatosApp datos = gestorDatos.cargarDatos();
        for (int i = 0; i < datos.participantes.size(); i++) {
            Participante p = datos.participantes.get(i);
            if (p.getNombre().equals(nombre)) {
                return p;
            }
        }
        return null;
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
