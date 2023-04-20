/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primerobligatoriop2.Utilidades;

/**
 *
 * @author joaqu
 */
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

}
