# Tarea 3 - Java y Apache NetBeans

Proyecto con seis ejercicios de interfaces gráficas Swing, arreglos, archivos de texto y programación orientada a objetos.

## Requisitos
- JDK 8 o superior.
- Apache NetBeans.

## Cómo abrir y ejecutar
1. En NetBeans, selecciona **File > New Project > Java with Ant > Java Application**.
2. Ponle como nombre `Tarea3_NetBeans`.
3. Copia la carpeta `src/tarea3` de este proyecto dentro de la carpeta `src` del proyecto creado (o abre/importa el proyecto si tu NetBeans lo permite).
4. Copia la carpeta `temp` en la raíz del proyecto, al mismo nivel que `src`.
5. Abre `tarea3.Main.java` y presiona **Run File**. También puedes establecer `tarea3.Main` como clase principal.
6. Desde el menú principal, abre cualquiera de los seis ejercicios.

## Archivos de salida
Los ejercicios 1 y 2 escriben:
- `temp/daniel_reporte_ventas.txt`
- `temp/daniel_asistencia.txt`

El ejercicio 3 lee `temp/temperaturas.txt`. El ejercicio 4 lee y actualiza `temp/discos.csv`.

> Si tu profesor requiere que el nombre de archivo use tu nombre exacto, cambia `daniel` en `VentasFrame.java` y `AsistenciaFrame.java` por el nombre solicitado.

## Estructura
- `src/tarea3/Main.java`: menú principal.
- `src/tarea3/VentasFrame.java`: control de ventas semanales.
- `src/tarea3/AsistenciaFrame.java`: registro de asistentes.
- `src/tarea3/TemperaturasFrame.java`: lectura y análisis de temperaturas.
- `src/tarea3/DiscosFrame.java`: búsqueda y actualización de discos.
- `src/tarea3/Artista.java` y `Cancion.java`: clases del ejercicio 5.
- `src/tarea3/StreamingFrame.java`: formulario de artistas y canciones.
- `src/tarea3/Videojuego.java` y `VideojuegosFrame.java`: catálogo dinámico.
- `temp/`: archivos de datos de ejemplo y salida.
