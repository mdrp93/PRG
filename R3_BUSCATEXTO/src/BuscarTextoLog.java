//Ejercicios de Repaso de Clases (R03)

public class BuscarTextoLog {

    // Buscar texto siempre que la cadena sea lo que está dentro de los delimitadores como h1
    public String BuscarTextoLogical(String archivo, String textoABuscar, boolean incluirDelimitadores, boolean esIdentificadorDelimitador) {
        // Verificar que se haya ingresado el texto a buscar
        if (archivo.isEmpty()) {
            return "Por favor, debe ingresar la ruta del archivo: C:/ArchivosHTML/ejemplo.html";
        } else if (textoABuscar.isEmpty()) { 
            return "Por favor, ingrese el texto a buscar (por ejemplo: Título o h1).";
        }
    
        // Crear una instancia de BuscaTexto
        BuscaTexto buscador = new BuscaTexto();
    
        // Cargar el contenido del archivo en el objeto BuscaTexto
        buscador.cargaFichero(archivo);
        buscador.busca(textoABuscar);
    
        // Buscar y extraer el texto comprendido entre los tags ">" y "<"
        String delimitador1 = ">";
        String delimitador2 = "<";
        String delimitador3 = "<";
        String delimitador4 = ">";
        String textoExtraido = null;
        String textoExtraidoCon = null;
        String palabraExtraida = null;
    
        StringBuilder resultado = new StringBuilder();
    
        if (!esIdentificadorDelimitador) {
            // Opción 1: La palabra NO está entre los delimitadores
            if (incluirDelimitadores) {
                // Opción 1.1: El usuario quiere imprimir los delimitadores
                palabraExtraida = buscador.extraeCadenaConDelim(delimitador3, delimitador4);
                resultado.append("PALABRA ENCONTRADA. \n\nTexto extraído CON delimitadores.\n").append(palabraExtraida).append("\n");
            } else {
                // Opción 1.2: El usuario NO quiere imprimir los delimitadores
                palabraExtraida = buscador.extraerCadenaSin(textoABuscar, delimitador2);
                resultado.append("PALABRA ENCONTRADA. \n\nTexto extraído SIN delimitadores.\n ").append(palabraExtraida).append("\n");
            }
        } else  {
        
            if (incluirDelimitadores) {
                textoExtraidoCon = buscador.extraeCadenaConDelim(delimitador3, delimitador4);
                resultado.append("ID DELIMITADOR ENCONTRADO. \n\nTexto extraído CON delimitadores.\n").append(textoExtraidoCon).append("\n");
            } else {
                // Opción 2.2: El usuario NO quiere imprimir los delimitadores y la palabra no está entre ellos
                textoExtraido = buscador.extraeCadena(delimitador1, delimitador2);
                resultado.append("ID DELIMITADOR ENCONTRADO. \n\nTexto extraído SIN delimitadores. \n\n").append(textoExtraido).append("\n");
            }
        }
        
        return resultado.toString(); // Devuelve el resultado como cadena
    }
    
}



//C:\RepositoriosGit\RepoPRG\PRG\R3_BUSCATEXTO\src\cambio.html

//C:\R3_BUSCATEXTO\resources\cambio.html

/*Palabra encontrada entre delimitadores y se desea imprimir con delimitadores (palabraExtraida!= null && textoExtraidoCon != null):
    Este caso se produce cuando la palabra buscada está entre los delimitadores y el usuario elige imprimir los delimitadores.
    Se imprimirá el texto extraído con los delimitadores.

Palabra encontrada entre delimitadores y se desea imprimir sin delimitadores (palabraExtraida!= null && textoExtraido != null):
    Sucede cuando la palabra buscada está entre los delimitadores, pero el usuario decide no imprimir los delimitadores.
    Se imprimirá el texto extraído sin los delimitadores.

Palabra no encontrada entre delimitadores y se desea imprimir con delimitadores (palabraExtraida == null && textoExtraidoCon != null):
    Este caso se presenta cuando la palabra buscada no está entre los delimitadores y el usuario elige imprimir los delimitadores de otro segmento del texto.
    Se imprimirá el texto extraído con los delimitadores de ese segmento.

Palabra no encontrada entre delimitadores y se desea imprimir sin delimitadores (palabraExtraida == null && textoExtraido != null):
    Ocurre cuando la palabra buscada no está entre los delimitadores y el usuario opta por no imprimir los delimitadores de otro segmento del texto.
    Se imprimirá el texto extraído sin los delimitadores de ese segmento.

Palabra no encontrada entre delimitadores (palabraExtraida == null && textoExtraidoCon == null && textoExtraido == null):
    Este es el caso en el que la palabra buscada no se encuentra entre los delimitadores especificados.
    Se imprimirá un mensaje indicando que la palabra no se encontró entre los delimitadores. */


