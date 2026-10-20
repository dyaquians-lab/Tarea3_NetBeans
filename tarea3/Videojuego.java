package tarea3;

public class Videojuego {
    private final String titulo;
    private final String consola;
    private final int anioLanzamiento;

    public Videojuego(String titulo, String consola, int anioLanzamiento) {
        this.titulo = titulo;
        this.consola = consola;
        this.anioLanzamiento = anioLanzamiento;
    }

    public String getTitulo() { return titulo; }
    public String getConsola() { return consola; }
    public int getAnioLanzamiento() { return anioLanzamiento; }
}
