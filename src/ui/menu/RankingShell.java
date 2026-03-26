package ui.menu;

import service.GestorParticipantes;
import service.GestorRetos;
import ui.cli.CommandHandler;
import ui.cli.InputReader;
import ui.view.ConsoleView;
import ui.view.Formatter;

/**
 * RankingShell - Interfaz de línea de comandos tipo shell
 *
 * Responsabilidades:
 * - Coordinar componentes de la UI (InputReader, CommandHandler, ConsoleView)
 * - Mantener el bucle principal de la aplicación
 * - Gestionar el estado de la sesión
 * - Proporcionar punto de entrada para la interfaz de usuario
 *
 * Es el "pegamento" que une entrada, procesamiento y salida.
 */
public class RankingShell {
    private final InputReader inputReader;
    private final CommandHandler commandHandler;
    private final ConsoleView consoleView;
    private final Formatter formatter;

    public RankingShell(GestorRetos gestorRetos, GestorParticipantes gestorParticipantes) {
        this.inputReader = new InputReader();
        this.consoleView = new ConsoleView();
        this.formatter = new Formatter();
        this.commandHandler = new CommandHandler(gestorRetos, gestorParticipantes, consoleView, inputReader, formatter);
    }

    /**
     * Inicia el shell interactivo
     */
    public void start() {
        // TODO: Implementar bucle principal
        // Mostrar bienvenida
        // Bucle: leer comando -> procesar -> mostrar resultado
        // Hasta que commandHandler indique salir

        consoleView.showWelcome();
        // Esperar a que el usuario presione Enter para continuar (sin mostrar prompt)
        inputReader.readCommand();

        while (commandHandler.isRunning()) {
            try {
                consoleView.showPrompt();
                String command = inputReader.readCommand();

                if (!commandHandler.processCommand(command)) {
                    break;
                }
            } catch (Exception e) {
                consoleView.showError("Error procesando comando: " + e.getMessage());
            }
        }

        cleanup();
    }

    /**
     * Limpia recursos al salir
     */
    private void cleanup() {
        inputReader.close();
        consoleView.showInfo("Sesión terminada");
    }

    /**
     * Ejecuta un comando único (útil para testing)
     * 
     * @param command Comando a ejecutar
     * @return true si se ejecutó correctamente
     */
    public boolean executeCommand(String command) {
        // TODO: Implementar ejecución de comando único
        // Útil para testing o integración
        return commandHandler.processCommand(command);
    }
}