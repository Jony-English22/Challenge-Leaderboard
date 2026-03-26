package ui.view;

import java.util.List;
import java.util.Map;

import model.Participante;
import model.Resultado;
import model.Reto;

/**
 * Formatter - Utilidad para formatear salida en consola
 *
 * Responsabilidades:
 * - Formatear listas y tablas para display
 * - Crear mensajes de éxito, error y información
 * - Generar encabezados y separadores visuales
 * - Mantener consistencia visual en toda la aplicación
 *
 * No maneja entrada de usuario ni lógica de negocio.
 */
public class Formatter {

    /**
     * Formatea una lista de retos en tabla
     * 
     * @param retos Lista de retos a mostrar
     * @return String formateado listo para imprimir
     */
    public static String formatRetosList(List<Reto> retos) {
        if (retos.isEmpty()) {
            return "No hay retos disponibles.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(header("RETOS DISPONIBLES") + "\n");
        sb.append(String.format("%-3s %-25s %-8s %-10s %-12s\n", "ID", "Nombre", "Puntos", "Tiempo", "Estado"));
        sb.append("------------------------------------------------------------\n");
        for (Reto reto : retos) {
            sb.append(String.format("%-3d %-25s %-8d %-10s %-12s\n",
                    reto.getId(),
                    reto.getNombre().length() > 24 ? reto.getNombre().substring(0, 21) + "..." : reto.getNombre(),
                    reto.getPuntosMaximos(),
                    reto.getTiempoMinutos() + " min",
                    reto.getEstado().toString()));
        }
        return sb.toString();
    }

    /**
     * Formatea detalles completos de un reto
     * 
     * @param reto Reto a mostrar
     * @return String con detalles formateados
     */
    public static String formatRetoDetails(Reto reto) {
        StringBuilder sb = new StringBuilder();
        sb.append(header("DETALLES DEL RETO") + "\n");
        sb.append("ID: ").append(reto.getId()).append("\n");
        sb.append("Nombre: ").append(reto.getNombre()).append("\n");
        sb.append("Descripción: ").append(reto.getDescripcion() != null ? reto.getDescripcion() : "Sin descripción")
                .append("\n");
        sb.append("Puntos máximos: ").append(reto.getPuntosMaximos()).append("\n");
        sb.append("Tiempo límite: ").append(reto.getTiempoMinutos()).append(" minutos\n");
        sb.append("Estado: ").append(reto.getEstado().toString()).append("\n");
        sb.append("Número de resultados: ").append(reto.getResultados().size()).append("\n");
        if (!reto.getResultados().isEmpty()) {
            sb.append("\nResultados:\n");
            for (Resultado resultado : reto.getResultados()) {
                sb.append("- ").append(resultado.getNombreParticipante())
                        .append(": ").append(resultado.getPuntosObtenidos()).append(" puntos (Posición: ")
                        .append(resultado.getPosicion()).append(")\n");
            }
        }
        return sb.toString();
    }

    /**
     * Formatea ranking de participantes
     * 
     * @param ranking Lista ordenada de participantes con puntos
     * @return String con tabla de ranking
     */
    public static String formatRanking(List<Map.Entry<Participante, Integer>> ranking) {
        if (ranking.isEmpty()) {
            return "No hay participantes para mostrar en el ranking.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(header("RANKING DE PARTICIPANTES") + "\n");
        sb.append(String.format("%-5s %-20s %-10s\n", "Pos", "Nombre", "Puntos"));
        sb.append("-------------------------------------\n");
        int pos = 1;
        for (Map.Entry<Participante, Integer> entry : ranking) {
            sb.append(String.format("%-5d %-20s %-10d\n", pos++, entry.getKey().getNombre(), entry.getValue()));
        }
        return sb.toString();
    }

    /**
     * Formatea lista de participantes
     * 
     * @param participantes Lista de participantes
     * @return String con tabla de participantes
     */
    public static String formatParticipantesList(List<Participante> participantes) {
        if (participantes.isEmpty()) {
            return "No hay participantes registrados.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(header("participantes") + "\n");
        sb.append(String.format("%-8s %-20s\n", "ID", "Nombre"));
        sb.append("-----------------------------\n");
        for (Participante p : participantes) {
            sb.append(String.format("%-8s %-20s\n", p.getId(), p.getNombre()));
        }
        return sb.toString();
    }

    /**
     * Formatea detalles de un participante
     * 
     * @param participante Participante a mostrar
     * @return String con detalles formateados
     */
    public static String formatParticipanteDetails(Participante participante) {
        // TODO: Implementar detalles del participante
        // Incluir: ID, Nombre, Puntos totales
        return "ID: " + participante.getId() + "\nNombre: " + participante.getNombre();
    }

    /**
     * Crea mensaje de éxito
     * 
     * @param mensaje Mensaje a mostrar
     * @return String formateado con color/estilo de éxito
     */
    public static String success(String mensaje) {
        return "\u001B[32m✅ " + mensaje + "\u001B[32m";
    }

    /**
     * Crea mensaje de error
     * 
     * @param mensaje Mensaje de error
     * @return String formateado con estilo de error
     */
    public static String error(String mensaje) {
        return "\u001B[0m❌ " + mensaje + "\u001B[0m";
    }

    /**
     * Crea mensaje informativo
     * 
     * @param mensaje Mensaje informativo
     * @return String formateado con color naranja
     */
    public static String info(String mensaje) {
        return "\033[33mℹ️ " + mensaje + "\033[0m";
    }

    /**
     * Crea encabezado para secciones
     * 
     * @param titulo Título de la sección
     * @return String con encabezado formateado
     */
    public static String header(String titulo) {
        return "=== " + titulo.toUpperCase() + " ===";
    }

    /**
     * Crea prompt para entrada de usuario
     * 
     * @param prompt Texto del prompt
     * @return String formateado para prompt
     */
    public static String prompt(String prompt) {
        return prompt + " ";
    }

    /**
     * Limpia la pantalla de consola
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Pausa la ejecución por segundos
     * 
     * @param segundos Tiempo de pausa
     */
    public static void pause(int segundos) {
        try {
            Thread.sleep(segundos * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}