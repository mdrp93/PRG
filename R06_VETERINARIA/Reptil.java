import java.time.LocalDate;

public class Reptil extends Animal {
    //Atributos adicionales
    private EspecieReptil especie;
    private boolean venenoso;

    //Constructor
    public Reptil (String nombre, EspecieReptil especie, LocalDate fechaNacimiento, double peso, boolean venenoso, String comentarios){
        super(nombre, fechaNacimiento, peso, comentarios);
        this.especie = especie;
        this.venenoso = venenoso;
    }

    //propiedades
    public EspecieReptil getEspecie() {
        return especie;
    }
    public boolean getVenenoso() {
        return venenoso;
    }


    //método toString
    @Override
    public String toString() {
        String esVenenoso = venenoso ? "sí" : "no";
        return "Ficha de Reptil\n" +
                "Nombre: " + getNombre() + "\n" +
                "Especie: " + especie + "\n" +
                "Fecha de Nacimiento: " + getFechaNacimiento() + "\n" +
                "Peso: " + getPeso() + " kg\n" +
                "¿Es venenoso?: " + esVenenoso + "\n" +
                "Comentarios: " + getComentarios();
    }
}
