import service.GestorDatos;
import service.GestorParticipantes;
import service.GestorRetos;
import ui.menu.RankingShell;
import ui.cli.PromptRenderingCore;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inicializar gestores

        GestorDatos gestorDatos = new GestorDatos();
        GestorRetos gestorRetos = new GestorRetos(gestorDatos);
        GestorParticipantes gestorParticipantes = new GestorParticipantes(gestorDatos);

        // Conectar gestores
        gestorParticipantes.setGestorRetos(gestorRetos);
        gestorRetos.setGestorParticipantes(gestorParticipantes);

        // Lanzar interfaz CLI
        RankingShell shell = new RankingShell(gestorRetos, gestorParticipantes);
        shell.start();


        Scanner scanner = new Scanner(System.in);
        PromptRenderingCore prompt = new PromptRenderingCore("user", "~");


            while (true) {
                // Reemplaza cualquier System.out.print por esto
                prompt.render();

                // Leer comando
                String comando = scanner.nextLine();

                // para probar
                System.out.println("Escribiste: " + comando);
            }
        }
    }
