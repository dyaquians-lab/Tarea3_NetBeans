package tarea3;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AsistenciaFrame extends JFrame {
    private final JTextField txtNombre = new JTextField(18);
    private final JTextArea area = new JTextArea(12, 30);
    private final String[] asistentes = new String[5];
    private int indice = 0;

    public AsistenciaFrame() {
        setTitle("Registro de Asistencia a Conferencia");
        setSize(470, 360);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));
        JPanel arriba = new JPanel();
        arriba.add(new JLabel("Nombre del asistente:"));
        arriba.add(txtNombre);
        JButton registrar = new JButton("Registrar Asistente");
        JButton exportar = new JButton("Exportar Lista");
        arriba.add(registrar);
        arriba.add(exportar);
        add(arriba, BorderLayout.NORTH);
        area.setEditable(false);
        add(new JScrollPane(area), BorderLayout.CENTER);
        registrar.addActionListener(e -> registrar());
        exportar.addActionListener(e -> exportar());
    }

    private void registrar() {
        if (indice >= asistentes.length) {
            JOptionPane.showMessageDialog(this, "Ya se registraron los 5 asistentes.");
            return;
        }
        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Escriba el nombre del asistente.");
            return;
        }
        asistentes[indice] = nombre;
        indice++;
        area.append(indice + ". " + nombre + "\n");
        txtNombre.setText("");
        txtNombre.requestFocus();
    }

    private void exportar() {
        if (indice == 0) {
            JOptionPane.showMessageDialog(this, "No hay asistentes registrados.");
            return;
        }
        StringBuilder contenido = new StringBuilder("Lista de Asistencia\n");
        for (int i = 0; i < indice; i++) contenido.append(i + 1).append(". ").append(asistentes[i]).append("\n");
        try {
            File carpeta = new File("temp");
            if (!carpeta.exists()) carpeta.mkdirs();
            try (PrintWriter out = new PrintWriter(new FileWriter(new File(carpeta, "daniel_asistencia.txt")))) {
                out.print(contenido);
            }
            JOptionPane.showMessageDialog(this, "Lista guardada en temp/daniel_asistencia.txt");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo exportar: " + ex.getMessage());
        }
    }
}
