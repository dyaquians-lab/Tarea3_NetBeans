package tarea3;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VideojuegosFrame extends JFrame {
    private final ArrayList<Videojuego> catalogo = new ArrayList<>();
    private final JTextField txtTitulo = new JTextField(12);
    private final JTextField txtConsola = new JTextField(12);
    private final JTextField txtAnio = new JTextField(7);
    private final JTextArea area = new JTextArea(14, 38);

    public VideojuegosFrame() {
        setTitle("Catálogo de Videojuegos");
        setSize(540, 430);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));
        JPanel arriba = new JPanel(new GridLayout(2, 4, 5, 5));
        arriba.add(new JLabel("Título:")); arriba.add(txtTitulo);
        arriba.add(new JLabel("Consola:")); arriba.add(txtConsola);
        arriba.add(new JLabel("Año:")); arriba.add(txtAnio);
        JButton agregar = new JButton("Agregar Juego");
        JButton mostrar = new JButton("Mostrar Catálogo");
        JButton eliminar = new JButton("Eliminar Último");
        arriba.add(agregar); arriba.add(mostrar);
        JPanel acciones = new JPanel();
        acciones.add(eliminar);
        JPanel norte = new JPanel(new BorderLayout());
        norte.add(arriba, BorderLayout.CENTER);
        norte.add(acciones, BorderLayout.SOUTH);
        add(norte, BorderLayout.NORTH);
        area.setEditable(false);
        add(new JScrollPane(area), BorderLayout.CENTER);
        agregar.addActionListener(e -> agregarJuego());
        mostrar.addActionListener(e -> mostrarCatalogo());
        eliminar.addActionListener(e -> eliminarUltimo());
    }

    private void agregarJuego() {
        String titulo = txtTitulo.getText().trim();
        String consola = txtConsola.getText().trim();
        try {
            int anio = Integer.parseInt(txtAnio.getText().trim());
            if (titulo.isEmpty() || consola.isEmpty() || anio < 1950 || anio > 2100)
                throw new IllegalArgumentException();
            Videojuego juego = new Videojuego(titulo, consola, anio);
            catalogo.add(juego);
            txtTitulo.setText(""); txtConsola.setText(""); txtAnio.setText("");
            JOptionPane.showMessageDialog(this, "Juego agregado al catálogo.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ingrese título, consola y un año válido.");
        }
    }

    private void mostrarCatalogo() {
        area.setText("");
        for (int i = 0; i < catalogo.size(); i++) {
            Videojuego juego = catalogo.get(i);
            area.append((i + 1) + ". " + juego.getTitulo() + " | Consola: " + juego.getConsola()
                    + " | Año: " + juego.getAnioLanzamiento() + "\n");
        }
        area.append("\nTotal de juegos registrados: " + catalogo.size());
    }

    private void eliminarUltimo() {
        if (catalogo.size() > 0) {
            catalogo.remove(catalogo.size() - 1);
            JOptionPane.showMessageDialog(this, "Se eliminó el último juego registrado.");
        } else {
            JOptionPane.showMessageDialog(this, "El catálogo está vacío.");
        }
    }
}
