package com.farmacy.Console;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/*
 * El siguiente codigo lo copie del repositorio de Agenicia De Vuelos
 * https://github.com/trainingLeader/AgenciaDeVuelos/tree/main
 * Esto con el objetivo que el trainer no rompa el codigo
 * Te queremos Jholver <3  
 */

public class Util {
    
    /**
     * Método para obtener un número decimal positivo desde la entrada estándar.
     * Muestra un mensaje, solicita la entrada del usuario y valida que sea un número válido y positivo.
     * Si la entrada no es válida, muestra un mensaje de error y solicita nuevamente la entrada.
     *
     * @param message El mensaje que se mostrará al usuario para solicitar la entrada.
     * @return El número decimal positivo ingresado por el usuario.
     */
    public static int getIntInput(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        while (true) {
            try {
                String input = scanner.nextLine().trim(); 
                int num = Integer.parseInt(input);  
                if (num >= 0){
                    return num;
                }
                System.out.println("!\tERROR: Ingresa un numero positivo");

            } catch (NumberFormatException e) {
                System.out.println("!\tERROR: Debes ingresar un número entero. Intenta de nuevo.");
                System.out.println(message);
            }
        }
    }

    /**
     * Modificado para tener Floats en vez de Double
     * Método para obtener un número decimal positivo desde la entrada estándar.
     * Muestra un mensaje, solicita la entrada del usuario y valida que sea un número válido y positivo.
     * Si la entrada no es válida, muestra un mensaje de error y solicita nuevamente la entrada.
     *
     * @param message El mensaje que se mostrará al usuario para solicitar la entrada.
     * @return El número decimal positivo ingresado por el usuario.
     */
    public static float getFloatInput(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        while (true) {
            try {
                String input = scanner.nextLine().trim(); 
                Float num = Float.parseFloat(input);  
                if (num >= 0){
                    return num;
                }
                System.out.println("!\tERROR: Ingresa un numero positivo");

            } catch (NumberFormatException e) {
                System.out.println("!\tERROR: Debes ingresar un número válido. Intenta de nuevo.");
                System.out.println(message);
            }
        }
    }

    /**
     * Solicita al usuario ingresar un texto desde la consola.
     * Muestra un mensaje proporcionado y espera a que se ingrese un texto no vacío.
     *
     * @param message El mensaje que se mostrará al usuario antes de solicitar la entrada.
     * @return El texto ingresado por el usuario (no vacío).
     */
    public static String getStringInput(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        while (true) {
            String value = scanner.nextLine().trim();
            if (!value.equals("")) {
                return value;
            }
            System.out.println("!\tERROR: No es posible registrar un texto vacio. Intente de nuevo: ");
        }
    }


    /**
     * Verifica si una cadena de fecha dada coincide con el patrón de formato especificado.
     *
     * @param date   La cadena de fecha que se va a verificar.
     * @param format El patrón de formato esperado para la cadena de fecha (en formato DateTimeFormatter de Java).
     * @return {@code true} si la cadena de fecha coincide con el formato especificado,
     *         {@code false} si no coincide o si ocurre un error de análisis.
     */
    public static boolean checkDateFormat(String date, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        try {
            LocalDate.parse(date, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /*
     * Codigo dado por el trainer para poder limpiar la consola
     */
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
