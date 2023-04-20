package primerobligatoriop2;

import java.util.*;
import primerobligatoriop2.Utilidades.Perfiles;

public class PrimerObligatorioP2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String ingresoMenu = input.next();
        System.out.println("Presiona R para registrar jugador");
        System.out.println("Presiona S para jugar modo Simple");
        System.out.println("Presiona F para jugar modo Full");
        System.out.println("Presiona D para generar un report de juego");
        System.out.println("Presiona E para salir del juego");
        ingresoMenu = input.next();
        input.nextLine();
        System.out.println("Presiono " + ingresoMenu);
        ArrayList<Perfiles> personas = new ArrayList<>();

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
            for (Perfiles persona : personas) {
                System.out.println(persona.getNombre() + ", " + persona.getEdad() + " años, alias " + persona.getAlias());
            }
        }
    }
}


//FALTA METER TODO EL CODIGO EN UN WHILE QUE HAGA QUE MIENTRAS NO SE PRESIONA LA "E" SIGA TRIGGEREANDO EL MENU. 