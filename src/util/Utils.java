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
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    public static void emitirSonido() {
        java.awt.Toolkit.getDefaultToolkit().beep();
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
