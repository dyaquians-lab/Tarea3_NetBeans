package tarea3;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DiscosFrame extends JFrame {
    private final String[] titulos = new String[5];
    private final int[] anios = new int[5];
    private int cantidad = 0;
    private final JTextField txtBuscar = new JTextField(16);
    private final JLabel lblAnio = new JLabel("Año actual: —");
    private final JTextField txtNuevoAnio = new JTextField(7);
    private final File archivo = new File("temp/discos.csv");

    public DiscosFrame() {
        setTitle("Biblioteca de Discos Musicales");
        setSize(500, 230);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));
        JPanel panel = new JPanel(new GridLayout(3, 1, 5, 5));
        JPanel p1 = new JPanel();
        p1.add(new JLabel("Buscar Álbum:"));
        p1.add(txtBuscar);
        JButton buscar = new JButton("Buscar");
        p1.add(buscar);
        JPanel p2 = new JPanel();
        p2.add(lblAnio);
        JPanel p3 = new JPanel();
        p3.add(new JLabel("Nuevo Año:"));
        p3.add(txtNuevoAnio);
        JButton actualizar = new JButton("Actualizar Registro");
        p3.add(actualizar);
        panel.add(p1); panel.add(p2); panel.add(p3);
        add(panel, BorderLayout.CENTER);
        buscar.addActionListener(e -> buscarAlbum());
        actualizar.addActionListener(e -> actualizarAlbum());
        cargarAutomaticamente();
    }

    private void cargarAutomaticamente() {
        cantidad = 0;
        try (Scanner sc = new Scanner(archivo)) {
            while (sc.hasNextLine() && cantidad < titulos.length) {
                String linea = sc.nextLine().trim();
                if (linea.isEmpty()) continue;
                String[] partes = linea.split(",");
                if (partes.length >= 2) {
                    titulos[cantidad] = partes[0].trim();
                    anios[cantidad] = Integer.parseInt(partes[1].trim());
                    cantidad++;
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudieron cargar discos.csv: " + ex.getMessage());
        }
    }

    private int encontrarAlbum() {
        String buscado = txtBuscar.getText().trim();
        for (int i = 0; i < cantidad; i++) {
            if (titulos[i].equalsIgnoreCase(buscado)) return i;
        }
        return -1;
    }

    private void buscarAlbum() {
        int i = encontrarAlbum();
        if (i < 0) {
            lblAnio.setText("Álbum no encontrado");
        } else {
            lblAnio.setText("Año actual: " + anios[i]);
        }
    }

    private void actualizarAlbum() {
        int i = encontrarAlbum();
        if (i < 0) {
            JOptionPane.showMessageDialog(this, "Busque un álbum existente primero.");
            return;
        }
        try {
            int nuevo = Integer.parseInt(txtNuevoAnio.getText().trim());
            if (nuevo < 1) throw new NumberFormatException();
            anios[i] = nuevo;
            try (PrintWriter out = new PrintWriter(new FileWriter(archivo, false))) {
                for (int j = 0; j < cantidad; j++) out.println(titulos[j] + "," + anios[j]);
            }
            lblAnio.setText("Año actual: " + nuevo);
            JOptionPane.showMessageDialog(this, "Registro actualizado en discos.csv.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un año válido.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
        }
    }
}
