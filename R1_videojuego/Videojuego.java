// Enum para las plataformas
enum Plataforma {
    PC,
    PS4,
    XBOX_ONE,
    SWITCH
}

// Enum para los tipos de juego
enum TipoJuego {
    ACCION,
    AVENTURA,
    ESTRATEGIA,
    RPG,
    DEPORTIVO
}

// Clase Videojuego
public class Videojuego {
    private String nombre;
    private int anno;
    private Plataforma plataforma;
    private TipoJuego tipoJuego;
    private int valoracion;

    // Constructor
    public Videojuego(String nombre, int anno, Plataforma plataforma, TipoJuego tipoJuego, int valoracion) {
        setNombre(nombre);
        setAnno(anno);
        this.plataforma = plataforma;
        this.tipoJuego = tipoJuego;
        setValoracion(valoracion);
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del videojuego no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    public TipoJuego getTipoJuego() {
        return tipoJuego;
    }

    public void setTipoJuego(TipoJuego tipoJuego) {
        this.tipoJuego = tipoJuego;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        if (valoracion < 0 || valoracion > 100) {
            throw new IllegalArgumentException("La valoración debe estar entre 0 y 100.");
        }
        this.valoracion = valoracion;
    }

    // Método toString
    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Año: " + anno + ", Plataforma: " + plataforma + ", Tipo de Juego: " + tipoJuego + ", Valoración: " + valoracion;
    }
}
