package ui.view;

import java.util.List;
import java.util.Map;

import model.Participante;
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
        // TODO: Implementar formateo de lista de retos
        // Debe incluir: ID, Nombre, Estado, Participantes
        // Usar formato de tabla con bordes
        return "TODO: Implementar formatRetosList";
    }

    /**
     * Formatea detalles completos de un reto
     * 
     * @param reto Reto a mostrar
     * @return String con detalles formateados
     */
    public static String formatRetoDetails(Reto reto) {
        // TODO: Implementar detalles del reto
        // Incluir: nombre, descripción, puntos, tiempo, estado, participantes
        return "TODO: Implementar formatRetoDetails";
    }

    /**
     * Formatea ranking de participantes
     * 
     * @param ranking Lista ordenada de participantes con puntos
     * @return String con tabla de ranking
     */
    public static String formatRanking(List<Map.Entry<Participante, Integer>> ranking) {
        // TODO: Implementar formateo de ranking
        // Posición, Nombre, Puntos totales
        return "TODO: Implementar formatRanking";
    }

    /**
     * Formatea lista de participantes
     * 
     * @param participantes Lista de participantes
     * @return String con tabla de participantes
     */
    public static String formatParticipantesList(List<Participante> participantes) {
        // TODO: Implementar lista de participantes
        // Número, ID, Nombre
        return "TODO: Implementar formatParticipantesList";
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
        // TODO: Implementar mensaje de éxito
        // Usar colores ANSI o símbolos como ✅
        return "✅ " + mensaje;
    }

    /**
     * Crea mensaje de error
     * 
     * @param mensaje Mensaje de error
     * @return String formateado con estilo de error
     */
    public static String error(String mensaje) {
        // TODO: Implementar mensaje de error
        // Usar colores ANSI o símbolos como ❌
        return "❌ " + mensaje;
    }

    /**
     * Crea mensaje informativo
     * 
     * @param mensaje Mensaje informativo
     * @return String formateado
     */
    public static String info(String mensaje) {
        // TODO: Implementar mensaje informativo
        // Usar colores ANSI o símbolos como ℹ️
        return "ℹ️ " + mensaje;
    }

    /**
     * Crea encabezado para secciones
     * 
     * @param titulo Título de la sección
     * @return String con encabezado formateado
     */
    public static String header(String titulo) {
        // TODO: Implementar encabezado
        // Usar líneas de separación y centrado
        return "=== " + titulo.toUpperCase() + " ===";
    }

    /**
     * Crea prompt para entrada de usuario
     * 
     * @param prompt Texto del prompt
     * @return String formateado para prompt
     */
    public static String prompt(String prompt) {
        // TODO: Implementar prompt
        // Ejemplo: "ranking> "
        return prompt + "> ";
    }

    /**
     * Limpia la pantalla de consola
     */
    public static void clearScreen() {
        // TODO: Implementar limpieza de pantalla
        // Usar secuencias ANSI o comandos del sistema
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Pausa la ejecución por segundos
     * 
     * @param segundos Tiempo de pausa
     */
    public static void pause(int segundos) {
        // TODO: Implementar pausa
        try {
            Thread.sleep(segundos * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}