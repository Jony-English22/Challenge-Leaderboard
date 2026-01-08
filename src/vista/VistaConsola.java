package vista;

import model.EstadoReto;
import model.Participante;
import model.Reto;
import java.util.List;
import java.util.Map;
import static util.Utils.*;

public class VistaConsola {

    public static void mostrarRanking(List<Map.Entry<Participante, Integer>> ranking) {
        limpiarPantalla();

        String titulo = formatearTitulo("Ranking", 40);
        String[] encabezados = {"Posición", "Participante", "Puntos"};
        String[][] datos = construirMatrizRanking(ranking);
        String tablas = formatearTablas(datos, encabezados);
        System.out.println(titulo + "\n" + tablas);
    }

    public static void mostrarRetoEnCurso(Reto reto, String tiempoRestante) {
        limpiarPantalla();

        final int ANCHO_TOTAL = 80;
        final String ANCHO_BORDE = "=".repeat(ANCHO_TOTAL - 2);

        System.out.println("╔" + ANCHO_BORDE + "╗");
        String titulo = "R E T O: " + reto.getNombre();
        System.out.println("║" + centrarTexto(titulo, ANCHO_TOTAL - 2) + "║");

        String textoTimer = "Timer: ⏱️  " + tiempoRestante;
        int espaciosTimer = ANCHO_TOTAL - 2 - textoTimer.length();
        System.out.println("║" + " ".repeat(Math.max(0, espaciosTimer)) + textoTimer + "║");

        System.out.println("╠" + ANCHO_BORDE + "╣");

        if (reto.getResultados().isEmpty()) {
            System.out.println("║" + centrarTexto("Esperando participantes...", ANCHO_TOTAL - 2) + "║");
            System.out.println("╚" + ANCHO_BORDE + "╝");
        } else {
            String[] encabezados = {"N°", "Nombre", "Puntos"};
            String[][] datos = convertirResultadosAMatriz(reto.getResultados());
            mostrarTabla(encabezados, datos);
        }

        // Comandos
        System.out.println();
        System.out.println("Comandos:");
        System.out.println("  - Escribe el nombre del participante para agregarlo");
        System.out.println("  - 'fin' para terminar el reto");
        System.out.println("  - 'cancelar' para abortar");
        System.out.println();
        System.out.print("➜ ");

    }

    /**
     * Muestra el historial de retos completados
     * @param retos Lista de retos COMPLETADOS (ya filtrada)
     */
    public static void mostrarHistorial(List<Reto> retos) {
        limpiarPantalla();

        String titulo = formatearTitulo("RETOS COMPLETADOS", 40);

        if (retos.isEmpty()) {
            System.out.println("No hay retos completados.");
            return;
        }

        String[] encabezados = {"ID", "Nombre", "Participantes"};
        String[][] datos = construirMatrizRetoH(retos);
        String tabla = formatearTablas(datos, encabezados);
        System.out.println(titulo + "\n" + tabla);
    }

    public static void mostrarListaRetos(List<Reto> reto) {
        limpiarPantalla();

        String titulo = formatearTitulo("RETOS DISPONIBLES", 40);

        if (reto.isEmpty()) {
            System.out.println("No hay retos disponibles. Crear uno nuevo.");
            return;
        }

        String[] encabezados = {"ID", "Nombre", "Puntos", "Tiempo(min)"};
        String[][] datos = construirMatrizRetos(reto);
        String tabla = formatearTablas(datos, encabezados);
        System.out.println(titulo + "\n" + tabla);
        System.out.println("\nSelecciona el ID del reto para iniciar");
    }

    public static void mostrarListaParticipantes(List<Participante> participantes) {
        limpiarPantalla();

        String titulo = formatearTitulo("PARTICIPANTES", 40);

        if (participantes.isEmpty()) {
            System.out.println("No hay participantes registrados.Registrar participantes.");
            return;
        }

        String[] encabezados = {"N°", "Nombre" };
        String[][] datos = construirMatrizParticipantes(participantes);
        String tabla = formatearTablas(datos, encabezados);
        System.out.println(titulo + "\n" + tabla);
        System.out.println("\nTotal: " + participantes.size() + " participante(s)");
    }
}
