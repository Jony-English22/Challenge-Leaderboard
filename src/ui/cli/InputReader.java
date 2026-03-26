package ui.cli;

import java.util.Scanner;

/**
 * InputReader - Maneja la entrada de datos del usuario
 *
 * Responsabilidades:
 * - Leer comandos desde consola
 * - Parsear entrada básica (trim, lowercase)
 * - Proporcionar métodos seguros para leer diferentes tipos de datos
 * - Manejar interrupciones y errores de entrada
 *
 * No procesa comandos, solo lee y valida entrada básica.
 */
public class InputReader {
    private final Scanner scanner;

    public InputReader() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Lee el siguiente comando completo
     * 
     * @return Comando completo como string
     */
    public String readCommand() {
        // TODO: Implementar lectura de comando
        // Leer línea completa, hacer trim y lowercase si es necesario
        // Manejar EOF o interrupciones
        System.out.print("> ");
        if (!scanner.hasNextLine()) {
            return "salir";
        }
        return scanner.nextLine().trim();
    }

    /**
     * Lee una línea de texto
     * 
     * @param prompt Mensaje a mostrar antes de leer
     * @return Texto ingresado
     */
    public String readLine(String prompt) {
        // TODO: Implementar lectura con prompt
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * Lee un número entero
     * 
     * @param prompt Mensaje a mostrar
     * @return Número entero o null si inválido
     */
    public Integer readInt(String prompt) {
        // TODO: Implementar lectura de entero
        // Intentar parsear, retornar null si falla
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Confirma una acción (sí/no)
     * 
     * @param prompt Pregunta de confirmación
     * @return true si usuario confirma, false otherwise
     */
    public boolean confirm(String prompt) {
        // TODO: Implementar confirmación
        // Leer respuesta y verificar si es "s", "si", "y", "yes"
        String response = readLine(prompt + " (s/n): ").toLowerCase();
        if (response.isEmpty())
            return false;
        return response.equals("s") || response.equals("si") ||
                response.equals("y") || response.equals("yes");
    }

    /**
     * Cierra el scanner
     */
    public void close() {
        scanner.close();
    }

    /**
     * Verifica si hay más entrada disponible
     * 
     * @return true si hay más datos para leer
     */
    public boolean hasNext() {
        return scanner.hasNext();
    }
}