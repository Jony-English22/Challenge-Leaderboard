package util;

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
}
