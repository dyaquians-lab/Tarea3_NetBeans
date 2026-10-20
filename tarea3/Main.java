package tarea3;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public Main() {
        setTitle("Tarea 3 - Sistemas en Java");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(460, 430);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 1, 8, 8));
        JLabel titulo = new JLabel("Tarea 3 - Aplicaciones Java", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo);
        agregarBoton("1. Control de ventas semanales", () -> new VentasFrame().setVisible(true));
        agregarBoton("2. Registro de asistencia", () -> new AsistenciaFrame().setVisible(true));
        agregarBoton("3. Análisis de temperaturas", () -> new TemperaturasFrame().setVisible(true));
        agregarBoton("4. Biblioteca de discos musicales", () -> new DiscosFrame().setVisible(true));
        agregarBoton("5. Clases Artista y Canción", () -> new StreamingFrame().setVisible(true));
        agregarBoton("6. Catálogo de videojuegos", () -> new VideojuegosFrame().setVisible(true));
        setVisible(true);
    }

    private void agregarBoton(String texto, Runnable accion) {
        JButton boton = new JButton(texto);
        boton.addActionListener(e -> accion.run());
        add(boton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
