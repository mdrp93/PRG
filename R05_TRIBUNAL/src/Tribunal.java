import java.io.*;
import java.util.*;

public class Tribunal {

    private List<Profesor> listaProfesores;

    public Tribunal(String nombreArchivo) {
        listaProfesores = new ArrayList<>();
        cargarProfesoresDesdeArchivo(nombreArchivo);
    }


    private void cargarProfesoresDesdeArchivo(String nombreArchivo) {
        if (nombreArchivo.endsWith(".bin")) {
            System.out.println("\nCargando profesores desde archivo binario...\n");
            cargarProfesoresDesdeArchivoBinario(nombreArchivo);
        } else if (nombreArchivo.endsWith(".csv")) {
            System.out.println("\nCargando profesores desde archivo CSV...\n");
            cargarProfesoresDesdeArchivoCSV(nombreArchivo);
        } else {
            throw new IllegalArgumentException("\nFormato de archivo no válido.\n");
        }
    }

    private void cargarProfesoresDesdeArchivoBinario(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            while (true) {
                try {
                    String nombre = (String) ois.readObject();
                    String dni = (String) ois.readObject();
                    TipoGenero genero = TipoGenero.values()[ois.readInt()];
                    listaProfesores.add(new Profesor(nombre, dni, genero));
                    System.out.println("\nEl archivo .bin se cargo con exito\n");
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void cargarProfesoresDesdeArchivoCSV(String nombreArchivo) {
    try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split("-");
           // System.out.println("comprobacion");
            if (partes.length == 3) {
                String nombre = partes[0].trim();
                String dni = partes[1].trim();
                int codigoGenero = Integer.parseInt(partes[2].trim()); // Convertir el código de género a entero
                TipoGenero genero = codigoGenero == 0 ? TipoGenero.Hombre : TipoGenero.Mujer; // Asignar el enum correspondiente
                listaProfesores.add(new Profesor(nombre, dni, genero));
            }
        }
        System.out.println("\nEl archivo CSV se cargó con éxito\n");
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    public void eligeTribunalPro() {
       
        if (listaProfesores.isEmpty()) {
            System.out.println("\nNo hay profesores en la lista.\n");
            return;
        }
     
         // Ordenar la lista de profesores por el número del DNI leído de derecha a izquierda
         Collections.sort(listaProfesores, new Comparator<Profesor>() {
            @Override
            public int compare(Profesor p1, Profesor p2) {
                String dni1 = p1.getDni();
                String dni2 = p2.getDni();
                return dni1.substring(dni1.length() - 8).compareTo(dni2.substring(dni2.length() - 8));
            }
        });

        System.out.println("\nLista de profesores:\n");
        for (Profesor profesor : listaProfesores) {
            System.out.println(profesor);
        }
    
        // Sortear una posición de la lista al azar
        Random random = new Random();
        int posicionAleatoria = random.nextInt(listaProfesores.size());
        System.out.println("\nEn el sorteo ha salido la posición: " + posicionAleatoria+"\n");

    
        List<Profesor> hombres = new ArrayList<>();
        List<Profesor> mujeres = new ArrayList<>();

        // A partir de esa posición, elegir dos hombres y dos mujeres

        int index = posicionAleatoria; // Comenzar desde la posición sorteada
        int hombresEncontrados = 0;
        int mujeresEncontradas = 0;

        while (true) {
            Profesor profesor = listaProfesores.get(index);
            if (profesor.getGenero() == TipoGenero.Hombre && hombresEncontrados < 2) {
                hombres.add(profesor);
                hombresEncontrados++;
                } else if (profesor.getGenero() == TipoGenero.Mujer && mujeresEncontradas < 2) {
                    mujeres.add(profesor);
                    mujeresEncontradas++;
                }else if(hombresEncontrados == 2 && mujeresEncontradas == 2) {     // Salir del bucle si ya hemos seleccionado a dos hombres y dos mujeres    
                    break;
                }
       
            // Avanzar al siguiente profesor en la lista, circularmente
            index = (index + 1) % listaProfesores.size();
        
            // Si volvemos a la posición inicial, salir del bucle (para evitar un bucle infinito si no hay suficientes profesores)
            if (index == posicionAleatoria) {
                break;
            }
        }

        // Mostrar el tribunal elegido con nombre y dni
            /*System.out.println("\nVocales elegidos en el sorteo:\n");
                for (Profesor hombre : hombres) {
                    System.out.println(hombre);
                }
                for (Profesor mujer : mujeres) {
                    System.out.println(mujer);
                }
            System.out.println("");
            }*/

        // Mostrar el tribunal elegido solo nombre  
        System.out.println("\nVocales elegidos en el sorteo:\n");
        for (Profesor hombre : hombres) {
            System.out.println(hombre.getNombre()); // Imprimir solo el nombre del hombre
        }
        for (Profesor mujer : mujeres) {
            System.out.println(mujer.getNombre()); // Imprimir solo el nombre de la mujer
        }
        System.out.println("\n");
        }
}



/*Para realizar el sorteo del tribunal en la función eligeTribunalPro() ordenando la lista por el DNI pero leído de derecha a izquierda:

Crear un comparador personalizado que ordene los profesores según el número del DNI leído de derecha a izquierda.
Ordenar la lista de profesores utilizando este comparador.
Seleccionar los profesores de manera similar a como se hizo en eligeTribunal().

*/


/*Comparator es una interfaz funcional en Java que define un método compare(T o1, T o2) para comparar dos objetos de tipo T.
 Cuando se utiliza junto con Collections.sort(), permite ordenar una lista según el criterio especificado en el comparador.

 En esta expresión, se está utilizando una forma de declaración anónima de una clase que implementa la interfaz Comparator<Profesor>.
 Esto significa que se crea una clase temporal sin nombre que implementa la interfaz Comparator<Profesor>, y se define la lógica de comparación dentro de su método compare().

  la lógica de comparación se basa en los últimos 8 dígitos del número del DNI, leídos de derecha a izquierda. Esto se hace mediante el uso
 del método substring() para obtener una subcadena del DNI y luego se compara utilizando el método compareTo() de String.
 */