import java.util.ArrayList;
import java.util.List;

public class ClinicaVeterinaria {

    //Atributos
    private static ClinicaVeterinaria instance;
    private List<Animal> listaAnimales;

    // Constructor básico para inicializar la lista
    public ClinicaVeterinaria() {
        listaAnimales = new ArrayList<>();
    }

    public static ClinicaVeterinaria getInstance() {
        if (instance == null) {
            instance = new ClinicaVeterinaria();
        }
        return instance;
    }

    // Método para insertar un animal en la lista
    public void insertaAnimal(Animal animal) {
        listaAnimales.add(animal);
    }

    //Método para buscar un animal por nombre
    public Animal buscaAnimal(String nombre) {
        for (Animal animal : listaAnimales) {
            if (animal.getNombre().equals(nombre)) {
                return animal;
            }
        }
        return null; // Retorna null si no se encuentra el animal con el nombre dado
    }

    // Método para modificar el comentario de un animal
    public void modificaComentarioAnimal(String nombre, String nuevoComentario) {
        Animal animal = buscaAnimal(nombre);
        // Impresión de depuración para verificar los comentarios
        System.out.println("Comentarios obtenidos del área de texto 2: " + nuevoComentario);
        if (animal != null) {
            animal.setComentarios(nuevoComentario);
        }
    }

    // Método para obtener la lista de animales
    public List<Animal> getListaAnimales() {

        return listaAnimales;
    }
}



