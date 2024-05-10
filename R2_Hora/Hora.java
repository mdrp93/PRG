import java.time.LocalTime;

public class Hora {
    private int segundos;

    // Constructor con segundos
    public Hora(int segundos) {
        if (segundos < 0) {
            throw new IllegalArgumentException("Los segundos no pueden ser negativos.");
        }
        this.segundos = segundos;
    }

    // Constructor con horas, minutos y segundos
    public Hora(int horas, int minutos, int segundos) {
        if (minutos < 0 || minutos > 59 || segundos < 0 || segundos > 59) {
            throw new IllegalArgumentException("Los minutos y segundos deben estar entre 0 y 59.");
        }
        if (horas < 0) {
            throw new IllegalArgumentException("Las horas no pueden ser negativas.");
        }
        this.segundos = horas * 3600 + minutos * 60 + segundos;
    }

    // Propiedades
    public int getSegundosTotales() {
        return segundos;
    }

    public void setSegundosTotales(int segundos) {
        if (segundos < 0) {
            throw new IllegalArgumentException("Los segundos no pueden ser negativos.");
        }
        this.segundos = segundos;
    }

    public int getHoras() {
        return segundos / 3600;
    }

    public int getMinutos() {
        return (segundos % 3600) / 60;
    }

    public int getSegundos() {
        return segundos % 60;
    }

    public void setHoras(int horas) {
        segundos = horas * 3600 + getMinutos() * 60 + getSegundos();
    }

    public void setMinutos(int minutos) {
        segundos = getHoras() * 3600 + minutos * 60 + getSegundos();
    }

    public void setSegundos(int segundos) {
        this.segundos = getHoras() * 3600 + getMinutos() * 60 + segundos;
    }

    // Métodos
    public int sumaHoras(int horas1, int horas2) {
        int segundos1 = convertirHorasASegundos(horas1);
        int segundos2 = convertirHorasASegundos(horas2);
        int horasSumadas = convertirSegundosAHoras(segundos1 + segundos2);
        return horasSumadas;
    }

    public int sumaMinutos(int minutos1, int minutos2) {
        int segundos1 = convertirMinutosASegundos(minutos1);
        int segundos2 = convertirMinutosASegundos(minutos2);
        int minutosSumados = convertirSegundosAMinutos(segundos1 + segundos2);
        return minutosSumados;
    }

    public int sumaSegundos(int segundos1, int segundos2) {
        return segundos1 + segundos2;
    }

    // Método en la clase Hora para formatear los segundos en horas, minutos y segundos
    public String formatearTiempo(int segundos) {
        int horas = segundos / 3600;
        int minutos = (segundos % 3600) / 60;
        int segundosRestantes = segundos % 60;
        return String.format("%02d:%02d:%02d", horas, minutos, segundosRestantes);
    }



    public int restaHoras(int horas1, int horas2) {
        int segundos1= convertirHorasASegundos(horas1);
        int segundos2= convertirHorasASegundos(horas2);
        int horasRestadas = convertirSegundosAHoras( segundos1 - segundos2);
        return horasRestadas;
    }


    public int restaMinutos(int minutos1, int minutos2) {
        int segundos1 = convertirMinutosASegundos(minutos1);
        int segundos2 = convertirMinutosASegundos(minutos2);
        int minutosRestados = convertirSegundosAMinutos(segundos1 - segundos2);
        return minutosRestados;
    }

    public int restaSegundos(int segundos1, int segundos2) {
        return segundos1 - segundos2;
    }




    // Conversión entre horas, minutos y segundos
    public int convertirHorasAMinutos(int horas) {
        return horas * 60;
    }

    public int convertirHorasASegundos(int horas) {
        return horas * 3600;
    }

    public int convertirMinutosASegundos(int minutos) {
        return minutos * 60;
    }

    public int convertirSegundosAMinutos(int segundos) {
        return segundos / 60;
    }

    public int convertirSegundosAHoras(int segundos) {
        return segundos / 3600;
    }

    // Operadores
    public void add(Hora h) {
        this.segundos += h.getSegundosTotales();
    }

    public void substract(Hora h) {
        this.segundos -= h.getSegundosTotales();
    }


    // Override
    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", getHoras(), getMinutos(), getSegundos());
    }



    public static void sumarHoraActual(int horas, int minutos, int segundos) {
        LocalTime horaActual = LocalTime.now();
        int segundosTotales = horas * 3600 + minutos * 60 + segundos;
        LocalTime nuevaHora = horaActual.plusSeconds(segundosTotales);
        String horaActualFormateada = String.format("%02d:%02d:%02d", horaActual.getHour(), horaActual.getMinute(), horaActual.getSecond());
        String nuevaHoraFormateada = String.format("%02d:%02d:%02d", nuevaHora.getHour(), nuevaHora.getMinute(), nuevaHora.getSecond());
        System.out.println("La hora actual: " + horaActualFormateada + ", la hora dada: " + String.format("%02d:%02d:%02d", horas, minutos, segundos) + " es igual a " + nuevaHoraFormateada + "\n");
    }

    public static void mostrarHoraLocal() {
        LocalTime horaActual = LocalTime.now();
        String horaFormateada = String.format("%02d:%02d:%02d", horaActual.getHour(), horaActual.getMinute(), horaActual.getSecond());
        System.out.println("La hora actual es: " + horaFormateada);
    }

}
