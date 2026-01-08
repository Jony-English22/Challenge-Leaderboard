package util;

import model.Participante;
import model.Resultado;
import model.Reto;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Utils {

    public static void limpiarPantalla() {
        try {
            String os = System.getProperty("os.name");

            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls")
                        .inheritIO()
                        .start()
                        .waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50 ; i++) {
                System.out.println();
            }
        }
    }

    public static void emitirSonido() {
        java.awt.Toolkit.getDefaultToolkit().beep();
    }

    public static String formatearTablas(String[][] datos, String[] encabezados) {
        StringBuilder tabla = new StringBuilder();

        int numColumnas = encabezados.length;
        int[] ancho = new int[numColumnas];

        // Calcular anchos
        for (int i = 0; i < numColumnas; i++) {
            ancho[i] = encabezados[i].length();
        }

        for (String[] fila : datos) {
            for (int i = 0; i < numColumnas; i++) {
                if (fila[i].length() > ancho[i]) {
                    ancho[i] = fila[i].length();
                }
            }
        }

        // Borde Superior
        tabla.append("╔");
        for (int i = 0; i < numColumnas; i++) {
            tabla.append("═".repeat(ancho[i] + 2));
            tabla.append(i < numColumnas - 1 ? "╦" : "╗\n");
        }

        // Encabezados (CORREGIDO: faltaba "║" al final)
        tabla.append("║");
        for (int i = 0; i < numColumnas; i++) {
            tabla.append(" ");
            tabla.append(centrarTexto(encabezados[i], ancho[i]));
            tabla.append(" ║");  // ← AGREGADO
        }
        tabla.append("\n");

        // Borde Medio (CORREGIDO: usar "═" en lugar de "=")
        tabla.append("╠");
        for (int i = 0; i < numColumnas; i++) {
            tabla.append("═".repeat(ancho[i] + 2));  // ← CORREGIDO
            tabla.append(i < numColumnas - 1 ? "╬" : "╣\n");
        }

        // Filas de Datos
        for (String[] fila : datos) {
            tabla.append("║");
            for (int i = 0; i < numColumnas; i++) {
                tabla.append(" ");
                tabla.append(String.format("%-" + ancho[i] + "s", fila[i]));
                tabla.append(" ║");
            }
            tabla.append("\n");
        }

        // Borde Inferior (CORREGIDO: usar "═")
        tabla.append("╚");
        for (int i = 0; i < numColumnas; i++) {
            tabla.append("═".repeat(ancho[i] + 2));  // ← CORREGIDO
            tabla.append(i < numColumnas - 1 ? "╩" : "╝\n");
        }

        return tabla.toString();
    }

    public static String formatearMenu(String titulo, String[] opciones, int anchoMinimo) {
        StringBuilder menu = new StringBuilder();

        // Calcular ancho necesario (el mayor entre título, opciones y mínimo)
        int ancho = anchoMinimo;

        if (titulo.length() > ancho) {
            ancho = titulo.length();
        }

        for (String opcion : opciones) {
            if (opcion.length() > ancho) {
                ancho = opcion.length();
            }
        }

        // Agregar margen interno
        ancho += 4;

        // ═══ Borde Superior ═══
        menu.append("╔");
        menu.append("═".repeat(ancho));
        menu.append("╗\n");

        // ═══ Título Centrado ═══
        menu.append("║");
        menu.append(centrarTexto(titulo, ancho));
        menu.append("║\n");

        // ═══ Línea Divisoria ═══
        menu.append("╠");
        menu.append("═".repeat(ancho));
        menu.append("╣\n");

        // ═══ Opciones ═══
        for (String opcion : opciones) {
            menu.append("║");
            menu.append("  ");  // Margen izquierdo
            menu.append(String.format("%-" + (ancho - 2) + "s", opcion));
            menu.append("║\n");
        }

        // ═══ Borde Inferior ═══
        menu.append("╚");
        menu.append("═".repeat(ancho));
        menu.append("╝");

        return menu.toString();
    }

    public static String formatearMensaje(String titulo, String mensaje, int anchoMinimo) {
        StringBuilder cuadro = new StringBuilder();

        // Calcular ancho
        int ancho = anchoMinimo;
        if (titulo.length() + 4 > ancho) {
            ancho = titulo.length() + 4;
        }
        if (mensaje != null && mensaje.length() + 4 > ancho) {
            ancho = mensaje.length() + 4;
        }

        // Borde Superior
        cuadro.append("╔");
        cuadro.append("═".repeat(ancho));
        cuadro.append("╗\n");

        // Título Centrado
        cuadro.append("║");
        cuadro.append(centrarTexto(titulo, ancho));
        cuadro.append("║\n");

        // Si hay mensaje, agregar divisor y mensaje
        if (mensaje != null && !mensaje.isEmpty()) {
            cuadro.append("╠");
            cuadro.append("═".repeat(ancho));
            cuadro.append("╣\n");

            cuadro.append("║");
            cuadro.append(centrarTexto(mensaje, ancho));
            cuadro.append("║\n");
        }

        // Borde Inferior
        cuadro.append("╚");
        cuadro.append("═".repeat(ancho));
        cuadro.append("╝");

        return cuadro.toString();
    }

    public static String formatearDetalles(String titulo, String[] etiquetas, String[] valores, int anchoMinimo) {
        StringBuilder cuadro = new StringBuilder();

        // Calcular ancho de etiqueta más larga
        int anchoEtiqueta = 0;
        for (String etiqueta : etiquetas) {
            if (etiqueta.length() > anchoEtiqueta) {
                anchoEtiqueta = etiqueta.length();
            }
        }

        // Calcular ancho total necesario
        int ancho = anchoMinimo;
        if (titulo.length() + 4 > ancho) {
            ancho = titulo.length() + 4;
        }

        for (int i = 0; i < valores.length; i++) {
            // Formato: "  Etiqueta:  valor  "
            int lineaAncho = 2 + anchoEtiqueta + 2 + valores[i].length() + 2;
            if (lineaAncho > ancho) {
                ancho = lineaAncho;
            }
        }

        // Borde Superior
        cuadro.append("╔");
        cuadro.append("═".repeat(ancho));
        cuadro.append("╗\n");

        // Título Centrado
        cuadro.append("║");
        cuadro.append(centrarTexto(titulo, ancho));
        cuadro.append("║\n");

        // Línea Divisoria
        cuadro.append("╠");
        cuadro.append("═".repeat(ancho));
        cuadro.append("╣\n");

        // Filas de detalles
        for (int i = 0; i < etiquetas.length; i++) {
            cuadro.append("║  ");
            // Etiqueta alineada a la derecha
            cuadro.append(String.format("%" + anchoEtiqueta + "s", etiquetas[i]));
            cuadro.append(": ");
            // Valor alineado a la izquierda + relleno
            int espacioValor = ancho - anchoEtiqueta - 6; // 6 = "║  " + ": " + "║"
            cuadro.append(String.format("%-" + espacioValor + "s", valores[i]));
            cuadro.append(" ║\n");
        }

        // Borde Inferior
        cuadro.append("╚");
        cuadro.append("═".repeat(ancho));
        cuadro.append("╝");

        return cuadro.toString();
    }

    /**
     * Formatea un título simple (sin contenido adicional)
     */
    public static String formatearTitulo(String titulo, int anchoMinimo) {
        return formatearMensaje(titulo, null, anchoMinimo);
    }

    public static String centrarTexto(String texto, int ancho) {
        int izq = (ancho - texto.length()) /2;
        int der = ancho - texto.length() - izq;
        return " ".repeat(izq) + texto + " ".repeat(der);
    }

    public static void pausar(long segundos) {
        try {
            Thread.sleep(segundos * 1000L);
        } catch (InterruptedException e) {
            System.out.println("Pausa interrumpida: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public static void mostrarTabla(String[] encabezados, String[][] datos) {
        if (datos == null || datos.length == 0) {
            System.out.println("║         No hay datos para mostrar         ║");
            return;
        }

        int[] anchos = calcularAnchosColumnas(encabezados, datos);

        String lineaSuperior = construirLinea(anchos, '╔', '╦', '╗', '═');
        String lineaMedia = construirLinea(anchos, '╠', '╬', '╣', '═');
        String lineaInferior = construirLinea(anchos, '╚', '╩', '╝', '═');

        System.out.println(lineaSuperior);
        System.out.println(construirFila(encabezados, anchos));
        System.out.println(lineaMedia);

        for (String[] fila : datos) {
            System.out.println(construirFila(fila, anchos));
        }

        System.out.println(lineaInferior);
    }

    /**
     * Calcular el ancho necesario para cada columna
     */
    private static int[] calcularAnchosColumnas(String[] encabezados, String[][] datos) {
        int[] anchos = new int[encabezados.length];

        // Ancho mínimo basado en encabezados
        for (int i = 0; i < encabezados.length; i++) {
            anchos[i] = encabezados[i].length();
        }

        // Ajustar según los datos
        for (String[] fila : datos) {
            for (int i = 0; i < fila.length && i < anchos.length; i++) {
                if (fila[i] != null && fila[i].length() > anchos[i]) {
                    anchos[i] = fila[i].length();
                }
            }
        }

        for (int i = 0; i < anchos.length; i++) {
            anchos[i] += 2; // 1 espacio a cada lado
        }

        return anchos;
    }

    /**
     * Contruye una línea separadora
     */
    private static String construirLinea(int[] anchos, char inicio, char medio, char fin, char relleno) {
        StringBuilder sb = new StringBuilder();
        sb.append(inicio);

        for (int i = 0; i < anchos.length; i++) {
            sb.append(String.valueOf(relleno).repeat(anchos[i]));
            sb.append(i < anchos.length - 1 ? medio : fin);
        }

        return sb.toString();
    }

    /**
     * Construye una fila de datos
     */
    private static String construirFila(String[] datos, int[] anchos) {
        StringBuilder sb = new StringBuilder();
        sb.append("║");

        for (int i = 0; i < anchos.length; i++) {
            String valor = (i < datos.length && datos[i] != null ? datos[i] : "");
            sb.append(centrarTexto(valor, anchos[i]));
            sb.append("║");
        }

        return sb.toString();
    }

    @SafeVarargs
    public static <T> void mostrarTabla(List<T> lista, String[] encabezados, Function<T, String>... extractores) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("║         No hay datos para mostrar         ║");
            return;
        }

        // Convertir lista a matriz usando los extractores
        String[][] datos = new String[lista.size()][encabezados.length];

        for (int i = 0; i < lista.size(); i++) {
            T objecto = lista.get(i);
            for (int j = 0; j < extractores.length; j++) {
                datos[i][j] = extractores[j].apply(objecto);
            }
        }

        mostrarTabla(encabezados, datos);

    }

    // ═══════════════════════════════════════════════════════════
    //    MÉTODOS DE CONSTRUCCIÓN DE MATRICES PARA TABLAS
    // ═══════════════════════════════════════════════════════════

    /**
     * Convierte lista de resultados en matriz para tabla
     * @param resultados Lista de resultados del reto
     * @return Matriz [posición, nombre, puntos]
     */
    public static String[][] convertirResultadosAMatriz(List<Resultado> resultados) {
        String[][] datos = new String[resultados.size()][3];

        for (int i = 0; i < resultados.size(); i++) {
            Resultado r = resultados.get(i);
            datos[i][0] = String.valueOf(r.getPosicion());
            datos[i][1] = r.getNombreParticipante();
            datos[i][2] = String.valueOf(r.getPuntosObtenidos());
        }

        return datos;
    }

    /**
     * Convierte ranking en matriz para tabla
     * @param ranking Lista ordenada de participantes con puntos
     * @return Matriz [posición, nombre, puntos totales]
     */
    public static String[][] construirMatrizRanking(List<Map.Entry<Participante, Integer>> ranking) {
        String[][] matriz = new String[ranking.size()][3];
        for (int i = 0; i < ranking.size(); i++) {
            Map.Entry<Participante, Integer> entry = ranking.get(i);
            matriz[i][0] = String.valueOf(i + 1);
            matriz[i][1] = entry.getKey().getNombre();
            matriz[i][2] = String.valueOf(entry.getValue());
        }
        return matriz;
    }

    /**
     * Convierte lista de participantes en matriz para tabla
     * @param participantes Lista de participantes
     * @return Matriz [número, nombre]
     */
    public static String[][] construirMatrizParticipantes(
            List<Participante> participantes) {
        String[][] matriz = new String[participantes.size()][2];
        for (int i = 0; i < participantes.size(); i++) {
            Participante p = participantes.get(i);
            matriz[i][0] = String.valueOf(i + 1);
            matriz[i][1] = p.getNombre();
        }
        return matriz;
    }

    /**
     * Convierte lista de retos en matriz para tabla
     * @param retos Lista de retos
     * @return Matriz [id, nombre, puntos máximos, tiempo]
     */
    public static String[][] construirMatrizRetos(List<Reto> retos) {
        String[][] matriz = new String[retos.size()][4];
        for (int i = 0; i < retos.size(); i++) {
            Reto r = retos.get(i);
            matriz[i][0] = String.valueOf(r.getId());
            matriz[i][1] = r.getNombre();
            matriz[i][2] = String.valueOf(r.getPuntosMaximos());
            matriz[i][3] = String.valueOf(r.getTiempoMinutos());
        }
        return matriz;
    }

    public static String[][] construirMatrizRetoH(List<Reto> retos) {
        String[][] matriz = new String[retos.size()][3];
        for (int i = 0; i < retos.size(); i++) {
            Reto r = retos.get(i);
            matriz[i][0] = String.valueOf(r.getId());
            matriz[i][1] = r.getNombre();
            matriz[i][2] = String.valueOf(r.getResultados().size());
        }
        return matriz;
    }
}
