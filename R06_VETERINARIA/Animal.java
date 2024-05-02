import java.time.LocalDate;

public abstract class Animal {
    // atributos
    protected String nombre;
    protected LocalDate fechaNacimiento;
    protected double peso;
    protected String comentarios;

    //constructor
    public Animal(String nombre, LocalDate fechaNacimiento, double peso, String comentarios) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.comentarios = comentarios;

    }

    //getters y setters
    public String getNombre() {
        return nombre;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {

        this.peso = peso;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        // Impresión de depuración para verificar los comentarios
        System.out.println("Comentarios obtenidos del área de texto 5: " + comentarios);
        this.comentarios = comentarios;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public abstract String toString();

}
