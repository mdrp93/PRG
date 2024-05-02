import java.time.LocalDate;


public class Perro extends Animal {

    //Atributos adicionales
    private RazaPerro raza;
    private String microchip;

    //Constructor
    public Perro (String nombre, RazaPerro raza, LocalDate fechaNacimiento, double peso, String microchip, String comentarios){
        super(nombre, fechaNacimiento, peso, comentarios);
        this.raza = raza;
        this.microchip = microchip;
    }

    //propiedades
    public RazaPerro getRaza() {
        return raza;
    }

    public String getMicrochip() {
        return microchip;
   }


    //método toString
    @Override
    public String toString() {
        return "Ficha de Perro\n" +
                "Nombre: " + getNombre() + "\n" +
                "Raza: " + raza + "\n" +
                "Fecha de Nacimiento: " + getFechaNacimiento() + "\n" +
                "Peso: " + getPeso() + " kg\n" +
                "Microchip: " + microchip + "\n" +
                "Comentarios: " + getComentarios();
    }
    
}











/** INFO: "super" en Java se utiliza para referirse a la clase padre de la clase actual. Cuando se llama a super() dentro del constructor de una clase hija, se está invocando al constructor de la clase padre.
 * El constructor de la clase Perro llame al constructor de la clase Animal (su clase padre) para inicializar los atributos comunes, como el nombre, la fecha de nacimiento y el peso.
 **/