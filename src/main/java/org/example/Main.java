package org.example;

import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<Integer, String[]> OPCIONES = Map.of(
            1, new String[]{"USD", "ARS"},
            2, new String[]{"ARS", "USD"},
            3, new String[]{"USD", "BRL"},
            4, new String[]{"BRL", "USD"},
            5, new String[]{"USD", "COP"},
            6, new String[]{"COP", "USD"}
    );

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int opcion;
            do {
                mostrarMenu();
                opcion = leerEntero(scanner, "Elija una opción válida: ");

                if (opcion == 7) {
                    System.out.println("Gracias por usar el conversor de monedas.");
                    break;
                }

                String[] monedas = OPCIONES.get(opcion);
                if (monedas == null) {
                    System.out.println("Opción no válida.\n");
                    continue;
                }

                double cantidad = leerDouble(scanner, "Ingrese la cantidad que desea convertir: ");
                double resultado = ConversorApp.convert(monedas[0], monedas[1], cantidad);

                System.out.printf("El valor %.2f %s corresponde a ===> %.2f %s%n%n",
                        cantidad, monedas[0], resultado, monedas[1]);
                System.out.println("********************************************************");
            } while (true);
        }
    }

    private static void mostrarMenu() {
        System.out.println("""
                Sea Bienvenido/a al conversor de Moneda

                1) Dólar  ==> Peso Argentino
                2) Peso Argentino ==> Dólar
                3) Dólar  ==> Real brasileño
                4) Real brasileño ==> Dólar
                5) Dólar  ==> Peso Colombiano
                6) Peso Colombiano ==> Dólar
                7) Salir
                """);
    }

    private static int leerEntero(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextInt()) {
            System.out.print("Entrada inválida. " + mensaje);
            sc.next();
        }
        return sc.nextInt();
    }

    private static double leerDouble(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextDouble()) {
            System.out.print("Entrada inválida. " + mensaje);
            sc.next();
        }
        return sc.nextDouble();
    }
}
