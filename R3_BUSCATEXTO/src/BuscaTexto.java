import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuscaTexto {
    private String texto;
    private int puntero;

    // Constructor vacío
    public BuscaTexto() {
        this.texto = "";
        this.puntero = 0;
    }

    // Constructor con texto inicial
    public BuscaTexto(String texto) {
        this.texto = texto;
        this.puntero = 0;
    }

    // Método para cargar el contenido de un archivo en el atributo texto
    public void cargaFichero(String fichero) {
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            StringBuilder contenido = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea);
                contenido.append("\n");
            }
            this.texto = contenido.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para reiniciar el puntero a 0
    public void reinicia() {
        this.puntero = 0;
    }

    // Método para buscar una cadena desde el principio del texto
    public boolean busca(String cadena) {
        puntero = texto.indexOf(cadena);
        return puntero != -1;
    }

    // Método para buscar una cadena desde la posición actual del puntero
    public boolean buscaSiguiente(String cadena) {
        puntero = texto.indexOf(cadena, puntero + 1);
        return puntero != -1;
    }

    // Método para extraer una cadena entre dos delimitadores sin incluirlos
    public String extraeCadena(String delimitador1, String delimitador2) {
        int inicio = texto.indexOf(delimitador1, puntero);
        if (inicio == -1) return null;
        inicio += delimitador1.length();
        int fin = texto.indexOf(delimitador2, inicio);
        if (fin == -1) return null;
        return texto.substring(inicio, fin);
    }

    // Método para extraer una cadena entre dos delimitadores sin incluirlos
    public String extraerCadenaSin(String palabra, String delimitador2) {
        int inicio = texto.indexOf(palabra, puntero);
        if (inicio == -1) return null;
        int fin = texto.indexOf(delimitador2, inicio);
        if (fin == -1) return null;
        return texto.substring(inicio, fin);
    }


    //Metodo para extraer texto con los delimitadores incluidos
    public String extraeCadenaConDelim(String delimitador1, String delimitador2) {
        int inicio = texto.lastIndexOf(delimitador1, puntero);
        if (inicio == -1) return null;

        int fin = texto.indexOf(delimitador2, inicio + delimitador1.length());
        if (fin == -1) return null;

        // Encuentra el siguiente '</h1>'
        int siguienteDelimitador2 = texto.indexOf(delimitador2, fin + 1);
        if (siguienteDelimitador2 == -1) return null;

        int siguienteDelimitador3 = texto.indexOf(delimitador1, siguienteDelimitador2 + delimitador2.length());
        if (siguienteDelimitador3 == -1) return null;

        return texto.substring(inicio, siguienteDelimitador3);
    }

    // Getter y setter para el atributo puntero
    public int getPuntero() {
        return puntero;
    }

    public void setPuntero(int puntero) {
        if (puntero < 0)
            this.puntero = 0;
        else if (puntero > texto.length())
            this.puntero = texto.length();
        else
            this.puntero = puntero;
    }

    // Getter para el atributo texto
    public String getTexto() {
        return texto;
    }
}



/*INFO DE FUNCIONAMIENTO buscatextoconDelim:
Por supuesto, aquí tienes un paso a paso de cómo funciona el método `extraeCadenaConDelim`:

1. **Búsqueda del inicio del texto a extraer**:
   - `inicio = texto.lastIndexOf(delimitador1, puntero);`: Busca la última ocurrencia de `delimitador1` dentro del texto, comenzando desde la posición `puntero`. La posición resultante se almacena en la variable `inicio`.

2. **Validación de inicio**:
   - `if (inicio == -1) return null;`: Si no se encuentra el primer delimitador, el método retorna `null`, indicando que no se pudo extraer el texto.

3. **Búsqueda del fin del texto a extraer**:
   - `fin = texto.indexOf(delimitador2, inicio + delimitador1.length());`: Busca la primera ocurrencia de `delimitador2` después de la posición donde se encontró `delimitador1`. La posición resultante se almacena en la variable `fin`.

4. **Validación de fin**:
   - `if (fin == -1) return null;`: Si no se encuentra el segundo delimitador, el método retorna `null`, indicando que no se pudo extraer el texto.

5. **Búsqueda del siguiente delimitador2**:
   - `siguienteDelimitador2 = texto.indexOf(delimitador2, fin + 1);`: Busca la siguiente ocurrencia de `delimitador2` después de la posición donde se encontró el segundo delimitador `delimitador2`.

6. **Validación del siguiente delimitador2**:
   - `if (siguienteDelimitador2 == -1) return null;`: Si no se encuentra el siguiente delimitador `delimitador2`, el método retorna `null`.

7. **Búsqueda del siguiente delimitador1**:
   - `siguienteDelimitador3 = texto.indexOf(delimitador1, siguienteDelimitador2 + delimitador2.length());`: Busca la siguiente ocurrencia de `delimitador1` después de la posición donde se encontró el siguiente delimitador `delimitador2`.

8. **Validación del siguiente delimitador1**:
   - `if (siguienteDelimitador3 == -1) return null;`: Si no se encuentra el siguiente delimitador `delimitador1`, el método retorna `null`.

9. **Extracción del texto entre delimitadores**:
   - `texto.substring(inicio, siguienteDelimitador3)`: Utiliza las posiciones encontradas (`inicio` y `siguienteDelimitador3`) para extraer el texto comprendido entre estos índices. Esto devuelve el texto buscado.

10. **Retorno del texto extraído**:
    - El método retorna el texto extraído entre los delimitadores `delimitador1` y `delimitador2`.

Este proceso asegura que se extraiga el texto correctamente entre los delimitadores especificados.*/