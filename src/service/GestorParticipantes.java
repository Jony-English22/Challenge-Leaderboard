package service;

import model.Participante;
import model.Resultado;
import model.Reto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private String generarId(List<Participante> participantes) {
        int max = 0;

        for (Participante p : participantes) {
            String numero = p.getId().substring(2);
            int valor = Integer.parseInt(numero);
            if (valor > max) {
                max = valor;
            }
        }
        return String.format("RK%03d", max + 1);
    }

    public boolean registrarParticipante(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }

        GestorDatos.DatosApp datos = gestorDatos.cargarDatos();
        String id = generarId(datos.participantes);
        Participante nuevo = new Participante(id, nombre);
        datos.participantes.add(nuevo);
        gestorDatos.guardarDatos(datos);
        return true;

    }

    public boolean eliminarParticipante(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }

        GestorDatos.DatosApp datos = gestorDatos.cargarDatos();
        for (int i = 0; i < datos.participantes.size(); i++) {
            Participante p = datos.participantes.get(i);
            if (p.getNombre().equals(nombre)) {
                datos.participantes.remove(i);
                gestorDatos.guardarDatos(datos);
                return true;
            }
        }
        return false;
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
        GestorDatos.DatosApp datos = gestorDatos.cargarDatos();
     return datos.participantes;
    }

    public int calcularPuntosTotales(String nombre) {
        if (gestorRetos == null) {
            return 0;
        }

        GestorDatos.DatosApp datos = gestorDatos.cargarDatos();
        int puntosTotales = 0;

        for (Reto reto : datos.retos) {
            for (Resultado resultado : reto.getResultados()) {
                if (resultado.getNombreParticipante().equals(nombre)) {
                    puntosTotales  += resultado.getPuntosObtenidos();
                }
            }
        }
        return puntosTotales;
    }

    public boolean existeParticipante(String nombre) {
        GestorDatos.DatosApp datos = gestorDatos.cargarDatos();

        for (Participante p: datos.participantes) {
            if (p.getNombre().equals(nombre)) {
                return  true;
            }
        }
        return false;
    }

    public List<Map.Entry<Participante, Integer>> obtenerRankingOrdenado() {
        Map<Participante, Integer> puntosMap = new HashMap<>();
        List<Participante> participantes = listarParticipantes();

        for (Participante p : participantes) {
            int puntos = calcularPuntosTotales(p.getNombre());
            puntosMap.put(p, puntos);
        }

        return puntosMap.entrySet().stream().sorted(Map.Entry.<Participante, Integer>comparingByValue().reversed()).collect(Collectors.toList());

    }

    public void cargarParticipantes() {
        GestorDatos.DatosApp datos = gestorDatos.cargarDatos();
        this.participantes = new ArrayList<>(datos.participantes);

    }

    public boolean actualizarParticipante(String nombreActual, String nombreNuevo) {
        if (nombreActual == null || nombreActual.trim().isEmpty() || 
            nombreNuevo == null || nombreNuevo.trim().isEmpty()) {
            return false;
        }

        GestorDatos.DatosApp datos = gestorDatos.cargarDatos();

        for (Participante p : datos.participantes) {
            if (p.getNombre().equals(nombreActual)) {
                p.setNombre(nombreNuevo);
                gestorDatos.guardarDatos(datos);
                return true;
            }
        }
        return false;
    }
}
