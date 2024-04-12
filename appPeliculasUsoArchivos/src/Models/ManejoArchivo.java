/*
 * Clase ManejoArchivo
 *
 * Esta clase proporciona métodos para manejar el almacenamiento y recuperación de objetos Pelicula
 * en un archivo de texto. Utiliza operaciones de lectura y escritura de archivos para mantener una
 * lista de películas que se puede actualizar y recuperar de forma persistente.
 */

package Models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase para gestionar el archivo que contiene datos de películas.
 * Permite agregar nuevas películas al archivo y leer la lista de películas existentes.
 */
public class ManejoArchivo {
    // Ruta del archivo donde se almacenan los datos de las películas.
    private final String ruta = "C:/datos/listaPelicula.txt";
    
    // Lista para almacenar las películas que se leen del archivo.
    private ArrayList<Pelicula> lista = new ArrayList<>();

    /**
     * Agrega una nueva película al archivo.
     *
     * @param pelicula El objeto Pelicula a agregar.
     * Este método escribe los datos de la película al final del archivo especificado en la ruta.
     * Los datos de la película se escriben en el formato: codigo:nombre:categoria:precio
     */
    public void agregar(Pelicula pelicula) {
        try (FileWriter fw = new FileWriter(ruta, true)) {
            String cadena = pelicula.getCodigo() + ":" + pelicula.getNombre() + ":" + pelicula.getCategoria() + ":" + pelicula.getPrecio() + System.lineSeparator();
            fw.write(cadena);
        } catch (IOException ex) {
            System.err.println("Error al agregar película: " + ex.getMessage());
        }
    }

    /**
     * Lee los datos de las películas desde el archivo y los almacena en una lista.
     *
     * @return Una lista de objetos Pelicula que representan las películas almacenadas en el archivo.
     * Este método limpia la lista existente de películas y lee el archivo línea por línea,
     * creando un nuevo objeto Pelicula por cada línea válida y agregándolo a la lista.
     */
    public ArrayList<Pelicula> leer() {
        lista.clear(); // Limpia la lista existente de películas
        try (BufferedReader fr = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = fr.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length == 4) { // Asegura que la línea tiene exactamente cuatro partes
                    Pelicula pelicula = new Pelicula(partes[0], partes[1], partes[2], Double.parseDouble(partes[3]));
                    lista.add(pelicula);
                } else {
                    System.err.println("Error de formato en línea: " + linea);
                }
            }
        } catch (IOException | NumberFormatException ex) {
            System.err.println("Error al leer películas: " + ex.getMessage());
        }
        return lista;
    }
}
