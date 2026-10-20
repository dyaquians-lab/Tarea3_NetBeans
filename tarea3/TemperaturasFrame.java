package tarea3;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TemperaturasFrame extends JFrame {
    private final double[] temperaturas = new double[6];
    private int indice = 0;
    private final JTextArea area = new JTextArea(12, 34);
    private final File archivo = new File("temp/temperaturas.txt");

    public TemperaturasFrame() {
        setTitle("Lector y Analizador de Temperaturas");
        setSize(470, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));
        JPanel arriba = new JPanel();
        JButton cargar = new JButton("Cargar Datos");
        JButton calcular = new JButton("Calcular Estadísticas");
        arriba.add(cargar);
        arriba.add(calcular);
        add(arriba, BorderLayout.NORTH);
        area.setEditable(false);
        add(new JScrollPane(area), BorderLayout.CENTER);
        cargar.addActionListener(e -> cargarDatos());
        calcular.addActionListener(e -> calcularEstadisticas());
    }

    private void cargarDatos() {
        indice = 0;
        try (Scanner sc = new Scanner(archivo)) {
            while (sc.hasNextLine() && indice < temperaturas.length) {
                String linea = sc.nextLine().trim();
                if (!linea.isEmpty()) temperaturas[indice++] = Double.parseDouble(linea);
            }
            if (indice != temperaturas.length) {
                JOptionPane.showMessageDialog(this, "El archivo debe contener exactamente 6 temperaturas.");
                return;
            }
            JOptionPane.showMessageDialog(this, "Los datos fueron cargados en memoria.");
            area.setText("Se cargaron 6 temperaturas desde temp/temperaturas.txt.\n");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "No se encontró temp/temperaturas.txt.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El archivo contiene un valor no numérico.");
        }
    }

    private void calcularEstadisticas() {
        if (indice != temperaturas.length) {
            JOptionPane.showMessageDialog(this, "Primero presione Cargar Datos.");
            return;
        }
        double suma = 0;
        int calidos = 0, frescos = 0;
        for (double temperatura : temperaturas) {
            suma += temperatura;
            if (temperatura >= 25.0) calidos++;
            else frescos++;
        }
        double promedio = suma / temperaturas.length;
        area.setText("Análisis Climático\n------------------\n\nPromedio de Temperatura: "
                + String.format("%.2f", promedio) + "\n\nDías Cálidos (>= 25°): " + calidos
                + "\n\nDías Frescos (< 25°): " + frescos);
    }
}
