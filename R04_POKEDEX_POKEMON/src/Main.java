import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Comando: java -jar R04_POKEDEX.jar pokemon.csv");
            System.out.println("Commando para salir de la terminal: exit");
            return;
        }

        String csvFileName = args[0];
        Pokedex pokedex = new Pokedex(csvFileName);
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Menú:");
            System.out.println("1. Capturar Pokémon");
            System.out.println("2. Porcentaje de Pokémon capturados");
            System.out.println("3. Buscar Pokémon por ID");
            System.out.println("4. Buscar Pokémon por nombre");
            System.out.println("5. Listado de todos los Pokémon");
            System.out.println("6. Listado de Pokémon capturados");
            System.out.println("7. Listado de Pokémon por tipo");
            System.out.println("8. Listado de Pokémon capturados por tipo");
            System.out.println("9. Salir");
            System.out.print("\nSeleccione una opción: ");
            if (scanner.hasNextInt()) {
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("\nIngrese el nombre del Pokémon a capturar: ");
                    String nombrePokemon = scanner.next(); // Leer la entrada del usuario
                    nombrePokemon = quitarAcentos(nombrePokemon);
                    if (Character.isLowerCase(nombrePokemon.charAt(0))) {   // Verificar si la primera letra es minúscula y convertirla a mayúscula si es necesario
                        nombrePokemon = Character.toUpperCase(nombrePokemon.charAt(0)) + nombrePokemon.substring(1).toLowerCase();
                    } else {
                        nombrePokemon = nombrePokemon.substring(0, 1).toUpperCase() + nombrePokemon.substring(1).toLowerCase(); //convertir las demas en minusculas para que no haya errores de sintaxis
                    }
                    pokedex.capturaPokemon(nombrePokemon);
                    break;

                case 2:
                    System.out.println("\nPorcentaje de Pokémon capturados: " + pokedex.porcentajeCapturas() + "%\n");
                    break;
                case 3:
                    System.out.print("\nIngrese el ID del Pokémon: ");
                    int idPokemon = scanner.nextInt();
                    Pokemon pokemonId = pokedex.buscaPokemon(idPokemon);
                    if (pokemonId != null) {
                        System.out.println("\nPokemon encontrado: " + pokemonId.toString());
                    } else {
                        System.out.println("\nNo se encontró ningún Pokémon con el ID especificado.\n");
                    }
                    break;
                case 4:
                    System.out.print("\nIngrese el nombre del Pokémon: ");
                    String nombre = scanner.next();
                    nombre = quitarAcentos(nombre);
                    if (Character.isLowerCase(nombre.charAt(0))) {
                        nombre = Character.toUpperCase(nombre.charAt(0)) + nombre.substring(1).toLowerCase();
                    }else {
                        nombre = nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
                    }
                    Pokemon pokemonNombre = pokedex.buscaPokemon(nombre);
                    if (pokemonNombre != null) {
                        System.out.println("\nPokemon encontrado: " + pokemonNombre.toString());
                    } else {
                        System.out.println("\nNo se encontró ningún Pokémon con el nombre especificado.\n");
                    }
                    break;
                case 5:
                    System.out.println("\nListado de todos los Pokémon:\n");
                    System.out.println(pokedex.listadoPokemon());
                    break;
                case 6:
                    System.out.println("\nListado de Pokémon capturados:\n");
                    System.out.println(pokedex.listadoPokemonCapturados());
                    break;
                case 7:
                    System.out.print("\nIngrese el tipo de Pokémon: ");
                    String tipo1 = scanner.next();
                    tipo1 = quitarAcentos(tipo1);
                    if (Character.isLowerCase(tipo1.charAt(0))) {
                        tipo1 = Character.toUpperCase(tipo1.charAt(0)) + tipo1.substring(1).toLowerCase();
                    }else{
                        tipo1 = tipo1.substring(0, 1).toUpperCase() + tipo1.substring(1).toLowerCase();
                    }
                    System.out.println("\nListado de Pokémon del tipo '" + tipo1 + "':");
                    System.out.println(pokedex.listadoPokemonTipo(tipo1));
                    break;
                case 8:
                    System.out.print("\nIngrese el tipo de Pokémon: ");
                    String tipo2 = scanner.next();
                    tipo2 = quitarAcentos(tipo2);
                    if (Character.isLowerCase(tipo2.charAt(0))) {
                        tipo2 = Character.toUpperCase(tipo2.charAt(0)) + tipo2.substring(1).toLowerCase();
                    }else{
                        tipo2 = tipo2.substring(0, 1).toUpperCase() + tipo2.substring(1).toLowerCase();
                    }
                    System.out.println("\nListado de Pokémon capturados del tipo '" + tipo2 + "':");
                    System.out.println(pokedex.listadoPokemonCapturadosTipo(tipo2));
                    break;
                case 9:
                    try {
                        pokedex.guardaCSV(csvFileName); // Guardar datos en el archivo CSV al finalizar el programa ACTUALIZA ORIGINAL
                        exit = true;
                    } catch (Exception e) {
                        System.out.println("\nError al guardar datos en el archivo CSV: " + e.getMessage());
                        exit = true; // Salir del bucle si ocurre un error al guardar los datos
                    }
                    break;

                default:
                    System.out.println("\nOpción no válida. Por favor, seleccione una opción del menú.");
                    break;
            }
        }else {
                // Limpiar el buffer del scanner para evitar bucles infinitos
                scanner.next();
                System.out.println("\nPor favor, con un número del 1 al 9 seleccione una opción del menú.");
            }
        }
        scanner.close();
    }

    // Método para quitar los acentos de una cadena
    private static String quitarAcentos(String input) {
        return input.replaceAll("[áÁ]", "a")
                .replaceAll("[éÉ]", "e")
                .replaceAll("[íÍ]", "i")
                .replaceAll("[óÓ]", "o")
                .replaceAll("[úÚ]", "u")
                .replaceAll("[ñÑ]", "n");
    }

}

