package tarea3;

import javax.swing.*;
import java.awt.*;

public class StreamingFrame extends JFrame {
    private final JTextField txtNombre = new JTextField(12);
    private final JTextField txtPais = new JTextField(12);
    private final JTextField txtOyentes = new JTextField(8);
    private final JTextField txtTitulo = new JTextField(12);
    private final JTextField txtDuracion = new JTextField(8);
    private final JTextField txtGenero = new JTextField(10);
    private final JTextField txtArtistaCancion = new JTextField(12);
    private final JTextArea area = new JTextArea(14, 40);

    public StreamingFrame() {
        setTitle("Fundamentos de Clases y Objetos - Streaming");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));
        JPanel principal = new JPanel(new GridLayout(1, 2, 10, 10));
        JPanel pArtista = new JPanel(new GridLayout(4, 2, 5, 5));
        pArtista.setBorder(BorderFactory.createTitledBorder("Datos del artista"));
        pArtista.add(new JLabel("Nombre:")); pArtista.add(txtNombre);
        pArtista.add(new JLabel("País de origen:")); pArtista.add(txtPais);
        pArtista.add(new JLabel("Oyentes mensuales:")); pArtista.add(txtOyentes);
        JButton crearArtista = new JButton("Crear Artista");
        pArtista.add(new JLabel()); pArtista.add(crearArtista);
        JPanel pCancion = new JPanel(new GridLayout(5, 2, 5, 5));
        pCancion.setBorder(BorderFactory.createTitledBorder("Datos de la canción"));
        pCancion.add(new JLabel("Título:")); pCancion.add(txtTitulo);
        pCancion.add(new JLabel("Duración (segundos):")); pCancion.add(txtDuracion);
        pCancion.add(new JLabel("Género:")); pCancion.add(txtGenero);
        pCancion.add(new JLabel("Nombre del artista:")); pCancion.add(txtArtistaCancion);
        JButton crearCancion = new JButton("Crear Canción");
        pCancion.add(new JLabel()); pCancion.add(crearCancion);
        principal.add(pArtista); principal.add(pCancion);
        add(principal, BorderLayout.NORTH);
        area.setEditable(false);
        add(new JScrollPane(area), BorderLayout.CENTER);
        crearArtista.addActionListener(e -> crearArtista());
        crearCancion.addActionListener(e -> crearCancion());
    }

    private void crearArtista() {
        String nombre = txtNombre.getText().trim();
        String paisOrigen = txtPais.getText().trim();
        try {
            int oyentesMensuales = Integer.parseInt(txtOyentes.getText().trim());
            if (nombre.isEmpty() || paisOrigen.isEmpty() || oyentesMensuales < 0) throw new IllegalArgumentException();
            Artista artista = new Artista();
            artista.nombre = nombre;
            artista.paisOrigen = paisOrigen;
            artista.oyentesMensuales = oyentesMensuales;
            area.append("ARTISTA\nNombre: " + artista.nombre + "\nPaís de origen: " + artista.paisOrigen
                    + "\nOyentes mensuales: " + artista.oyentesMensuales + "\n\n");
            txtNombre.setText(""); txtPais.setText(""); txtOyentes.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Complete los datos del artista y use un número válido de oyentes.");
        }
    }

    private void crearCancion() {
        String titulo = txtTitulo.getText().trim();
        String genero = txtGenero.getText().trim();
        String nombreArtista = txtArtistaCancion.getText().trim();
        try {
            int duracionSegundos = Integer.parseInt(txtDuracion.getText().trim());
            if (titulo.isEmpty() || genero.isEmpty() || nombreArtista.isEmpty() || duracionSegundos <= 0)
                throw new IllegalArgumentException();
            Cancion cancion = new Cancion();
            cancion.titulo = titulo;
            cancion.duracionSegundos = duracionSegundos;
            cancion.genero = genero;
            cancion.nombreArtista = nombreArtista;
            area.append("CANCIÓN\nTítulo: " + cancion.titulo + "\nDuración: " + cancion.duracionSegundos
                    + " segundos\nGénero: " + cancion.genero + "\nArtista: " + cancion.nombreArtista + "\n\n");
            txtTitulo.setText(""); txtDuracion.setText(""); txtGenero.setText(""); txtArtistaCancion.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Complete los datos de la canción y use una duración válida.");
        }
    }
}
