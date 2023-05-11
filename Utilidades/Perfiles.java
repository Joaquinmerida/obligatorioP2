package primerobligatoriop2.Utilidades;

import java.util.*;

public class Perfiles {

    public String nombre;
    public int edad;
    public String alias;

    public Perfiles(String nombre, int edad, String alias) {
        this.nombre = nombre;
        this.edad = edad;
        this.alias = alias;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
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
        String nombre = input.nextLine();
        System.out.println("Ingrese su edad: ");
        int edad = input.nextInt();
        input.nextLine();
        System.out.println("Ingrese su alias: ");
        String alias = input.nextLine();
        if (Perfiles.esAliasUnico(personas, alias)) {
            System.out.println("no existe");
            Perfiles nuevoPerfil = new Perfiles(nombre, edad, alias);
            personas.add(nuevoPerfil);
            System.out.println("Jugador registrado con exito");
        } else {
            System.out.println("Ese alias ya esta registrado");
        }
    }

}
