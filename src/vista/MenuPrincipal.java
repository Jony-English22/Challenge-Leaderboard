package vista;


import model.Participante;
import service.GestorParticipantes;
import service.GestorRetos;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import static util.Utils.formatearMenu;
import static util.Utils.limpiarPantalla;
import static util.Utils.pausar;


public class MenuPrincipal {
    private GestorParticipantes gestorParticipantes;
    private GestorRetos         gestorRetos;
    private Scanner             scanner;

    public MenuPrincipal(GestorParticipantes gestorParticipantes, GestorRetos gestorRetos) {
        this.gestorParticipantes = gestorParticipantes;
        this.gestorRetos         = gestorRetos;
        scanner                  = new Scanner(System.in);
        ejecutar();
    }

    public void ejecutar() {
        boolean continuar = true;
        int opcion = 0;

        while (continuar) {

            try {
                limpiarPantalla();
                mostrarMenuPrincipal();
                opcion = scanner.nextInt();
                scanner.nextLine();

            } catch (InputMismatchException e) {
                limpiarPantalla();
                System.out.println("Opción inválida");
                pausar(2000);
                continue;
            }

            switch (opcion) {
                case 1 -> menuVerRanking();
                case 2 -> menuGestionarParticipantes();
                case 3 -> menuCrearReto();
                case 4 -> menuIniciarReto();
                case 5 -> menuHistorial();
                case 0 -> {
                    System.out.println("SALIENDO...");
                    continuar = false;
                }
                default -> {
                    System.out.println("Opción no válida");
                    pausar(2000);
                }

            }
        }
        scanner.close();
    }

    private void mostrarMenuPrincipal() {
        String titulo = "SISTEMA DE RANKING - RETOS";

        String[] opciones = {
                "[1] Ver Ranking General",
                "[2] Gestionar Participantes",
                "[3] Crear Nuevo Reto",
                "[4] Iniciar Reto ($tart)",
                "[5] Historial de Retos",
                "[0] Salir"
        };

        // Imprimir menú formateado
        System.out.println(formatearMenu(titulo, opciones, 40));

        // Prompt de entrada
        System.out.print("Opción: ");
    }

    private void menuVerRanking() {
        // 1. Limpiar pantalla
        limpiarPantalla();

        // 2. Obtener ranking ordenado del gestor
        List<Map.Entry<Participante, Integer>> ranking = gestorParticipantes.obtenerRankingOrdenado();

        // 3. Mostrar ranking
        VistaConsola.mostrarRanking(ranking);

        // 4. Prompt para volver
        System.out.print("\nPresiona ENTER para volver...");

        // 5. Esperar ENTER
        scanner.nextLine();
    }

    private void menuGestionarParticipantes() {
        String titulo = "PARTICIPANTES";
        String[] opciones = {
                "[1] Registrar participante",
                "[2] Listar participantes",
                "[3] Actualizar participante",
                "[4] Eliminar participante",
                "[0] Volver al menú principal"
        };

        System.out.println(formatearMenu(titulo, opciones, 40));

        System.out.print("Opción: ");
    }

    private void menuCrearReto() {

    }

    private void menuIniciarReto() {

    }

    private void menuHistorial() {

    }

    private void procesarRetoEnCurso() {

    }
}
