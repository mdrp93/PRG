***
#### ⚠️ The recommendation is to open this README file in a Markdown editor or in IntelliJ IDEA, as Visual Studio Code sometimes encounters issues with certain links and other components of the file in its preview mode. ⚠️
***

# PROJECT: TEXT FINDER (BuscaTexto) <a name="top"></a>

<p align.text="left">
<img src="https://img.shields.io/badge/PROJECT-WEB APPLICATION DEVELOPMENT-violet">  <img src="https://img.shields.io/badge/SUBJECT-PROGRAMMING-blue">
<img src="https://img.shields.io/badge/EXERCISE-REVIEW OF JAVA-green+">
 </p>  

- `Finality`: The program allows extracting information from a text.

## Sections of this README

***
  - [Prerequisites 📋](#Prerequisites-📋-a-namepre-a)
  - [Run files 🔧 ](#run-files--a-nameruna)
  - [Tools 🛠️ ](#tools--a-nametoolsa)
  - [Libraries 📚 ](#libraries--a-namelibrariesa)
  - [General Info 📝 ](#general-info--a-nameinfoa)
  - [Usage clarification 📖 ](#usage-clarification--a-nameextraa)
  - [Auth@r ✒️ ](#authr--a-nameautora)
  - [License 📄](#license-a-namelicensea)

## Prerequisites 📋<a name="pre"></a>
***

_Things you need to run the project_

```
Java Development Kit (JDK) installed on your machine
IDE (Integrated Development Environment) like IntelliJ IDEA or VScode
```

## Run files 🔧<a name="run"></a>

***
_Step-by-step guide on how to run the program_

1. Ensure you have the provided .jar file for the project.
2. Make sure you have Java installed on your system.
3. Locate the .jar file in your file explorer.
4. Double-click the .jar file to execute it.*
5. The program's interface should appear, allowing you to perform text searches.

*If you want to execute the program from the console, you can use the following command:

```
C:\YourDirectory\jarFolder> java -jar R3_BUSCATEXTO.jar cambio.html
```

## Tools 🛠️ <a name="tools"></a>

***
_Click to follow the links to the tools._

[<img alt="Static Badge" src="https://img.shields.io/badge/Windows-0078D6?style=for-the-badge&logo=windows&logoColor=white">](https://www.microsoft.com/es-es/software-download/windows11)

[<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=003D85">](https://www.java.com/es/download/ie_manual.js/)

[<img src="https://img.shields.io/badge/IntelliJ_IDEA-FF5733.svg?style=for-the-badge&logo=intellij-idea&logoColor=black">](https://www.jetbrains.com/es-es/idea/)

[<img src="https://img.shields.io/badge/Markdown-000000?style=for-the-badge&logo=markdown&logoColor=white">](https://www.markdownguide.org/tools/)

## Libraries 📚 <a name="libraries"></a>
***

A list of libraries used within the project:

- [Java SE 22 & JDK 22](https://docs.oracle.com/en/java/javase/22/docs/api/index.html): Version 22.0.0
  
## General Info 📝 <a name="info"></a>
***
### Statement of minimum requirements for this project

_The content of the exercise and the program are in Spanish._

    Escribe la clase BuscaTexto que nos permitirá sacar información de un texto. La clase tendrá los siguientes componentes:

    Atributos:
    - String texto, donde almacenaremos el texto en el que vamos a buscar la información
    - int puntero, donde guardaremos un puntero que nos dirá por dónde vamos buscando en el texto.
    
    Constructores:
    - Un constructor vacío, que inicializará el texto a una cadena vacía y el puntero a 0.
    - Un constructor al que le pasamos una cadena que guardará en texto y pondrá el puntero a 0.

    Métodos:
    - cargaFichero(String fichero), lee el fichero de texto que le pasamos y guarda su contenido en el atributo texto.
    - reinicia(), pone el puntero a 0.
    - boolean busca(String cadena), busca la cadena dentro del texto y guarda esa posición en el puntero. Si la cadena no se encuentra, no tocaremos el puntero y devolveremos false. Siempre buscará desde el principio.
    - boolean buscaSiguiente(String cadena), busca la cadena dentro del texto a partir de la posición actual del puntero y guarda el resultado en el puntero. Si no se encuentra, no tocamos el puntero y devolvemos false.
    - String extraeCadena(String delimitador1, String delimitador2), a partir de la posición actual del puntero, busca el delimitador1. A partir de ahí irá guardando todos los caracteres que encuentre en una cadena 
      hasta llegar al delimitador2. Devolverá la cadena obtenida. Si no se encuentra alguno de los dos delimitadores, nos devuelve null. Hay que tener en cuenta que los delimitadores pueden ser cadenas de cualquier 
      tamaño y que en la cadena que se devuelve no deben ir incluidos los mismos..
      Ej.: Si el texto es un HTML “<h1>ejemplo</h1>” y decimos extraeCadena(“<”, “>”) nos debería devolver “h1”, mientras que si decimos extraeCadena(“>”,“<”) devolvería “ejemplo”.
    - String extraeCadenaConDelim(String delimitador1, String delimitador2), igual que la anterior, pero en la cadena que nos devuelve incluye los delimitadores.
      Ej.: Si el texto es “<html><h1>ejemplo</h1></html>” y decimos eCD(“<h1>”,“</h1>”), nos extraerá el trozo: “<h1>ejemplo</h1>”.
    
    Propiedades:
    - get y set para el atributo puntero. Al modificar el puntero, no se podrá escribir un valor menor que 0 ni mayor que la longitud del texto. Si nos escriben un valor menor que 0 se guardará un 0 y si nos escriben 
      un valor mayor que la longitud del texto, se escribirá la longitud del texto.
    - get para el atributo texto.

### Operation of the program

1. **Launching the Program:** When you run the program, it opens a graphical user interface (GUI) window titled "Buscador de Texto" (Text Finder).

2. **Input Fields:** The GUI consists of several input fields and options:
    - **Archivo Field:** A text field where you can enter the path of the file to be searched.
    - **Seleccionar Archivo Button:** A button to open a file chooser dialog and select the file.
    - **Texto a buscar Field:** A text field where you can enter the text you want to search for within the selected file.
    - **Incluir Delimitadores CheckBox:** A checkbox to indicate whether to include delimiters in the search.
    - **Es un id de delimitador CheckBox:** A checkbox to specify whether the text to search for is an identifier delimiter.
    - **Buscar y Extraer Button:** A button to initiate the search and extraction process.

3. **Search and Extraction:** After entering the necessary information and clicking the "Buscar y Extraer" button, the program performs the following actions:
    - It reads the input file specified in the "Archivo Field."
    - It searches for the specified text in the file and extracts the text based on the selected options (including or excluding delimiters).
    - The extracted text is displayed in the "resultadoTextArea," which is a JTextArea component.

4. **Output Display:** The extracted text, along with any additional information or messages, is displayed in the GUI's result area.

### How the Program Works

1. **GUI Setup:** The program sets up a graphical user interface using Swing components such as JFrame, JPanel, JTextField, JCheckBox, JTextArea, and JButton.

2. **File Selection:** When you click the "Seleccionar Archivo" button, a file chooser dialog opens, allowing you to select the file you want to search.

3. **Search and Extraction Logic:**
    - The program utilizes a `BuscarTextoLog` class to perform the search and extraction logic.
    - The `BuscarTextoLog` class encapsulates methods for searching and extracting text from files.
    - Delimiters are used to specify the boundaries of the text to be extracted.
    - Depending on the options selected (including/excluding delimiters, whether the text is an identifier delimiter), different extraction methods are used.

4. **Displaying Results:** After the search and extraction process, the extracted text is displayed in the JTextArea component within the GUI.

Overall, the program provides a user-friendly interface for searching and extracting text from files, allowing users to specify various options for the search process.

## Usage clarification 📖 <a name="extra"></a>
***

_Clarifications about the project_

### Description

This project implements a text finder that allows users to search for words or phrases within a specific file.

### Features

- **File selection:** Users can select the file they want to search text in.
- **Precise searching:** It is recommended to enter the exact word and consider case sensitivity for precise searching.

### Usage

To use the text finder, follow these steps:

1. Run the application.
2. Select the file you want to search text in.
3. Enter the word or phrase you want to search for.
4. Select additional options as needed.
5. Click the "Search and extract text" button.
6. View the results in the results area.

```
Note: For precise searching, it is recommended to enter the exact word and consider case sensitivity.
```
### Screenshot

_Program preview_

![App BuscaTexto: program preview](/resources/appBuscaTexto.png)
## Auth@r ✒️ <a name="autor"></a>

***
**María Diaz-Rozas** &nbsp; [<img src="https://img.shields.io/badge/mdrp93-gitHub?style=social&logo=gitHub&logoColor=00000&link=Click%20in">](https://github.com/mdrp93) &nbsp;
← _Click to view GitHub profile_

## License 📄<a name="license"></a>

***
This project is under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

<br>

[Subir](#project-text-finder-buscatexto-a-nametopa)
