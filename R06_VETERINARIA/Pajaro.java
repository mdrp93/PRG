import java.time.LocalDate;

public class Pajaro extends Animal {

    //Atributos adicionales
    private EspeciePajaro especie;
    private boolean cantor;

    //Constructor
    public Pajaro (String nombre, EspeciePajaro especie, LocalDate fechaNacimiento, double peso, boolean cantor, String comentarios){
        super(nombre, fechaNacimiento, peso, comentarios);
        this.especie = especie;
        this.cantor = cantor;
    }

    //propiedades
    public EspeciePajaro getEspecie() {
        return especie;
    }
    public boolean getCantor() {
        return cantor;
    }

    //método toString
    @Override
    public String toString() {
        String esCantor = cantor ? "sí" : "no";  /*Si la condición es verdadera, se asigna el valor después del símbolo ?, y si la condición es falsa, se asigna el valor después del símbolo : */
        return "Ficha de Pájaro\n" +
                "Nombre: " + getNombre() + "\n" +
                "Especie: " + especie + "\n" +
                "Fecha de Nacimiento: " + getFechaNacimiento() + "\n" +
                "Peso: " + getPeso() + " kg\n" +
                "¿Es cantor?: " + esCantor + "\n" +
                "Comentarios: " + getComentarios();
    }

}


