import service.GestorDatos;
import service.GestorParticipantes;
import service.GestorRetos;
import ui.menu.RankingShell;

public class Main {
    public static void main(String[] args) {
        // Inicializar gestores

        GestorDatos gestorDatos = new GestorDatos();
        GestorRetos gestorRetos = new GestorRetos(gestorDatos);
        GestorParticipantes gestorParticipantes = new GestorParticipantes(gestorDatos);

        // Conectar gestores
        gestorParticipantes.setGestorRetos(gestorRetos);

        // Lanzar interfaz CLI
        RankingShell shell = new RankingShell(gestorRetos, gestorParticipantes);
        shell.start();

    }
}