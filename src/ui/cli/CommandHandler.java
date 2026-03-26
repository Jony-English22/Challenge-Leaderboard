package ui.cli;

import java.util.List;
import java.util.Map;

import model.Participante;
import model.Reto;
import service.GestorParticipantes;
import service.GestorRetos;
import ui.view.ConsoleView;
import ui.view.Formatter;

/**
 * CommandHandler - Interpreta y ejecuta comandos del usuario
 *
 * Responsabilidades:
 * - Parsear comandos y argumentos
 * - Ejecutar acciones correspondientes usando los servicios
 * - Coordinar con ConsoleView para mostrar resultados
 * - Manejar errores de comandos y validaciones
 *
 * No maneja entrada/salida directa, delega a InputReader y ConsoleView.
 */
public class CommandHandler {
    private final GestorRetos gestorRetos;
    private final GestorParticipantes gestorParticipantes;
    private final ConsoleView consoleView;
    private final InputReader inputReader;
    private boolean running;

    public CommandHandler(GestorRetos gestorRetos, GestorParticipantes gestorParticipantes, ConsoleView consoleView,
            InputReader inputReader) {
        this.gestorRetos = gestorRetos;
        this.gestorParticipantes = gestorParticipantes;
        this.consoleView = consoleView;
        this.inputReader = inputReader;
        this.running = true;
    }

    /**
     * Procesa un comando completo
     * 
     * @param command Comando a procesar
     * @return true si debe continuar, false para salir
     */
    public boolean processCommand(String command) {
        // TODO: Implementar procesamiento de comandos
        // Parsear comando y argumentos
        // Ejecutar acción correspondiente
        // Mostrar resultados vía consoleView

        if (command.isEmpty()) {
            return true;
        }

        String[] parts = command.split("\\s+", 2);
        String cmd = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1] : "";

