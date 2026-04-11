package service;

import static util.Utils.emitirSonido;

public class TimeReto implements Runnable {

    private Integer minutos;
    private Integer segundos;
    private volatile boolean corriendo;
    private Thread  thread;

    public TimeReto(int minutos) {
        this.minutos   = minutos;
        this.segundos  = 0;
        this.corriendo = false;
        this.thread    = null;
    }
    public void iniciar() {
        if (!corriendo) {
            thread = new Thread(this);
            corriendo = true;
            thread.start();
        }
    }

    public void detener() {
        corriendo = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.err.println("Error al detener timer: " + e.getMessage());
        }
    }

    /**
     * Obtiene el tiempo restante formateado
     * @return String en formato "MM:SS"
     */
    public String getTiempoRestante() {
        return String.format("%02d:%02d", minutos, segundos);
    }

    /**
     * Verifica si el timer termino
     * @return true si llegó a 00:00
     */
    public boolean haTerminado() {
        return minutos == 0 && segundos == 0;
    }

    @Override
    public void run() {
        while(corriendo) {
            try {
                Thread.sleep(1000);

                if (segundos > 0) {
                    segundos--;
                } else {
                    if (minutos > 0) {
                        minutos--;
                        segundos = 59;
                    } else {
                        corriendo = false;
                        emitirSonido();
                        break;
                    }
                }
            } catch (InterruptedException e) {
                System.err.println("Timer interrupimpido: " + e.getMessage());
                corriendo = false;
            }
        }
    }
}