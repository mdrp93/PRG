import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Pokedex {
    private List<Pokemon> pokemonList;

    // Empty constructor to initialize an empty list
    public Pokedex() {
        this.pokemonList = new ArrayList<>();
    }

    // Constructor to load Pokémon from a CSV file
    public Pokedex(String csvFileName) {
        this.pokemonList = new ArrayList<>();
        leeCSV(csvFileName);
    }

    // Método para cargar la lista desde un archivo CSV
    public void leeCSV(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String nombre = data[1];
                String tipo = data[2];
                int ataque = Integer.parseInt(data[3]);
                int defensa = Integer.parseInt(data[4]);
                int vida = Integer.parseInt(data[5]);
                int ataqueEspecial = Integer.parseInt(data[6]);
                int defensaEspecial = Integer.parseInt(data[7]);
                int velocidad = Integer.parseInt(data[8]);
                String habilidad = data[9];
                boolean capturado = Boolean.parseBoolean(data[10]);

                // Crear un objeto Pokemon y agregarlo a la lista
                Pokemon pokemon = new Pokemon(id, nombre, tipo, ataque, defensa, vida, ataqueEspecial, defensaEspecial, velocidad, habilidad, capturado);
                pokemonList.add(pokemon);
            }
            System.out.println("Datos cargados desde el archivo CSV exitosamente. \n");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public void guardaCSV(String fileName) {
        String saveFileName = "pokedexSave.csv"; //copia de seguridad
        try (BufferedReader br = new BufferedReader(new FileReader("pokemon.csv"));
             FileWriter writer = new FileWriter(saveFileName)) {

            // Leer el contenido del archivo original y almacenarlo temporalmente en una lista de cadenas
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            // Iterar sobre la lista de Pokémon y actualizar el estado de "capturado" en la lista de cadenas
            for (Pokemon pokemon : pokemonList) {
                String capturado = pokemon.isCaptured() ? "true" : "false";
                for (int i = 0; i < lines.size(); i++) {
                    String[] data = lines.get(i).split(",");
                    int id = Integer.parseInt(data[0]);
                    if (id == pokemon.getId()) {
                        // Actualizar el campo "capturado" en la línea correspondiente
                        data[data.length - 1] = capturado;
                        lines.set(i, String.join(",", data));
                        break;
                    }
                }
            }

            // Escribir el contenido actualizado en el nuevo archivo CSV
            for (String updatedLine : lines) {
                writer.write(updatedLine + "\n");
            }
            System.out.println("\nDatos guardados en el archivo CSV exitosamente.\n");
            System.out.println("\nSe ha creado un archivo de seguridad: pokedexSave.csv\n");
        } catch (IOException e) {
            System.out.println("\nError al guardar en el archivo CSV: " + e.getMessage());
        }
    }


    // Method to capture a Pokémon by name
    public void capturaPokemon(String pokemonName) {
        for (Pokemon pokemon : pokemonList) {
            if (pokemon.getName().equalsIgnoreCase(pokemonName)) {
                if (pokemon.isCaptured()) {
                    System.out.println("\n¡El Pokémon '" + pokemonName + "' ya ha sido capturado!\n");
                } else {
                    pokemon.setCaptured(true);
                    System.out.println("\n¡El Pokémon '" + pokemonName + "' ha sido capturado!\n"); //capturar un Pokémon que ya está marcado como capturado
                    guardaCSV("pokemon.csv");}
                return; // Exit loop once Pokémon is found and handled
            }
        }
        System.out.println("\n¡El Pokémon '" + pokemonName + "' no se encontró en la lista!\n");
    }


    // Method to calculate the percentage of captured Pokémon
    public double porcentajeCapturas() {
        int totalPokemon = pokemonList.size();
        int capturados = 0;
        for (Pokemon pokemon : pokemonList) {
            if (pokemon.isCaptured()) {
                capturados++;
            }
        }
        return (double) capturados / totalPokemon * 100.0;
    }

    // Method to search for a Pokémon by ID
    public Pokemon buscaPokemon(int id) {
        for (Pokemon pokemon : pokemonList) {
            if (pokemon.getId() == id) {
                return pokemon;
            }
        }
        return null; // Return null if Pokémon with given ID is not found
    }

    // Method to search for a Pokémon by name
    public Pokemon buscaPokemon(String name) {
        for (Pokemon pokemon : pokemonList) {
            if (pokemon.getName().equalsIgnoreCase(name)) {
                return pokemon;
            }
        }
        return null; // Return null if Pokémon with given name is not found
    }

    // Method to get a list of all Pokémon with their details
    public String listadoPokemon() {
        StringBuilder sb = new StringBuilder();
        for (Pokemon pokemon : pokemonList) {
            sb.append("\nID: ").append(pokemon.getId())
                    .append(", Nombre: ").append(pokemon.getName())
                    .append(", Tipo: ").append(pokemon.getType())
                    .append(", Capturado: ").append(pokemon.isCaptured() ? "Sí" : "No")
                    .append("\n");
        }
        return sb.toString();
    }

    // Method to get a list of captured Pokémon with their ID, name, and type
    public String listadoPokemonCapturados() {
        StringBuilder sb = new StringBuilder();
        for (Pokemon pokemon : pokemonList) {
            if (pokemon.isCaptured()) {
                sb.append("\nID: ").append(pokemon.getId())
                        .append(", Nombre: ").append(pokemon.getName())
                        .append(", Tipo: ").append(pokemon.getType())
                        .append("\n");
            }
        }
        return sb.toString();
    }
    // Method to get a list of Pokémon of a specific type with their ID, name, and capture status
    public String listadoPokemonTipo(String tipo) {
        StringBuilder sb = new StringBuilder();
        for (Pokemon pokemon : pokemonList) {
            if (pokemon.getType().equalsIgnoreCase(tipo)) {
                sb.append("\nID: ").append(pokemon.getId())
                        .append(", Nombre: ").append(pokemon.getName())
                        .append(", Capturado: ").append(pokemon.isCaptured() ? "Sí" : "No")
                        .append("\n");
            }
        }
        return sb.toString();
    }
    // Method to get a list of captured Pokémon of a specific type with their ID and name
    public String listadoPokemonCapturadosTipo(String tipo) {
        StringBuilder sb = new StringBuilder();
        for (Pokemon pokemon : pokemonList) {
            if (pokemon.getType().equalsIgnoreCase(tipo) && pokemon.isCaptured()) {
                sb.append("\nID: ").append(pokemon.getId())
                        .append(", Nombre: ").append(pokemon.getName())
                        .append("\n");
            }
        }
        return sb.toString();
    }

}

