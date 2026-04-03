 package ui;

import service.TimeReto;

import javax.swing.*;
import java.awt.*;

    public class TimerUI {

        private JFrame frame;
        private JLabel labelTiempo;
        private TimeReto timer;

        public TimerUI() {
            timer = new TimeReto(1); // 1 minuto inicial

            frame = new JFrame("Timer Reto");
            frame.setSize(300, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            labelTiempo = new JLabel(timer.getTiempoRestante(), SwingConstants.CENTER);
            labelTiempo.setFont(new Font("Arial", Font.BOLD, 40));

            JPanel panelBotones = new JPanel();

            JButton btnIniciar = new JButton("Iniciar");
            JButton btnPausar = new JButton("Pausar");
            JButton btnReanudar = new JButton("Reanudar");
            JButton btnReiniciar = new JButton("Reiniciar");

            panelBotones.add(btnIniciar);
            panelBotones.add(btnPausar);
            panelBotones.add(btnReanudar);
            panelBotones.add(btnReiniciar);

            frame.add(labelTiempo, BorderLayout.CENTER);
            frame.add(panelBotones, BorderLayout.SOUTH);

            // acciones
            btnIniciar.addActionListener(e -> timer.iniciar());
            btnPausar.addActionListener(e -> timer.pausar());
            btnReanudar.addActionListener(e -> timer.reanudar());
            btnReiniciar.addActionListener(e -> timer.reiniciar(1));

            // hilo para actualizar UI
            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(200);
                        SwingUtilities.invokeLater(() ->
                                labelTiempo.setText(timer.getTiempoRestante())
                        );
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }).start();

            frame.setVisible(true);
        }

        public static void main(String[] args) {
            new TimerUI();
        }
    }