import java.util.Scanner;

public void main(String[] args) {
    Hora hora = new Hora(0, 0, 0); // Crear una instancia de Hora
    Scanner scanner = new Scanner(System.in);
    boolean salir = false;

    while (!salir) {
        System.out.println("Seleccione una operación:");
        System.out.println("1. Sumar");
        System.out.println("2. Restar");
        System.out.println("3. Convertir");
        System.out.println("4. Ver hora actual");
        System.out.println("5. Salir");
        System.out.println("\n");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                System.out.println("Seleccione qué desea sumar:");
                System.out.println("1. Sumar horas");
                System.out.println("2. Sumar minutos");
                System.out.println("3. Sumar segundos");
                System.out.println("4. Sumar a la hora actual");
                System.out.println("\n");
                int sumaOpcion = scanner.nextInt();
                switch (sumaOpcion) {
                    case 1:
                        System.out.println("Ingrese el primer valor de horas:");
                        int horas1 = scanner.nextInt();

                        System.out.println("Ingrese el segundo valor de horas:");
                        int horas2 = scanner.nextInt();

                        int resultadoHoras = hora.sumaHoras(horas1, horas2);
                        Hora resultadoHora = new Hora(resultadoHoras, 0, 0); // Crear una instancia de Hora con las horas sumadas
                        System.out.println("El resultado de sumar " + horas1 + " + " + horas2 + " es igual a " + resultadoHora);
                        break;

                    case 2:
                        System.out.println("Ingrese el primer valor de minutos:");
                        int minutos1 = scanner.nextInt();

                        System.out.println("Ingrese el segundo valor de minutos:");
                        int minutos2 = scanner.nextInt();

                        int resultadoMinutos = hora.sumaMinutos(minutos1, minutos2);
                        Hora resultadoMinuto = new Hora(0, resultadoMinutos, 0); // Crear una instancia de Hora con los minutos sumados
                        System.out.println("El resultado de sumar " + minutos1 + " + " + minutos2 + " es igual a " + resultadoMinuto);
                        break;

                    case 3:
                        System.out.println("Ingrese el primer valor de segundos:");
                        int segundos1 = scanner.nextInt();

                        System.out.println("Ingrese el segundo valor de segundos:");
                        int segundos2 = scanner.nextInt();

                        int resultadoSegundos = hora.sumaSegundos(segundos1, segundos2);
                        Hora resultado = new Hora(0, 0, resultadoSegundos); // Crear una instancia de Hora con los segundos sumados
                        System.out.println("El resultado de sumar " + segundos1 + " + " + segundos2 + " es igual a " + resultado);
                        break;


                    case 4:
                        System.out.println("Ingrese la cantidad de horas a sumar:");
                        int horasASumar = scanner.nextInt();
                        System.out.println("Ingrese la cantidad de minutos a sumar:");
                        int minutosASumar = scanner.nextInt();
                        System.out.println("Ingrese la cantidad de segundos a sumar:");
                        int segundosASumar = scanner.nextInt();
                        Hora.sumarHoraActual(horasASumar, minutosASumar, segundosASumar);
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
                break;

            case 2:
                System.out.println("Seleccione qué desea restar:");
                System.out.println("1. Restar horas");
                System.out.println("2. Restar minutos");
                System.out.println("3. Restar segundos");
                System.out.println("\n");
                int restaOpcion = scanner.nextInt();
                switch (restaOpcion) {
                    case 1:
                        System.out.println("Ingrese el primer valor de horas:");
                        int horas1 = scanner.nextInt();

                        System.out.println("Ingrese el segundo valor de horas:");
                        int horas2 = scanner.nextInt();

                        int resultadoHoras = hora.restaHoras(horas1, horas2);
                        Hora resultadoHora = new Hora(resultadoHoras, 0, 0); // Crear una instancia de Hora con las horas restadas
                        System.out.println("El resultado de restar " + horas1 + " - " + horas2 + " es igual a " + resultadoHora);
                        break;

                    case 2:
                        System.out.println("Ingrese el primer valor de minutos:");
                        int minutos1 = scanner.nextInt();

                        System.out.println("Ingrese el segundo valor de minutos:");
                        int minutos2 = scanner.nextInt();

                        int resultadoMinutos = hora.restaMinutos(minutos1, minutos2);
                        Hora resultadoMinuto = new Hora(0, resultadoMinutos, 0); // Crear una instancia de Hora con los minutos restados
                        System.out.println("El resultado de restar " + minutos1 + " - " + minutos2 + " es igual a " + resultadoMinuto);
                        break;

                    case 3:
                        System.out.println("Ingrese el primer valor de segundos:");
                        int segundos1 = scanner.nextInt();

                        System.out.println("Ingrese el segundo valor de segundos:");
                        int segundos2 = scanner.nextInt();

                        int resultadoSegundos = hora.restaSegundos(segundos1, segundos2);
                        Hora resultado = new Hora(0, 0, resultadoSegundos); // Crear una instancia de Hora con los segundos restados
                        System.out.println("El resultado de restar " + segundos1 + " - " + segundos2 + " es igual a " + resultado);
                        break;


                }
                break;


            case 3:
                System.out.println("Seleccione qué desea convertir:");
                System.out.println("1. Horas a minutos");
                System.out.println("2. Horas a segundos");
                System.out.println("3. Minutos a segundos");
                System.out.println("4. Segundos a minutos");
                System.out.println("5. Segundos a horas");
                System.out.println("\n");
                int convertirOpcion = scanner.nextInt();
                switch (convertirOpcion) {
                    case 1:
                        System.out.println("Ingrese la cantidad de horas:");
                        int horasConvertir = scanner.nextInt();
                        System.out.println("Minutos: " + hora.convertirHorasAMinutos(horasConvertir));
                        break;
                    case 2:
                        System.out.println("Ingrese la cantidad de horas:");
                        int horasConvertirSeg = scanner.nextInt();
                        System.out.println("Segundos: " + hora.convertirHorasASegundos(horasConvertirSeg));
                        break;
                    case 3:
                        System.out.println("Ingrese la cantidad de minutos:");
                        int minutosConvertirSeg = scanner.nextInt();
                        System.out.println("Segundos: " + hora.convertirMinutosASegundos(minutosConvertirSeg));
                        break;
                    case 4:
                        System.out.println("Ingrese la cantidad de segundos:");
                        int segundosConvertirMin = scanner.nextInt();
                        System.out.println("Minutos: " + hora.convertirSegundosAMinutos(segundosConvertirMin));
                        break;
                    case 5:
                        System.out.println("Ingrese la cantidad de segundos:");
                        int segundosConvertirHor = scanner.nextInt();
                        System.out.println("Horas: " + hora.convertirSegundosAHoras(segundosConvertirHor));
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;

                }
                break;
            case 4:
                Hora.mostrarHoraLocal();
                break;
            case 5:
                salir = true;
                System.out.println("Saliendo del programa...");
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

}
