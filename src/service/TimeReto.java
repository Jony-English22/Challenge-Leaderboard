package service;

import static util.Utils.emitirSonido;

public class TimeReto implements Runnable {

    private Integer minutos;
    private Integer segundos;
    private volatile boolean corriendo;
    private Thread thread;

    public TimeReto(int minutos) {
        this.minutos = minutos;
        this.segundos = 0;
        this.corriendo = false;
        this.thread = false;
    }
    public void iniciar() {
        if (!corriendo) return;
        thread = new Thread(this);
        corriendo = true;
        thread.start();
    }

    public void detener() {
        corriendo = false;
        try {
            thread.join();
        } catch
    }

    public void reanudar() {
        pausado = false;
    }

    public synchronized void detener() {
        corriendo = false;
        if (thread != null) {
            thread.interrupt();
        }
    }

    public synchronized void reiniciar(int minutos) {
        detener();
        this.minutos = minutos;
        this.segundos = 0;
        this.pausado = false;
    }

    public String getTiempoRestante() {
        return String.format("%02d:%02d", minutos, segundos);
    }

    public boolean haTerminado() {
        return minutos == 0 && segundos == 0;
    }

    @Override
    public void run() {
        while (corriendo && !haTerminado()) {
            try {

                Thread.sleep(1000);

                // pausa
                while (pausado && corriendo) {
                    Thread.sleep(200);
                }

                decrementarTiempo();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        if (haTerminado()) {
            emitirSonido();
        }

        corriendo = false;
    }

    private void decrementarTiempo() {
        if (segundos > 0) {
            segundos--;
        } else if (minutos > 0) {
            minutos--;
            segundos = 59;
        }
    }
}