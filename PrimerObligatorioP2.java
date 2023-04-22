package primerobligatoriop2;

import java.util.*;
import primerobligatoriop2.Utilidades.*;

public class PrimerObligatorioP2 {

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
        int contadorTurnos = 0;
        ArrayList<Perfiles> personas = new ArrayList<>();
        String[][] mat = new String[6][6];
        int[] cajaRoja = {8, 0};
        int[] cajaAzul = {8, 0};
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

        if (ingresoMenu.charAt(0) == 'S') {

            while (!finJuego) {
                if (cajaRoja[0] == 0 || cajaAzul[0] == 0) {
                    finJuego = true;
                    if (cajaRoja[0] == 0) {
                        ganador = "AZUL";
                        System.out.println("\u001B[31m" + "GANO EL JUGADOR" + ganador + "!!!!!!!!!!!!!!!");
                    } else {
                        ganador = "ROJO";
                        System.out.println("\u001B[34m" + "GANO EL JUGADOR" + ganador + "!!!!!!!!!!!!!!!");
                    }
                    
                }
                System.out.println("Coloque las coordenadas del nuevo gatito");
                String coordenadaX = input.next();
                int coordenadaY = input.nextInt();
                if (contadorTurnos % 2 == 0) {
                    System.out.println("JUGADOR ROJO: Coloque las coordenadas del nuevo gatito");
                    int [] coordenadas =  MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                    MovimientoGatitos.colocarGatito(coordenadas[0], coordenadas[1], mat, "rojo");
                    cajaRoja[0]--;
                } else {
                    System.out.println("JUGADOR AZUL: Coloque las coordenadas del nuevo gatito");
                     int [] coordenadas =  MovimientoGatitos.getCoordenadas(coordenadaX, coordenadaY);
                    MovimientoGatitos.colocarGatito(coordenadas[0], coordenadas[1], mat, "azul");
                    cajaAzul[0]--;
                }

                for (int i = 0; i < mat.length; i++) {
                    for (int j = 0; j < mat[0].length; j++) {
                        if (mat[i][j].contains("rojo")) {
                            System.out.print("\u001B[31m" + mat[i][j].charAt(0) + " ");
                        } else {
                            System.out.print("\u001B[34m" + mat[i][j].charAt(0) + " ");
                        }
                    }
                    System.out.println();
                }
                System.out.println("\u001B[31m" + "GATITOS RESTANTES: " + cajaRoja[0]);
                System.out.println("\u001B[34m" + "GATITOS RESTANTES: " + cajaAzul[0]);

                if (coordenadaY == 0) {
                    finJuego = true;
                }
                contadorTurnos++;
            }

        }
    }
}

// FALTA METER TODO EL CODIGO EN UN WHILE QUE HAGA QUE MIENTRAS NO SE PRESIONA LA "E" SIGA TRIGGEREANDO EL MENU. 
