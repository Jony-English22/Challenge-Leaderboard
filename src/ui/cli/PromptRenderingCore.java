package ui.cli;

    /**
     * Núcleo de renderización del prompt de la CLI.
     * Se encarga de generar e imprimir el prompt de forma centralizada.
     */
    public class PromptRenderingCore {

        private String usuario;
        private String contexto;

        private static final String FORMATO = "%s@challenge:%s$ ";

        public PromptRenderingCore(String usuario, String contexto) {
            if (usuario == null || usuario.isBlank()) {
                throw new IllegalArgumentException("El usuario no puede estar vacío");
            }
            if (contexto == null || contexto.isBlank()) {
                throw new IllegalArgumentException("El contexto no puede estar vacío");
            }

            this.usuario  =  usuario.trim();
            this.contexto = contexto.trim();
        }

        /**
         * Genera el prompt en formato estándar.
         */
        public String generarPrompt() {
            return String.format(FORMATO, usuario, contexto);
        }

        /**
         * Imprime el prompt en consola.
         */
        public void render() {
            System.out.print(generarPrompt());
        }

        public void setUsuario(String usuario) {
            if (usuario != null && !usuario.isBlank()) {
                this.usuario = usuario.trim();
            }
        }

        public void setContexto(String contexto) {
            if (contexto != null && !contexto.isBlank()) {
                this.contexto = contexto.trim();
            }
        }
    }

