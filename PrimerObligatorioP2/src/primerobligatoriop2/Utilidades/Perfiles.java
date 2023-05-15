package primerobligatoriop2.Utilidades;

import java.util.*;

public class Perfiles {

    public String nombre;
    public int edad;
    public String alias;
    public int contadorPartidas;

    public Perfiles(String nombre, int edad, String alias, int contadorPartidas) {
        this.nombre = nombre;
        this.edad = edad;
        this.alias = alias;
        this.contadorPartidas = 0;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getContadorPartidas() {
        return contadorPartidas;
    }

    public void setContadorPartidas(int contadorPartidas) {
        this.contadorPartidas = contadorPartidas;
    }

    public static void sumarPartida(ArrayList<Perfiles> personas, String alias) {
        for (Perfiles perfil : personas) {
            if (perfil.getAlias().equals(alias)) {
                perfil.setContadorPartidas(perfil.getContadorPartidas() + 1);
                break;
            }
        }
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public static boolean esAliasUnico(ArrayList<Perfiles> personas, String alias) {
        for (Perfiles persona : personas) {
            if (persona.getAlias().equals(alias)) {
                return false;
            }
        }

        return true;
    }

    public static void registrarJugador(ArrayList<Perfiles> personas) {
        Scanner input = new Scanner(System.in);
        System.out.println("Ingrese su nombre: ");
        String nombre = input.next();
        int edad = 0;
        boolean esEntero = false;
        while (!esEntero) {
            System.out.println("Ingrese su edad: ");
            if (input.hasNextInt()) {
                edad = input.nextInt();
                esEntero = true;
            } else {
                System.out.println("No se ha ingresado un valor entero. Intente nuevamente.");
                input.next();
            }
        }
        input.nextLine();
        System.out.println("Ingrese su alias: ");
        String alias = input.nextLine();
        if (Perfiles.esAliasUnico(personas, alias)) {
            Perfiles nuevoPerfil = new Perfiles(nombre, edad, alias, 0);
            personas.add(nuevoPerfil);
            System.out.println("Jugador registrado con exito");
        } else {
            System.out.println("Ese alias ya esta registrado");
        }
    }

}
