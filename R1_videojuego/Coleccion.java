import java.util.ArrayList;
import java.util.List;

// Clase Coleccion
public class Coleccion {
    private List<Videojuego> lista;

    // Constructor vacío
    public Coleccion() {
        lista = new ArrayList<>();
    }

    // Método para insertar un videojuego en la lista
    public void insertaVideojuego(Videojuego v) {
        lista.add(v);
    }

    // Método para eliminar un videojuego de la lista por posición
    public void eliminaVideojuego(int posicion) {
        if (posicion >= 0 && posicion < lista.size()) {
            lista.remove(posicion);
        } else {
            throw new IllegalArgumentException("La posición especificada no es válida.");
        }
    }

    // Método para obtener un videojuego de la lista por posición
    public Videojuego getVideojuego(int posicion) {
        if (posicion >= 0 && posicion < lista.size()) {
            return lista.get(posicion);
        } else {
            throw new IllegalArgumentException("La posición especificada no es válida.");
        }
    }

    // Método toString para obtener un listado de los videojuegos con todos sus datos
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("  Videojuego\t\tAño\tPlataforma\tTipo de juego\tValoración\n");
        result.append("-------------------------------------------------------------------------------------------------------------------------------------\n");
        for (Videojuego v : lista) {
            result.append(v.getNombre()).append("\t").append(v.getAnno()).append("\t").append(v.getPlataforma())
                    .append("\t").append(v.getTipoJuego()).append("\t").append(v.getValoracion()).append("\n");
        }
        return result.toString();
    }

    // Método toStringNum para obtener un listado numerado de los videojuegos
    public String toStringNum() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < lista.size(); i++) {
            result.append(i + 1).append("- ").append(lista.get(i).getNombre()).append("\n");
        }
        return result.toString();
    }

    // Método para obtener la lista de videojuegos
    public List<Videojuego> getLista() {
        return lista;
    }

    // Método para obtener los nombres de los videojuegos
    public String[] getNombresVideojuegos() {
        String[] nombres = new String[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            nombres[i] = lista.get(i).getNombre();
        }
        return nombres;
    }

    // Método para obtener un videojuego de la lista por su nombre
    public Videojuego getVideojuegoPorNombre(String nombre) {
        for (Videojuego v : lista) {
            if (v.getNombre().equals(nombre)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No se encontró ningún videojuego con el nombre especificado.");
    }

    // Método para obtener el índice de un videojuego por su nombre
    public int getIndiceVideojuegoPorNombre(String nombre) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equals(nombre)) {
                return i;
            }
        }
        throw new IllegalArgumentException("El videojuego con nombre '" + nombre + "' no se encuentra en la colección.");
    }
}
