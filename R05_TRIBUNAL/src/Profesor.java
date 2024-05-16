

public class Profesor {
    private String nombre;
    private String dni;
    private TipoGenero genero;


    public Profesor(String nombre, String dni, TipoGenero genero) { //Un constructor al que le pasamos el nombre, el dni y el genero, comprueba que está bien y los almacena en los atributos.
        setNombre(nombre);
        setDni(dni);
        setGenero(genero);
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
    }
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (dni != null && (dni.length() == 9 || dni.length() == 10)) {
            this.dni = dni;
        } else {
            throw new IllegalArgumentException("El DNI debe tener 9");
        }
    }
    
    public TipoGenero getGenero() {
        return genero;
    }

    public void setGenero(TipoGenero genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return nombre + " - " + dni;
    }

}
