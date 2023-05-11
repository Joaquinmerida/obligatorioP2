/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primerobligatoriop2;

import java.util.ArrayList;
import java.util.Scanner;
import primerobligatoriop2.Utilidades.Perfiles;

/**
 *
 * @author joaquinmerida
 */
public class borrador {

    public static void main(String[] args) {

        System.out.println("Presiona R para registrar jugador");
        System.out.println("Presiona S para jugar modo Simple");
        System.out.println("Presiona F para jugar modo Full");
        System.out.println("Presiona D para generar un report de juego");
        System.out.println("Presiona E para salir del juego");
        Scanner input = new Scanner(System.in);
        String ingresoMenu = input.next();
        input.nextLine();
        String ganador = "";
        String saltoGatitos = "";
        int contadorTurnos = 0;
        ArrayList<Perfiles> personas = new ArrayList<>();
        String[][] mat = new String[6][6];
        int[] cajaJugadores = {8, 0, 8, 0};
        boolean finJuego = false;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                mat[i][j] = "0";
            }
        }
        while (ingresoMenu.charAt(0) == 'D' || ingresoMenu.charAt(0) == 'R') {
            /*
            GENERAR PDF 
            if(ingresoMenu.charAt(0) ==  'D'){
            LISTA DE JUGADORES ORDENADA ALF
            Y CANTIDAD DE PARTIDAS QUE JUGO CADA UNO
            }
             */
            if (ingresoMenu.charAt(0) == 'R') {
                System.out.println("Ingrese su nombre: ");
                String nombre = input.nextLine();
                System.out.println("Ingrese su edad: ");
                int edad = input.nextInt();
                input.nextLine();
                System.out.println("Ingrese su alias: ");
                String alias = input.nextLine();
                //checkear que el alias sea unico
                Perfiles nuevoPerfil = new Perfiles(nombre, edad, alias);
                personas.add(nuevoPerfil);
                System.out.println(nombre + " " + edad + " " + alias);
                //IMPRIME ARRAY DE PERSONAS INGRESADAS
                for (Perfiles persona : personas) {
                    System.out.println(persona.getNombre() + ", " + persona.getEdad() + " años, alias " + persona.getAlias());
                }
            }
            System.out.println("Presiona R para registrar jugador");
            System.out.println("Presiona S para jugar modo Simple");
            System.out.println("Presiona F para jugar modo Full");
            System.out.println("Presiona D para generar un report de juego");
            System.out.println("Presiona E para salir del juego");
            ingresoMenu = input.next();
            input.nextLine();
        }

    }
}
