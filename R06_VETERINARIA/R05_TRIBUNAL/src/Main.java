import java.io.*;

public class Main {
    public static void main(String[] args) {


        if (args.length != 1) {

            System.out.println("\nComando archivo.bin : java -jar R05_TRIBUNAL.jar profesores.bin");
            System.out.println("Comando archivo.csv : java -jar R05_TRIBUNAL.jar profesores.csv\n");
            System.out.println("Comando para salir de la terminal: exit\n");

            return;
        }
            String nombreArchivo = args[0];

            Tribunal tribunal = new Tribunal(nombreArchivo);
            tribunal.eligeTribunalPro();
        }
    }
