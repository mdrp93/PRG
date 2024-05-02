
import java.time.LocalDate;

public class Gato extends Animal {

    //Atributos adicionales
    private RazaGato raza;
    private String microchip;

    //Constructor
    public Gato (String nombre, RazaGato raza, LocalDate fechaNacimiento, double peso, String microchip, String comentarios){
        super(nombre, fechaNacimiento, peso, comentarios);
        this.raza = raza;
        this.microchip = microchip;
    }

    //propiedades
    public RazaGato getRaza() {
        return raza;
    }

    public String getMicrochip() {
        return microchip;
    }


    //método toString
    @Override
    public String toString() {
        return "Ficha de Gato\n" +
                "Nombre: " + getNombre() + "\n" +
                "Raza: " + raza + "\n" +
                "Fecha de Nacimiento: " + getFechaNacimiento() + "\n" +
                "Peso: " + getPeso() + " kg\n" +
                "Microchip: " + microchip + "\n" +
                "Comentarios: " + getComentarios();
    }

}