        switch (cmd) {
            case "crear":
                return handleCrear(args);
            case "listar":
                return handleListar(args);
            case "eliminar":
                return handleEliminar(args);
            case "ver":
                return handleVer(args);
            case "participante":
                return handleParticipante(args);
            case "ranking":
                return handleRanking();
            case "ayuda":
                return handleAyuda();
            case "salir":
                return handleSalir();
            default:
                consoleView.showError("Comando desconocido: " + cmd);
                return true;
        }
    }

    /**
     * Maneja comando 'crear'
     * Sintaxis: crear "nombre" [descripcion] [puntos] [tiempo]
     */
    private boolean handleCrear(String args) {
        String nombre;
        String descripcion;
        Integer puntos;
        Integer duracion;

        if (args.isEmpty()) {
            // MODO INTERACTIVO
            consoleView.showInfo("=== Crear nuevo reto ===");

            nombre = inputReader.readLine("Nombre: ");
            descripcion = inputReader.readLine("Descripción: ");
            puntos = Integer.valueOf(inputReader.readInt("Puntos: "));
            duracion = Integer.valueOf(inputReader.readInt("Duración:"));
        } else {
            // MODO RAPIDO
            String[] partes = args.split("\\s+");

            if (partes.length < 3) {
                consoleView.showError("Uso: crear <nombre> <puntos> <tiempo>");
                return true;
            }
            nombre = partes[0];
            descripcion = "Sin descripción";

            try {
                puntos = Integer.parseInt(partes[1]);
                duracion = Integer.parseInt(partes[2]);
            } catch (NumberFormatException e) {
                consoleView.showError("Duración inválida");
                return true;
            }
        }
        if (nombre == null || nombre.isEmpty()) {
            consoleView.showError("El nombre es obligatorio");
            return true;
        }

        if (puntos == null) {
            consoleView.showError("Puntos inválidos");
            return true;
        }

        if (duracion == null) {
            consoleView.showError("Duración inválida");
            return true;
        }

        gestorRetos.crearReto(nombre, descripcion, puntos, duracion);

        consoleView.showInfo("Reto creado: " + nombre);
        return true;
    }

    /**
     * Maneja comando 'listar'
     * Sintaxis: listar [retos|participantes]
     */
    private boolean handleListar(String args) {
        // TODO: Implementar listado
        // Si args vacío, listar retos; si "participantes", listar participantes
        if (args == null || args.trim().isEmpty()) {
            listarRetos();
        } else if (args.trim().equalsIgnoreCase("participantes")) {
            listarParticipantes();
        } else {
            consoleView.showError("Argumentos no reconocido. Uso: listar || listar [participantes]");
        }
        return true;
    }

    /**
     * Maneja comando 'eliminar'
     * Sintaxis: eliminar <id>
     */
    private boolean handleEliminar(String args) {
        // TODO: Implementar eliminación
        // Parsear ID, confirmar eliminación, ejecutar
        if (args == null || args.trim().isEmpty()) {
            consoleView.showError("Argumento no reconocido. Uso: eliminar [id]");
            return true;
        }
        String[] partes = args.trim().split("\\s+");

        if (partes.length != 2) {
            consoleView.showError("Uso: eliminar <reto|parcipantente> [id]");
            return true;
        }

        String tipo = partes[0];
        String idStr = partes[1];

        try {
            int id = Integer.parseInt(idStr);

            if (tipo.equalsIgnoreCase("reto")) {
                eliminarReto(id);
            } else if (tipo.equalsIgnoreCase("participante")) {
                eliminarParticipante(id);
            } else {
                consoleView.showError("Tipo no válido. Usa: reto | participante");
            }
        } catch (NumberFormatException e) {
            consoleView.showError("El ID debe ser un número");
        }
        return true;
    }

    /**
     * Maneja comando 'ver'
     * Sintaxis: ver <id> || ver participante <id>
     */
    private boolean handleVer(String args) {
        if (args == null || args.trim().isEmpty()) {
            consoleView.showError("Argumento no reconocido. Uso: ver [id] || ver participante [id]");
            return true;
        }

        String[] partes = args.split("\\s+");
        if (partes.length == 1) {
            // Ver reto por ID (int)
            try {
                int id = Integer.parseInt(partes[0]);
                mostrarDetallesReto(id);
            } catch (NumberFormatException e) {
                consoleView.showError("El ID del reto debe ser un número");
            }
        } else if (partes.length == 2 && "participante".equalsIgnoreCase(partes[0])) {
            // Ver participante por ID (String)
            String id = partes[1];
            mostrarDetallesParticipante(id);
        } else {
            consoleView.showError("Uso: ver [id] || ver participante [id]");
        }
        return true;
    }

    /**
     * Maneja comando 'participante'
     * Sintaxis: participante <subcomando> [args]
     */
    private boolean handleParticipante(String args) {
        // TODO: Implementar gestión de participantes
        // Subcomandos: agregar, eliminar, listar
        if (args == null || args.trim().isEmpty()) {
            consoleView.showError("Argumento no reconocido. Uso: participante <agregar||eliminar||listar>");
            return true;
        }

        String[] partes = args.split("\\s+");

        switch (partes[0].toLowerCase()) {
            case "agregar":
                if (partes.length < 2) {
                    consoleView.showError("Uso: participante agregar <nombre>");
                } else {
                    String nombre = partes[1];
                    boolean creado = gestorParticipantes.registrarParticipante(nombre);
                    if (creado) {
                        consoleView.showInfo("Participante creado: " + nombre);
                    } else {
                        consoleView.showError("No se pudo crear participante. Nombre inválido o duplicado.");
                    }
                }
                break;

            case "eliminar":
                if (partes.length < 2) {
                    consoleView.showError("Uso: participante eliminar <indice>");
                } else {
                    try {
                        int indice = Integer.parseInt(partes[1]);
                        eliminarParticipante(indice);
                    } catch (NumberFormatException e) {
                        consoleView.showError("El índice debe ser un número entero");
                    }
                }
                break;

            case "listar":
                listarParticipantes();
                break;

            default:
                consoleView.showError("Argumento no reconocido. Uso: participante <agregar||eliminar||listar>");
                break;
        }
        return true;
    }

    /**
     * Maneja comando 'ranking'
     * Sintaxis: ranking
     */
    private boolean handleRanking() {
        // TODO: Implementar mostrar ranking
        // Obtener ranking y mostrarlo
        List<Map.Entry<Participante, Integer>> ranking = gestorParticipantes.obtenerRankingOrdenado();
        consoleView.showInfo(Formatter.formatRanking(ranking));
        return true;
    }

    /**
     * Maneja comando 'ayuda'
     */
    private boolean handleAyuda() {
        // TODO: Implementar ayuda
        // Mostrar lista de comandos disponibles
        consoleView.showHeader("Comandos del Sistema....");
        return true;
    }

    /**
     * Maneja comando 'salir'
     */
    private boolean handleSalir() {
        String confirm = inputReader.readLine("¿Estás seguro de que quieres salir? (s/n): ");
        if (confirm.equalsIgnoreCase("s")) {
            running = false;
            consoleView.showInfo("¡Hasta luego!");
            return false;
        } else {
            consoleView.showInfo("Operación cancelada.");
            return true;
        }
    }

    /**
     * Verifica si la aplicación debe continuar ejecutándose
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Listar Retos
     */
    private void listarRetos() {
        List<Reto> retos = gestorRetos.listarRetos();

        if (retos.isEmpty()) {
            consoleView.showInfo("No hay retos registrados");
            return;
        }

        consoleView.showInfo(Formatter.formatRetosList(retos));
    }

    /**
     * Listar Participates
     */
    private void listarParticipantes() {
        List<Participante> participantes = gestorParticipantes.listarParticipantes();

        if (participantes.isEmpty()) {
            consoleView.showInfo("No hay participantes registrados");
            return;
        }

        consoleView.showInfo(Formatter.formatParticipantesList(participantes));
    }

    /**
     * Eliminar Reto
     */
    private void eliminarReto(int id) {
        Reto reto = gestorRetos.obtenerReto(id);

        if (reto == null) {
            consoleView.showError("No existe ningún reto con ID: " + id);
            return;
        }

        consoleView.showInfo("Reto encontrado: " + reto.getNombre());
        String confirmacion = inputReader.readLine("¿Deseas eliminar este reto? (s/n): ");

        if (confirmacion.equalsIgnoreCase("s")) {
            boolean eliminado = gestorRetos.eliminarReto(id);
            if (eliminado) {
                consoleView.showInfo("Reto eliminado correctamente");
            } else {
                consoleView.showError("Error al eliminar el reto");
            }
        } else {
            consoleView.showInfo("Operación cancelada");
        }
    }

    /**
     * Eliminar Participantes
     */
    private void eliminarParticipante(int index) {
        List<Participante> participantes = gestorParticipantes.listarParticipantes();

        if (index < 0 || index >= participantes.size()) {
            consoleView.showError("Índice de participante inválido");
            return;
        }

        Participante participante = participantes.get(index);
        consoleView.showInfo("Participante encontrado: " + participante.getNombre());
        String confirmacion = inputReader.readLine("¿Deseas eliminar este participante? (s/n): ");

        if (confirmacion.equalsIgnoreCase("s")) {
            boolean eliminado = gestorParticipantes.eliminarParticipante(participante.getNombre());
            if (eliminado) {
                consoleView.showInfo("Participante eliminado correctamente");
            } else {
                consoleView.showError("Error al eliminar el participante");
            }
        } else {
            consoleView.showInfo("Operación cancelada");
        }
    }

    /**
     * Mostrar detalles de un reto
     */
    private void mostrarDetallesReto(int id) {
        Reto reto = gestorRetos.obtenerReto(id);
        if (reto == null) {
            consoleView.showError("No existe reto con ID: " + id);
        } else {
            consoleView.showInfo(Formatter.formatRetoDetails(reto));
        }
    }

    /**
     * Mostrar detalles de un participante
     */
    private void mostrarDetallesParticipante(String id) {
        Participante participante = gestorParticipantes.obtenerParticipantePorId(id);
        if (participante == null) {
            consoleView.showError("No existe participante con ID: " + id);
        } else {
            consoleView.showInfo(Formatter.formatParticipanteDetails(participante));
        }
    }
}