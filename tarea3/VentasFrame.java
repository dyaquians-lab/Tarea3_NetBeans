package tarea3;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class VentasFrame extends JFrame {
    private final JTextField txtVenta = new JTextField(12);
    private final JTextArea area = new JTextArea(8, 30);
    private final double[] ventas = new double[5];
    private int indice = 0;

    public VentasFrame() {
        setTitle("Sistema de Control de Ventas Semanales");
        setSize(440, 330);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));
        JPanel arriba = new JPanel();
        arriba.add(new JLabel("Monto de venta (Q):"));
        arriba.add(txtVenta);
        JButton agregar = new JButton("Agregar Venta");
        JButton reporte = new JButton("Generar Reporte");
        arriba.add(agregar);
        arriba.add(reporte);
        add(arriba, BorderLayout.NORTH);
        area.setEditable(false);
        add(new JScrollPane(area), BorderLayout.CENTER);
        agregar.addActionListener(e -> agregarVenta());
        reporte.addActionListener(e -> generarReporte());
    }

    private void agregarVenta() {
        if (indice >= ventas.length) {
            JOptionPane.showMessageDialog(this, "Ya se ingresaron las 5 ventas.");
            return;
        }
        try {
            double monto = Double.parseDouble(txtVenta.getText().trim());
            if (monto < 0) throw new NumberFormatException();
            ventas[indice] = monto;
            indice++;
            area.append("Venta " + indice + ": Q" + String.format("%.2f", monto) + "\n");
            txtVenta.setText("");
            txtVenta.requestFocus();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un monto numérico válido y no negativo.");
        }
    }

    private void generarReporte() {
        if (indice < ventas.length) {
            JOptionPane.showMessageDialog(this, "Ingrese las 5 ventas antes de generar el reporte.");
            return;
        }
        double total = 0;
        int superiores = 0;
        for (double venta : ventas) {
            total += venta;
            if (venta > 500.0) superiores++;
        }
        String contenido = "Reporte de Ventas\nTotal Vendido: " + String.format("%.2f", total)
                + "\nVentas superiores a Q500: " + superiores + "\n";
        area.append("\n" + contenido);
        try {
            File carpeta = new File("temp");
            if (!carpeta.exists()) carpeta.mkdirs();
            try (PrintWriter out = new PrintWriter(new FileWriter(new File(carpeta, "daniel_reporte_ventas.txt")))) {
                out.print(contenido);
            }
            JOptionPane.showMessageDialog(this, "Reporte guardado en temp/daniel_reporte_ventas.txt");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo guardar el reporte: " + ex.getMessage());
        }
    }
}
